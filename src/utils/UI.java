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

    /**
     * Main function for the start of the program.
     * It prints the welcome message and starts a loop with all the accepted commands.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println(UIMsg.welcomeMsg());
        pressEnter();

        dummyData();

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

    /**
     * It incites the user to press "Enter" button in order to continue the execution of the program.
     */
    private static void pressEnter() {
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    /**
     * It prints a message at the cmd and waits for an integer input
     * from the user [{@code min}, {@code max}] until the input is correct and returns it.
     *
     * @param msg The message to be printed
     * @param min The minimum value that is acceptable
     * @param max The maximum value that is acceptable
     * @return The user's input
     */
    private static int inputChoiceRange(String msg, int min, int max) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkRange(Integer.parseInt(scanner.nextLine()), min, max);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    /**
     * It prints a message at the cmd and waits for an integer input
     * from the user [1 for {@code true}, 0 for {@code false}] until the input is correct and returns it.
     *
     * @param msg The message to be printed
     * @return The user's input
     */
    private static boolean inputChoiceBoolean(String msg) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkRange(Integer.parseInt(scanner.nextLine()), 0, 1) == 1;
            } catch (Exception ignored) {
            }
        } while (true);
    }

    /**
     * It prints a message at the cmd and waits for an integer input
     * from the user [{@code min}, {@code infinity}] until the input is correct and returns it.
     *
     * @param msg The message to be printed
     * @param min The minimum value that is acceptable
     * @return The user's input
     */
    private static int inputChoiceMin(String msg, int min) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkMin(Integer.parseInt(scanner.nextLine()), min);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    /**
     * It prints a message at the cmd and waits for a text input
     * from the user until the input is not empty and returns it.
     *
     * @param msg The message to be printed
     * @return The user's input
     */
    private static String inputLine(String msg) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkString(scanner.nextLine());
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    /**
     * It prints a message at the cmd and waits for a date input
     * from the user until the input dd-mm-YYYY has given and returns a {@code ZonedDateTime} instance.
     *
     * @param msg The message to be printed
     * @return The user's input
     */
    private static ZonedDateTime inputDate(String msg) {
        do {
            try {
                return MyUtilities.convertToDate(inputLine(msg));
            } catch (Exception ignored) {
            }
        } while (true);
    }

    /**
     * Confirmation message if somebody wants to exit the program.
     * If answer is yes, the user also is asked if he wants to save the library to a binary file.
     *
     * @return {@code True}, if the user is about to exit. {@code False}, otherwise.
     */
    private static boolean exit() {
        if (inputChoiceBoolean(UIMsg.exitMsg())) {
            saveToDBBeforeExit();
            return true;
        }
        return false;
    }

    /**
     * Confirmation message if somebody wants to save the library to a binary file
     * before he exits the program.
     * If answer is yes, the library is saved to a binary file.
     */
    private static void saveToDBBeforeExit() {
        if (inputChoiceBoolean(UIMsg.saveToDBMsg("database")))
            saveToDB();
    }

    /**
     * It loads a library database from a file to the program.
     * User is asked if he wants to load the default name file or a custom one.
     */
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

    /**
     * It saves a library database to a binary file at the system.
     * If the file already exists, user is asked if he wants to override it.
     */
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

    /**
     * It prints a search query to the cmd and based on the answer of the user,
     * the program saves (or not) the text of the search to a .txt file at the system.
     */
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


    /**
     * Document function for the actions to the documents.
     * It starts a loop with all the accepted commands.
     */
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

    /**
     * It prints the accepted ways to search for a document
     * and based on the answers of the user, it prints the results.
     */
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

    /**
     * It searches for a document and returns it.
     * If the document does not exist, it asks if the user wants to do another search.
     *
     * @param action The action that is going to be executed on this document.
     * @return The document that has been found
     */
    private static DocInterface searchDocument(String action) {
        do {
            try {
                return library.getDocument(inputLine(UIMsg.actionMsg("Code", "Document", action)));

            } catch (IndexOutOfBoundsException e) {
                if (!inputChoiceBoolean(UIMsg.objectNotFoundMsg("Document")))
                    return null;
            }
        } while (true);
    }

    /**
     * It searches for an document and returns its code.
     * If the document does not exist, it asks if the user wants to do another search.
     *
     * @return The code of the document that has been found
     */
    private static String searchDocumentCode(String typeDoc) {
        do {
            try {
                String code = inputLine(UIMsg.inputMsg("Code", typeDoc));

                if (library.documentExists(code))
                    return code;

                if (!inputChoiceBoolean(UIMsg.objectFoundMsg("Document")))
                    return null;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    /**
     * It adds a new document to the program.
     * The user gives the title, year, number of pages and number of copies of the document.
     * Then, based on the type of the document, the program asks also for the additional characteristics of the document.
     * If the document already exists (based on the code), it asks for a new code or aborts the addition.
     */
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

    /**
     * It adds the authors of a new document to the program (maximum 5 authors).
     * The user gives the name of the authors that wants to be added.
     * If the author does not exist (based on the name), it asks for a new name or aborts the addition.
     */
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

    /**
     * It modifies an existing document of the program.
     * The user gives the code of the document, the attribute that wants to modify and the new value.
     * If the document does not exist (based on the code), it asks for a new code or aborts the modification.
     */
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

    /**
     * Creates the list of properties that the user can modify, based on the type the document.
     *
     * @param modDocClass Type of the document
     * @return The list of properties
     */
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

    /**
     * It modifies the authors' list of an existing Book document of the program.
     * The user gives the action (add or delete), the name of the author that wants to add or delete.
     * If the authors are 5, the user cannot add another one.
     * If the author is 1, the user cannot delete the author.
     */
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

    /**
     * It modifies an existing Document of the program.
     * The user gives the attribute that wants to modify and the new value.
     *
     * @param document The document that is about to be modified
     * @param attr     The attribute of the document that is about to be modified
     * @param type     Type of the Document
     */
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

    /**
     * It modifies an existing Paper document of the program.
     * The user gives the attribute that wants to modify and the new value.
     *
     * @param document The document that is about to be modified
     * @param attr     The attribute of the document that is about to be modified
     * @param type     Type of the Paper document
     */
    private static void modifyPaperClass(Paper document, String attr, String type) {
        switch (attr) {
            case "Publisher" -> document.setPublisher(inputLine(UIMsg.inputMsg("new Publisher", type)));
            case "ISBN" -> document.setISBN(inputLine(UIMsg.inputMsg("new ISBN", type)));

            default -> modifyDocumentClass(document, attr, type);
        }
    }

    /**
     * It modifies an existing Book document of the program.
     * The user gives the attribute that wants to modify and the new value.
     *
     * @param document The document that is about to be modified
     * @param attr     The attribute of the document that is about to be modified
     */
    private static void modifyBookClass(Book document, String attr) {
        if (attr.equals("Authors"))
            modifyAuthorDocument(document);
        else
            modifyPaperClass(document, attr, "Book");
    }

    /**
     * It modifies an existing Journal document of the program.
     * The user gives the attribute that wants to modify and the new value.
     *
     * @param document The document that is about to be modified
     * @param attr     The attribute of the document that is about to be modified
     */
    private static void modifyJournalClass(Journal document, String attr) {
        switch (attr) {
            case "Volume" -> document.setVolume(inputChoiceMin(UIMsg.inputMsg("new Volume", "Journal"), 1));
            case "Issue" -> document.setVolume(inputChoiceMin(UIMsg.inputMsg("new Volume", "Journal"), 0));

            default -> modifyPaperClass(document, attr, "Journal");
        }
    }

    /**
     * It modifies an existing Thesis document of the program.
     * The user gives the attribute that wants to modify and the new value.
     *
     * @param document The document that is about to be modified
     * @param attr     The attribute of the document that is about to be modified
     * @param type     Type of the Thesis document
     */
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

    /**
     * It deletes an existing document from the program.
     * The user gives the code of the document and confirms the deletion.
     * If the document does not exist (based on the code), it asks for a new code or aborts the deletion.
     */
    private static void deleteDocument() {
        DocInterface delDoc = searchDocument("delete");
        if (delDoc == null)
            return;

        if (inputChoiceBoolean(UIMsg.objectDeletionMsg(delDoc, "Document")))
            library.deleteDocument(delDoc.getCode());
    }

    /**
     * It deletes all the existing documents from the program.
     * The user confirms the deletion.
     */
    private static void deleteAllDocuments() {
        if (inputChoiceBoolean(UIMsg.objectDeletionMsg("ALL DOCUMENTS" + System.lineSeparator(), "Document")))
            library.deleteAllDocuments();
    }


    /**
     * Author function for the actions to the authors.
     * It starts a loop with all the accepted commands.
     */
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

    /**
     * It prints the accepted ways to search for an author
     * and based on the answers of the user, it prints the results.
     */
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

    /**
     * It searches for an author and returns it.
     * If the author does not exist, it asks if the user wants to do another search.
     *
     * @param action The action that is going to be executed on this author.
     * @return The author that has been found
     */
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

    /**
     * It searches for an author and returns its name.
     * If the author does not exist, it asks if the user wants to do another search.
     *
     * @return The name of the author that has been found
     */
    private static String searchNameAuthor() {
        do {
            try {
                String name = inputLine(UIMsg.inputMsg("Name", "Author"));
                if (library.authorExists(name))
                    return name;

                if (!inputChoiceBoolean(UIMsg.objectFoundMsg("Author")))
                    return null;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);
    }

    /**
     * It adds a new author to the program.
     * The user gives the name, date of birth and the description of the author.
     * If the author already exists (based on the name), it asks for a new name or aborts the addition.
     */
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

    /**
     * It modifies an existing author of the program.
     * The user gives the name of the author, the attribute that wants to modify and the new value.
     * If the author does not exist (based on the name), it asks for a new name or aborts the modification.
     */
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

    /**
     * It deletes an existing author from the program.
     * The user gives the name of the author and confirms the deletion.
     * If the author does not exist (based on the name), it asks for a new name or aborts the deletion.
     * If the author exists in at least one document, the action is aborted.
     */
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


    /**
     * Dummy data for the library
     */
    private static void dummyData() {
        if (!inputChoiceBoolean(UIMsg.dummyData()))
            return;

        library.addAuthor(new Author("Theodosis", new GregorianCalendar(1998, Calendar.MAY, 15).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Niki", new GregorianCalendar(2000, Calendar.JANUARY, 22).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Rallis", new GregorianCalendar(2001, Calendar.FEBRUARY, 27).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Panos", new GregorianCalendar(1998, Calendar.SEPTEMBER, 17).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("Nikos", new GregorianCalendar(1998, Calendar.APRIL, 6).toZonedDateTime(), "One of the best"));
        library.addAuthor(new Author("George", new GregorianCalendar(1998, Calendar.APRIL, 27).toZonedDateTime(), "One of the best"));

        library.addDocument(new Book("1001", "Book", 1998, 100, 100,
                "Someone", "0001", new ArrayList<>(Arrays.asList(library.getAuthor("Panos"), library.getAuthor("Nikos")))));
        library.addDocument(new Journal("1002", "Journal", 2000, 100, 100,
                "Someone", "0001", 1, 1));
        library.addDocument(new BachelorThesis("1003", "Bachelor", 1998, 100, 100,
                library.getAuthor("Theodosis"), "somebody", "Theodosius", "AUTH"));
        library.addDocument(new MasterThesis("1004", "Master", 1972, 100, 100,
                library.getAuthor("Niki"), "Somebody", "Theodosius", "AUTH"));
        library.addDocument(new DoctoralThesis("1005", "Doctoral", 1964, 100, 100,
                library.getAuthor("Rallis"), "Somebody", "CSD", "AUTH"));

    }
}
