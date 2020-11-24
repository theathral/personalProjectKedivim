package utils;

import author.Author;
import document.Book;
import document.Document;
import document.Journal;
import document.Paper;
import document.thesis.BachelorThesis;
import document.thesis.DoctoralThesis;
import document.thesis.MasterThesis;
import document.thesis.Thesis;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class UI {

    private static final String PATH = "libDB.bin";

    private static Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dummyData();

        System.out.println(UIMsg.welcomeMsg());
        pressEnter();

        do {
            switch (inputChoiceRange(UIMsg.mainMenu(), 0, 5)) {
                case 0:
                    if (exit())
                        return;
                    break;
                case 1:
                    loadDB();
                    break;
                case 2:
                    saveToDB();
                    break;
                case 3:
                    documents();
                    break;
                case 4:
                    authors();
                    break;
                case 5:
                    System.out.println(new LibraryPrint(library).printStats());
                    pressEnter();
                    break;
                default:
                    throw new RuntimeException();
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
        if (inputChoiceBoolean(UIMsg.saveToDBBeforeExitMsg()))
            saveToDB();
    }

    private static void loadDB() {
        do {
            try {
                switch (inputChoiceRange(UIMsg.loadDBMsg(), 0, 2)) {
                    case 0:
                        return;
                    case 1:
                        library = library.loadFile(PATH);
                        return;
                    case 2:
                        library = library.loadFile(inputLine(UIMsg.loadCustomDBMsg()));
                        return;
                    default:
                        throw new RuntimeException();
                }
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
                if (new File(PATH).isFile()) {
                    if (!inputChoiceBoolean(UIMsg.overrideDBMsg()))
                        return;
                    break;
                }
                library.writeToBinaryFile(PATH);
                return;
            } catch (IOException e) {
                System.out.println(UIMsg.wrongMsg());
                System.out.println(e.toString());
            }
        } while (true);
    }


    private static void documents() {
        do {
            switch (inputChoiceRange(UIMsg.documentsMenu(), 0, 5)) {
                case 0:
                    return;
                case 1:
                    System.out.println(new LibraryPrint(library).printDocuments());
                    pressEnter();
                    break;
                case 2:
                    searchAndPrintDocument();
                    break;
                case 3:
                    addDocument();
                    break;
                case 4:
                    modifyDocument();
                    break;
                case 5:
                    deleteDocument();
                    break;
                default:
                    throw new RuntimeException();
            }
        } while (true);
    }

    private static void searchAndPrintDocument() {
        do {
            try {
                switch (inputChoiceRange(UIMsg.searchDocumentMsg(), 0, 2)) {
                    case 0:
                        return;
                    case 1:
                        System.out.println(new LibraryPrint(library).printDocumentWithCode(inputLine(UIMsg.searchDocByCodeMsg())));
                        break;
                    case 2:
                        System.out.println(new LibraryPrint(library).printDocumentWithTitle(inputLine(UIMsg.searchDocByTitleMsg())));
                        break;
                    default:
                        throw new RuntimeException();
                }
                pressEnter();
                return;
            } catch (IndexOutOfBoundsException e) {
                switch (inputChoiceRange(UIMsg.objectNotFoundAndAddMsg("Document"), 0, 2)) {
                    case 0:
                        return;
                    case 1:
                        break;
                    case 2:
                        addDocument();
                        return;
                    default:
                        throw new RuntimeException();
                }
            }
        } while (true);
    }

    private static Document searchDocument(String action) {
        do {
            try {
                return library.getDocument(inputLine(UIMsg.findMsg("Code", "Document", action)));

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
        Document newDoc;

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
        ArrayList<Author> authorsList = new ArrayList<>();
        do {
            authorsList.add(searchAuthor("add"));
            if (authorsList.get(authorsList.size() - 1) == null)
                return null;

            if (authorsList.size() >= 5)
                return authorsList;

            int addMoreAuthorsChoice = inputChoiceRange(UIMsg.addMoreAuthors(), 0, 2);
            if (addMoreAuthorsChoice == 0)
                return null;
            else if (addMoreAuthorsChoice == 2)
                return authorsList;
            else if (addMoreAuthorsChoice != 1)
                throw new RuntimeException();

        } while (true);
    }

    private static void modifyDocument() {
        Document modDoc = searchDocument("modify");
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
        do {
            switch (inputChoiceRange(UIMsg.modifyAuthorsDocument(), 0, 2)) {
                case 0:
                    return;
                case 1:
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
                case 2:
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
        } while (true);
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
        Document delDoc = searchDocument("delete");
        if (delDoc == null)
            return;

        if (inputChoiceBoolean(UIMsg.objectDeletionMsg(delDoc, "Document")))
            library.deleteDocument(delDoc.getCode());
    }


    private static void authors() {
        do {
            switch (inputChoiceRange(UIMsg.authorsMenu(), 0, 5)) {
                case 0:
                    return;
                case 1:
                    System.out.println(new LibraryPrint(library).printAuthors());
                    pressEnter();
                    break;
                case 2:
                    searchAndPrintAuthor();
                    break;
                case 3:
                    addAuthor();
                    break;
                case 4:
                    modifyAuthor();
                    break;
                case 5:
                    deleteAuthor();
                    break;
                default:
                    throw new RuntimeException();
            }
        } while (true);
    }

    private static void searchAndPrintAuthor() {
        do {
            try {
                System.out.println(new LibraryPrint(library).printAuthor(inputLine(UIMsg.searchAuthorMsg())));
                pressEnter();
                return;
            } catch (IndexOutOfBoundsException e) {
                switch (inputChoiceRange(UIMsg.objectNotFoundAndAddMsg("Author"), 0, 2)) {
                    case 0:
                        return;
                    case 1:
                        break;
                    case 2:
                        addAuthor();
                        return;
                    default:
                        throw new RuntimeException();
                }
            }
        } while (true);
    }

    private static Author searchAuthor(String action) {
        do {
            try {
                return library.getAuthor(inputLine(UIMsg.findMsg("Name", "Author", action)));

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
        library.addAuthor(new Author("theodosis", new GregorianCalendar(1998, 4, 15).toZonedDateTime(), "one of the best"));
        library.addAuthor(new Author("niki", new GregorianCalendar(1972, 0, 22).toZonedDateTime(), "one of the best"));
        library.addAuthor(new Author("rallis", new GregorianCalendar(1964, 1, 27).toZonedDateTime(), "one of the best"));
        library.addAuthor(new Author("panos", new GregorianCalendar(1998, 8, 17).toZonedDateTime(), "one of the best"));
        library.addAuthor(new Author("nikos", new GregorianCalendar(1998, 3, 6).toZonedDateTime(), "one of the best"));
        library.addAuthor(new Author("george", new GregorianCalendar(1998, 3, 27).toZonedDateTime(), "one of the best"));

        library.addDocument(new Book("1001", "book", 2000, 100, 100,
                "someone", "0001", new ArrayList<>(Arrays.asList(library.getAuthor("panos"), library.getAuthor("nikos")))));
        library.addDocument(new Journal("1002", "journal", 2000, 100, 100,
                "someone", "0001", 1, 1));
        library.addDocument(new BachelorThesis("1003", "bachelor", 2000, 100, 100,
                library.getAuthor("theodosis"), "somebody", "csd", "auth"));
        library.addDocument(new MasterThesis("1004", "master", 2000, 100, 100,
                library.getAuthor("niki"), "somebody", "csd", "auth"));
        library.addDocument(new DoctoralThesis("1005", "doctoral", 2000, 100, 100,
                library.getAuthor("rallis"), "somebody", "csd", "auth"));

    }


}
