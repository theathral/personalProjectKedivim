package utils;

import author.Author;
import document.*;
import document.thesis.BachelorThesis;
import document.thesis.DoctoralThesis;
import document.thesis.MasterThesis;
import document.thesis.Thesis;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.*;

public class UI {

    private static final String BIN_PATH = "libDB.bin";
    private static final String SEARCH_PATH = "search_";
    private static final String TXT = ".txt";

    private static Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dummyData();

        System.out.println(UIMsg.welcomeMsg());
        pressEnter();

        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Load Database", "Save to Database", "Documents", "Authors", "Statistics", "EXIT PROGRAM"));
        do {
            int choice = inputChoiceRange(UIMsg.mainMenu(choiceList), 0, choiceList.size() - 1);
            if (choice == 0) // EXIT Program
                if (exit())
                    return;
                else continue;

            switch (choiceList.get(choice - 1)) {
                case "Load Database" -> loadDB();
                case "Save to Database" -> saveToDB();
                case "Documents" -> documents();
                case "Authors" -> authors();
                case "Statistics" -> printSearchAndSaveToDB(library.printStatsStr());
                default -> throw new RuntimeException();
            }
        } while (true);
    }

    private static void pressEnter() {
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    private static int inputChoiceRange(String msg, int min, int max) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkRange(Integer.parseInt(scanner.nextLine()), min, max);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static boolean inputChoiceBoolean(String msg) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkRange(Integer.parseInt(scanner.nextLine()), 0, 1) == 1;
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static int inputChoiceMin(String msg, int min) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkMin(Integer.parseInt(scanner.nextLine()), min);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static String inputLine(String msg) throws IllegalArgumentException {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkString(scanner.nextLine());
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    private static ZonedDateTime inputDate(String msg) {
        do {
            try {
                return MyUtilities.checkDate(inputLine(msg));
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static boolean exit() {
        if (inputChoiceBoolean(UIMsg.exitMsg())) {
            saveToDBBeforeExit();
            return true;
        }
        return false;
    }

    private static void saveToDBBeforeExit() {
        if (inputChoiceBoolean(UIMsg.saveToDBMsg("database")))
            saveToDB();
    }

    private static void loadDB() {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Default file", "Custom file", "Cancel"));

        do {
            try {
                int choice = inputChoiceRange(UIMsg.loadDBMsg(choiceList), 0, choiceList.size() - 1);
                if (choice == 0) // Cancel
                    return;

                switch (choiceList.get(choice - 1)) {
                    case "Default file" -> library = library.loadFile(BIN_PATH);
                    case "Custom file" -> library = library.loadFile(inputLine(UIMsg.actionMsg("path", "File", "load")));
                    default -> throw new RuntimeException();
                }
                return;

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(UIMsg.wrongFileMsg());
                pressEnter();
            } catch (IllegalArgumentException e) {
                System.out.println(UIMsg.libraryNotEmptyMsg());
                pressEnter();
                return;
            }
        } while (true);
    }

    private static void saveToDB() {
        do {
            try {
                if (new File(BIN_PATH).isFile()) {
                    if (!inputChoiceBoolean(UIMsg.overrideDBMsg()))
                        return;
                    break;
                }
                library.writeToBinaryFile(BIN_PATH);
                return;
            } catch (IOException e) {
                System.out.println(UIMsg.wrongMsg());
            }
        } while (true);
    }

    private static void printSearchAndSaveToDB(String searchResult) {
        String filePath = SEARCH_PATH + System.currentTimeMillis() + TXT;

        System.out.println(searchResult);
        pressEnter();

        if (inputChoiceBoolean(UIMsg.saveToDBMsg("search")))
            try {
                library.saveToFile(searchResult, filePath);
            } catch (IOException e) {
                System.out.println(UIMsg.wrongMsg());
            }
    }


    private static void documents() {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Print All", "Search Document", "Add Document", "Modify Document", "Delete Document", "DELETE ALL DOCUMENTS", "Back to Main Menu"));

        do {
            int choice = inputChoiceRange(UIMsg.subMenus("Documents", choiceList), 0, choiceList.size() - 1);
            if (choice == 0) // Back to Main Menu
                return;

            switch (choiceList.get(choice - 1)) {
                case "Print All" -> printSearchAndSaveToDB(library.printDocumentsStr());
                case "Search Document" -> searchAndPrintDocument();
                case "Add Document" -> addDocument();
                case "Modify Document" -> modifyDocument();
                case "Delete Document" -> deleteDocument();
                case "DELETE ALL DOCUMENTS" -> deleteAllDocuments();
                default -> throw new RuntimeException();
            }
        } while (true);
    }

    private static void searchAndPrintDocument() {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Code", "Title", "Cancel"));
        ArrayList<String> choiceExcList = new ArrayList<>(Arrays.asList("Try something else", "Add Document", "Cancel"));

        do {
            try {
                int choice = inputChoiceRange(UIMsg.searchDocumentMsg(choiceList), 0, choiceList.size() - 1);
                if (choice == 0) // Cancel
                    return;

                switch (choiceList.get(choice - 1)) {
                    case "Code" -> printSearchAndSaveToDB(library.printDocumentWithCodeStr(inputLine(UIMsg.actionMsg("Code", "Document", "search"))));
                    case "Title" -> printSearchAndSaveToDB(library.printDocumentWithTitleStr(inputLine(UIMsg.actionMsg("Title (or part of it)", "Document", "search"))));
                    default -> throw new RuntimeException();
                }
                return;
            } catch (IndexOutOfBoundsException e) {
                int choice = inputChoiceRange(UIMsg.objectNotFoundAndAddMsg("Document", choiceExcList), 0, choiceExcList.size() - 1);
                if (choice == 0) // Cancel
                    return;

                switch (choiceExcList.get(choice - 1)) {
                    case "Try something else" -> {
                    }
                    case "Add Document" -> {
                        addDocument();
                        return;
                    }
                    default -> throw new RuntimeException();
                }
            }
        }
        while (true);
    }

    private static DocInterface searchDocument(String action) {
        do {
            try {
                return library.getDocument(inputLine(UIMsg.actionMsg("Code", "Document", action)));

            } catch (IndexOutOfBoundsException e) {
                if (!inputChoiceBoolean(UIMsg.objectNotFoundMsg("Document")))
                    return null;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    private static String searchDocumentCode(String typeDoc) {
        do {
            try {
                String code = inputLine(UIMsg.inputMsg("Code", typeDoc));

                if (library.findDocumentByCode(code) == -1)
                    return code;

                if (!inputChoiceBoolean(UIMsg.objectFoundMsg("Document")))
                    return null;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    private static void addDocument() {
        ArrayList<String> typeKeys = new ArrayList<>(library.getTypeOfDocuments().keySet());
        typeKeys.add("Cancel");
        DocInterface newDoc;

        int choice = inputChoiceRange(UIMsg.typesListMsg(typeKeys), 0, typeKeys.size());
        if (choice == 0)
            return;

        String choiceStr = typeKeys.get(choice - 1);

        String code = searchDocumentCode(choiceStr);
        if (code == null)
            return;

        String title = inputLine(UIMsg.inputMsg("Title", choiceStr));
        int year = inputChoiceRange(UIMsg.inputMsg("Year", choiceStr), 1500, Year.now().getValue());
        int numOfPages = inputChoiceMin(UIMsg.inputMsg("Number of Pages", choiceStr), 1);
        int numOfCopies = inputChoiceMin(UIMsg.inputMsg("Number of Copies", choiceStr), 0);

        switch (choiceStr) {
            case "Book", "Journal" -> {
                String publisher = inputLine(UIMsg.inputMsg("Publisher", choiceStr));
                String isbn = inputLine(UIMsg.inputMsg("ISBN", choiceStr));

                switch (choiceStr) {
                    case "Book" -> {
                        ArrayList<Author> authorsList = addBookAuthors();
                        if (authorsList == null)
                            return;

                        newDoc = (new Book(code, title, year, numOfPages, numOfCopies, publisher, isbn, authorsList));
                    }
                    case "Journal" -> {
                        int volume = inputChoiceMin(UIMsg.inputMsg("Volume", choiceStr), 1);
                        int issue = inputChoiceMin(UIMsg.inputMsg("Issue", choiceStr), 0);
                        newDoc = new Journal(code, title, year, numOfPages, numOfCopies, publisher, isbn, volume, issue);
                    }
                    default -> throw new RuntimeException();
                }
            }
            case "Bachelor Thesis", "Master Thesis", "Doctoral Thesis" -> {
                Author author = searchAuthor("add");
                if (author == null)
                    return;

                String supervisor = inputLine(UIMsg.inputMsg("Supervisor", choiceStr));
                String department = inputLine(UIMsg.inputMsg("Department", choiceStr));
                String university = inputLine(UIMsg.inputMsg("University", choiceStr));

                newDoc = switch (choiceStr) {
                    case "Bachelor Thesis" -> new BachelorThesis(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
                    case "Master Thesis" -> new MasterThesis(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
                    case "Doctoral Thesis" -> new DoctoralThesis(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
                    default -> throw new RuntimeException();
                };
            }
            default -> throw new RuntimeException();
        }

        if (inputChoiceBoolean(UIMsg.newObjectCreatedMsg(newDoc, "Document"))) {
            library.addDocument(newDoc);
        }
    }

    private static ArrayList<Author> addBookAuthors() {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Add more", "No, continue with the other attributes", "Cancel"));

        ArrayList<Author> authorsList = new ArrayList<>();
        do {
            authorsList.add(searchAuthor("add"));
            if (authorsList.get(authorsList.size() - 1) == null)
                return null;

            if (authorsList.size() >= 5)
                return authorsList;

            int choice = inputChoiceRange(UIMsg.addMoreAuthors(choiceList), 0, choiceList.size() - 1);
            if (choice == 0) // Cancel
                return null;

            switch (choiceList.get(choice - 1)) {
                case "Add more" -> {
                }
                case "No, continue with the other attributes" -> {
                    return authorsList;
                }
                default -> throw new RuntimeException();
            }
        } while (true);
    }

    private static void modifyDocument() {
        DocInterface modDoc = searchDocument("modify");
        if (modDoc == null)
            return;

        System.out.println(modDoc);

        String modDocClass = modDoc.getClass().getSimpleName();

        ArrayList<String> attributes = modifyDocumentAttributes(modDocClass);
        int choice = inputChoiceRange(UIMsg.attributesListMsg(attributes), 0, attributes.size());
        if (choice == 0)
            return;

        switch (modDocClass) {
            case "Book" -> modifyBookClass((Book) modDoc, attributes.get(choice - 1));
            case "Journal" -> modifyJournalClass((Journal) modDoc, attributes.get(choice - 1));
            case "BachelorThesis" -> modifyThesisClass((Thesis) modDoc, attributes.get(choice - 1), "Bachelor Thesis");
            case "MasterThesis" -> modifyThesisClass((Thesis) modDoc, attributes.get(choice - 1), "Master Thesis");
            case "DoctoralThesis" -> modifyThesisClass((Thesis) modDoc, attributes.get(choice - 1), "Doctoral Thesis");
        }
    }

    private static ArrayList<String> modifyDocumentAttributes(String modDocClass) {
        ArrayList<String> attributes = new ArrayList<>(Arrays.asList("Code", "Title", "Year", "Number of Pages", "Number of Copies"));

        switch (modDocClass) {
            case "Book", "Journal" -> {
                attributes.addAll(Arrays.asList("Publisher", "ISBN"));
                switch (modDocClass) {
                    case "Book" -> attributes.add("Authors");
                    case "Journal" -> attributes.addAll(Arrays.asList("Volume", "Issue"));
                    default -> throw new RuntimeException();
                }
            }
            case "BachelorThesis", "MasterThesis", "DoctoralThesis" -> attributes.addAll(Arrays.asList("Author", "Supervisor", "Department", "University"));
            default -> throw new RuntimeException();
        }

        return attributes;
    }

    private static void modifyAuthorDocument(Book document) {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Add New", "Delete", "Cancel"));

        do {
            int choice = inputChoiceRange(UIMsg.modifyAuthorsDocument(choiceList), 0, choiceList.size() - 1);
            if (choice == 0) // Cancel
                return;

            switch (choiceList.get(choice - 1)) {
                case "Add New" -> {
                    if (document.getAuthors().size() >= 5) {
                        if (inputChoiceBoolean(UIMsg.objectCannotActionMsg("action", "proceeded", "there are already 5 authors")))
                            continue;
                        return;
                    }

                    Author newAuthor = searchAuthor("add");
                    if (newAuthor == null)
                        return;
                    if (document.getAuthors().contains(newAuthor)) {
                        if (inputChoiceBoolean(UIMsg.objectFoundMsg("Author")))
                            continue;
                        return;
                    }

                    document.addAuthor(newAuthor);
                    return;
                }
                case "Delete" -> {
                    if (document.getAuthors().size() <= 1) {
                        if (inputChoiceBoolean(UIMsg.objectCannotActionMsg("action", "proceeded", "there is only 1 author")))
                            continue;
                        return;
                    }

                    Author delAuthor = searchAuthor("remove");
                    if (delAuthor != null)
                        document.removeAuthor(delAuthor);

                    return;
                }
            }
        }
        while (true);
    }

    private static void modifyDocumentClass(Document document, String attr, String type) {
        switch (attr) {
            case "Code" -> {
                String code = searchDocumentCode(type);
                document.setTitle(code);
            }
            case "Title" -> document.setTitle(inputLine(UIMsg.inputMsg("new Title", type)));
            case "Year" -> document.setYear(inputChoiceRange(UIMsg.inputMsg("new Year", type), 1500, Year.now().getValue()));
            case "Number of Pages" -> document.setNumOfPages(inputChoiceMin(UIMsg.inputMsg("new Number of Pages", type), 1));
            case "Number of Copies" -> document.setNumOfCopies(inputChoiceMin(UIMsg.inputMsg("new Number of Copies", type), 0));

            default -> throw new RuntimeException();
        }
    }

    private static void modifyPaperClass(Paper document, String attr, String type) {
        switch (attr) {
            case "Publisher" -> document.setPublisher(inputLine(UIMsg.inputMsg("new Publisher", type)));
            case "ISBN" -> document.setISBN(inputLine(UIMsg.inputMsg("new ISBN", type)));

            default -> modifyDocumentClass(document, attr, type);
        }
    }

    private static void modifyBookClass(Book document, String attr) {
        if (attr.equals("Authors"))
            modifyAuthorDocument(document);
        else
            modifyPaperClass(document, attr, "Book");
    }

    private static void modifyJournalClass(Journal document, String attr) {
        switch (attr) {
            case "Volume" -> document.setVolume(inputChoiceMin(UIMsg.inputMsg("new Volume", "Journal"), 1));
            case "Issue" -> document.setVolume(inputChoiceMin(UIMsg.inputMsg("new Volume", "Journal"), 0));

            default -> modifyPaperClass(document, attr, "Journal");
        }
    }

    private static void modifyThesisClass(Thesis document, String attr, String type) {
        switch (attr) {
            case "Author" -> {
                Author modAuthor = searchAuthor("replace with");
                if (modAuthor == null)
                    return;

                document.setAuthor(modAuthor);
            }
            case "Supervisor" -> document.setSupervisor(inputLine(UIMsg.inputMsg("new Supervisor", type)));
            case "Department" -> document.setDepartment(inputLine(UIMsg.inputMsg("new Department", type)));
            case "University" -> document.setUniversity(inputLine(UIMsg.inputMsg("new University", type)));

            default -> modifyDocumentClass(document, attr, type);
        }
    }

    private static void deleteDocument() {
        DocInterface delDoc = searchDocument("delete");
        if (delDoc == null)
            return;

        if (inputChoiceBoolean(UIMsg.objectDeletionMsg(delDoc, "Document")))
            library.deleteDocument(delDoc.getCode());
    }

    private static void deleteAllDocuments() {
        if (inputChoiceBoolean(UIMsg.objectDeletionMsg("ALL DOCUMENTS" + System.lineSeparator(), "Document")))
            library.deleteAllDocuments();
    }


    private static void authors() {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Print All", "Search Author", "Add Author", "Modify Author", "Delete Author", "Back to Main Menu"));

        do {
            int choice = inputChoiceRange(UIMsg.subMenus("Authors", choiceList), 0, choiceList.size() - 1);
            if (choice == 0) // Back to Main Menu
                return;

            switch (choiceList.get(choice - 1)) {
                case "Print All" -> printSearchAndSaveToDB(library.printAuthorsStr());
                case "Search Author" -> searchAndPrintAuthor();
                case "Add Author" -> addAuthor();
                case "Modify Author" -> modifyAuthor();
                case "Delete Author" -> deleteAuthor();
                default -> throw new RuntimeException();
            }
        } while (true);
    }

    private static void searchAndPrintAuthor() {
        ArrayList<String> choiceList = new ArrayList<>(Arrays.asList("Try something else", "Add Author", "Cancel"));

        do {
            try {
                printSearchAndSaveToDB(library.printAuthorStr(inputLine(UIMsg.actionMsg("Name", "Author", "search"))));
                return;
            } catch (IndexOutOfBoundsException e) {
                int choice = inputChoiceRange(UIMsg.objectNotFoundAndAddMsg("Author", choiceList), 0, choiceList.size() - 1);
                if (choice == 0) // Cancel
                    return;

                switch (choiceList.get(choice - 1)) {
                    case "Try something else" -> {
                    }
                    case "Add Author" -> {
                        addAuthor();
                        return;
                    }
                    default -> throw new RuntimeException();
                }
            }
        } while (true);
    }

    private static Author searchAuthor(String action) {
        do {
            try {
                return library.getAuthor(inputLine(UIMsg.actionMsg("Name", "Author", action)));

            } catch (IndexOutOfBoundsException e) {
                if (!inputChoiceBoolean(UIMsg.objectNotFoundMsg("Author")))
                    return null;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    private static String searchNameAuthor() {
        do {
            try {
                String name = inputLine(UIMsg.inputMsg("Name", "Author"));
                if (library.findAuthor(name) == -1)
                    return name;

                if (!inputChoiceBoolean(UIMsg.objectFoundMsg("Author")))
                    return null;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    private static void addAuthor() {
        String name = searchNameAuthor();
        if (name == null)
            return;

        ZonedDateTime dateOfBirth = inputDate(UIMsg.inputMsg("Date of Birth (d-m-Y)", "Author"));
        String description = inputLine(UIMsg.inputMsg("Description", "Author"));

        Author newAuthor = new Author(name, dateOfBirth, description);
        if (inputChoiceBoolean(UIMsg.newObjectCreatedMsg(newAuthor, "Author")))
            library.addAuthor(newAuthor);
    }

    private static void modifyAuthor() {
        Author modAuthor = searchAuthor("modify");
        if (modAuthor == null)
            return;

        System.out.println(modAuthor);

        ArrayList<String> attributes = new ArrayList<>(Arrays.asList("Name", "Date of Birth", "Description"));
        int choice = inputChoiceRange(UIMsg.attributesListMsg(attributes), 0, attributes.size());
        if (choice == 0)
            return;

        switch (attributes.get(choice - 1)) {
            case "Name" -> {
                String name = searchNameAuthor();
                modAuthor.setName(name);
            }
            case "Date of Birth" -> modAuthor.setDateOfBirth(inputDate(UIMsg.inputMsg("new Date of Birth", "Author")));
            case "Description" -> modAuthor.setDescription(inputLine(UIMsg.inputMsg("new Description", "Author")));
        }
    }

    private static void deleteAuthor() {
        Author delAuthor;

        do {
            try {
                delAuthor = searchAuthor("delete");
                if (delAuthor == null)
                    return;

                if (!delAuthor.getDocuments().isEmpty()) {
                    if (!inputChoiceBoolean(UIMsg.objectCannotActionMsg("Author", "deleted", "exists in documents")))
                        return;
                    continue;
                }
                break;

            } catch (IllegalArgumentException ignored) {
            }
        } while (true);

        if (inputChoiceBoolean(UIMsg.objectDeletionMsg(delAuthor, "Author")))
            library.deleteAuthor(delAuthor.getName());
    }

    private static void dummyData() {
        library.addAuthor(new Author("Theodosius", new GregorianCalendar(1998, Calendar.MAY, 15).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Niki", new GregorianCalendar(1972, Calendar.JANUARY, 22).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Rallies", new GregorianCalendar(1964, Calendar.FEBRUARY, 27).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Panos", new GregorianCalendar(1998, Calendar.SEPTEMBER, 17).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Nikos", new GregorianCalendar(1998, Calendar.APRIL, 6).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("George", new GregorianCalendar(1998, Calendar.APRIL, 27).toZonedDateTime(), "One of the best"));

        library.addDocument(new Book("1001", "aBook", 1998, 100, 100,
                "Someone", "0001", new ArrayList<>(Arrays.asList(library.getAuthor("Panos"), library.getAuthor("Nikos")))));
        library.addDocument(new Journal("1002", "aJournal", 2000, 100, 100,
                "Someone", "0001", 1, 1));
        library.addDocument(new BachelorThesis("1003", "aBachelor", 1998, 100, 100,
                library.getAuthor("Theodosius"), "somebody", "Theodosius", "AUTH"));
        library.addDocument(new MasterThesis("1004", "aMaster", 1972, 100, 100,
                library.getAuthor("Niki"), "Somebody", "Theodosius", "AUTH"));
        library.addDocument(new DoctoralThesis("1005", "aDoctoral", 1964, 100, 100,
                library.getAuthor("Rallies"), "Somebody", "CSD", "AUTH"));

    }


}
