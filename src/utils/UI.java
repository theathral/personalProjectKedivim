package utils;

import author.Author;
import document.Book;
import document.Document;
import document.Journal;
import document.thesis.BachelorThesis;
import document.thesis.DoctoralThesis;
import document.thesis.MasterThesis;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private static final String PATH = "libraryDB.txt";

    private static Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(UIMsg.welcomeMsg());

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
                    break;
                default:
                    throw new RuntimeException();
            }
        } while (true);

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

    private static int inputChoiceMin(String msg, int min) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkMin(Integer.parseInt(scanner.nextLine()), min);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static int inputChoiceMax(String msg, int max) {
        do {
            try {
                System.out.print(msg);
                return MyUtilities.checkMax(Integer.parseInt(scanner.nextLine()), max);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static String inputLine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static boolean exit() {
        switch (inputChoiceRange(UIMsg.exitMsg(), 0, 1)) {
            case 0:
                return false;
            case 1:
                saveToDBBeforeExit();
                return true;
            default:
                throw new RuntimeException();
        }
    }

    private static void saveToDBBeforeExit() {
        switch (inputChoiceRange(UIMsg.saveToDBBeforeExitMsg(), 0, 1)) {
            case 0:
                return;
            case 1:
                saveToDB();
                return;
            default:
                throw new RuntimeException();
        }
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
            } catch (IllegalArgumentException e) {
                System.out.println(UIMsg.libraryNotEmptyMsg());
                return;
            }
        } while (true);
    }

    private static void saveToDB() {
        do {
            try {
                if (new File(PATH).isFile()) {
                    switch (inputChoiceRange(UIMsg.overrideDBMsg(), 0, 1)) {
                        case 0:
                            return;
                        case 1:
                            library.writeToBinaryFile(PATH);
                            return;
                        default:
                            throw new RuntimeException();
                    }
                }
            } catch (IOException e) {
                System.out.println(UIMsg.wrongMsg());
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
                case 2:
                    searchDocument();
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

    private static void searchDocument() {
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

    }


    private static void addDocument() {
        ArrayList<String> typeKeys = new ArrayList<>(library.getTypeOfDocuments().keySet());
        Document newDoc = null;
        int choice = inputChoiceRange(UIMsg.typesListMsg(typeKeys), 0, typeKeys.size());

//        do {
        String code = inputLine(UIMsg.inputMsg("Code", typeKeys.get(choice)));
//            if ()
//        } while (true);

        String title = inputLine(UIMsg.inputMsg("Title", typeKeys.get(choice)));
        int year = inputChoiceRange(UIMsg.inputMsg("Year", typeKeys.get(choice)), 1500, Year.now().getValue());
        int numOfPages = inputChoiceMin(UIMsg.inputMsg("Number of Pages", typeKeys.get(choice)), 1);
        int numOfCopies = inputChoiceMin(UIMsg.inputMsg("Number of Copies", typeKeys.get(choice)), 0);
        String publisher;
        String isbn;
        Author author = new Author(null, null, null, null, null); //////////////////////////////////////////////////////////
        int volume;
        int issue;
        String supervisor;
        String department;
        String university;
        String choiceStr = typeKeys.get(choice);

        switch (choiceStr) {
            case "Book":
                publisher = inputLine(UIMsg.inputMsg("Publisher", choiceStr));
                isbn = inputLine(UIMsg.inputMsg("ISBN", choiceStr));
//                author;
                newDoc = (new Book(title, year, numOfPages, numOfCopies, code, publisher, isbn, author));
                break;
            case "Journal":
                publisher = inputLine(UIMsg.inputMsg("Publisher", choiceStr));
                isbn = inputLine(UIMsg.inputMsg("ISBN", choiceStr));
                volume = inputChoiceMin(UIMsg.inputMsg("Volume", choiceStr), 1);
                issue = inputChoiceMin(UIMsg.inputMsg("Issue", choiceStr), 0);
                newDoc = new Journal(title, year, numOfPages, numOfCopies, code, publisher, isbn, volume, issue);
                break;
            case "Bachelor Thesis":
            case "Master Thesis":
            case "Doctoral Thesis":
//                author;
                supervisor = inputLine(UIMsg.inputMsg("Supervisor", choiceStr));
                department = inputLine(UIMsg.inputMsg("Department", choiceStr));
                university = inputLine(UIMsg.inputMsg("University", choiceStr));
                switch (choiceStr) {
                    case "Bachelor Thesis":
                        newDoc = (new BachelorThesis(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university));
                        break;
                    case "Master Thesis":
                        newDoc = (new MasterThesis(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university));
                        break;
                    case "Doctoral Thesis":
                        newDoc = (new DoctoralThesis(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university));
                        break;
                }
                break;
            default:
                throw new RuntimeException();
        }

        if (inputChoiceRange(UIMsg.newDocumentCreatedMsg(newDoc), 0, 1) == 1)
            library.addDocument(newDoc);
    }

    private static void modifyDocument() {
    }

    private static void deleteDocument() {
    }


    private static void authors() {
        do {
            switch (inputChoiceRange(UIMsg.authorsMenu(), 0, 5)) {
                case 0:
                    return;
                case 1:
                    System.out.println(new LibraryPrint(library).printAuthors());
                case 2:
                    System.out.println(new LibraryPrint(library).printAuthor(inputLine(UIMsg.searchAuthorMsg())));
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


    private static void addAuthor() {
    }

    private static void modifyAuthor() {
    }

    private static void deleteAuthor() {

    }

}
