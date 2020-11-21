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
import java.time.ZonedDateTime;
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
                    break;
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
        String code, title, publisher, isbn, supervisor, department, university;
        int year, numOfPages, numOfCopies, volume, issue;
        Author author = null;
        ArrayList<Author> authorList = new ArrayList<>();

        int choice = inputChoiceRange(UIMsg.typesListMsg(typeKeys), 0, typeKeys.size());
        String choiceStr = typeKeys.get(choice);

        do {
            try {
                code = inputLine(UIMsg.inputMsg("Code", choiceStr));

                if (library.findDocumentByCode(code) == -1)
                    break;

                if (!inputChoiceBoolean(UIMsg.objectFoundMsg("Document")))
                    return;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);

        title = inputLine(UIMsg.inputMsg("Title", choiceStr));
        year = inputChoiceRange(UIMsg.inputMsg("Year", choiceStr), 1500, Year.now().getValue());
        numOfPages = inputChoiceMin(UIMsg.inputMsg("Number of Pages", choiceStr), 1);
        numOfCopies = inputChoiceMin(UIMsg.inputMsg("Number of Copies", choiceStr), 0);

        switch (choiceStr) {
            case "Book" -> {
                publisher = inputLine(UIMsg.inputMsg("Publisher", choiceStr));
                isbn = inputLine(UIMsg.inputMsg("ISBN", choiceStr));

                int curAuthors = 0;
                do {
                    do {
                        try {
                            String name = inputLine(UIMsg.inputMsg("Name", choiceStr));

                            int index = library.findAuthor(name);
                            if (index != -1) {
                                authorList.add(library.getAuthor(index));
                                break;
                            }

                            int newAuthorChoice = inputChoiceRange(UIMsg.objectNotFoundAndAddMsg("Author"), 0, 2);
                            if (newAuthorChoice == 0)
                                return;
                            else if (newAuthorChoice == 1)
                                break;
                            else if (newAuthorChoice == 2) {
                                int authorSize = library.getAuthors().size();
                                addAuthor();
                                if (library.getAuthors().size() == authorSize + 1) {
                                    authorList.add(library.getAuthor(library.getAuthors().size() - 1));
                                    break;
                                } else
                                    System.out.println(UIMsg.wrongCreationMsg("Author"));
                            } else
                                throw new RuntimeException();
                        } catch (IllegalArgumentException ignored) {
                        }
                    } while (true);

                    curAuthors++;
                    if (curAuthors >= 5)
                        break;

                    int addMoreAuthorsChoice = inputChoiceRange(UIMsg.addMoreAuthors(), 0, 2);
                    if (addMoreAuthorsChoice == 0)
                        return;
                    else if (addMoreAuthorsChoice == 2)
                        break;
                    else if (addMoreAuthorsChoice != 1)
                        throw new RuntimeException();
                } while (true);

                newDoc = (new Book(title, year, numOfPages, numOfCopies, code, publisher, isbn, authorList));
            }
            case "Journal" -> {
                publisher = inputLine(UIMsg.inputMsg("Publisher", choiceStr));
                isbn = inputLine(UIMsg.inputMsg("ISBN", choiceStr));
                volume = inputChoiceMin(UIMsg.inputMsg("Volume", choiceStr), 1);
                issue = inputChoiceMin(UIMsg.inputMsg("Issue", choiceStr), 0);
                newDoc = new Journal(title, year, numOfPages, numOfCopies, code, publisher, isbn, volume, issue);
            }
            case "Bachelor Thesis", "Master Thesis", "Doctoral Thesis" -> {
                supervisor = inputLine(UIMsg.inputMsg("Supervisor", choiceStr));
                department = inputLine(UIMsg.inputMsg("Department", choiceStr));
                university = inputLine(UIMsg.inputMsg("University", choiceStr));

                do {
                    try {
                        String name = inputLine(UIMsg.inputMsg("Name", choiceStr));

                        int index = library.findAuthor(name);
                        if (index != -1) {
                            author = library.getAuthor(index);
                            break;
                        }

                        int newAuthorChoice = inputChoiceRange(UIMsg.objectNotFoundAndAddMsg("Author"), 0, 2);
                        if (newAuthorChoice == 0)
                            return;
                        else if (newAuthorChoice == 1)
                            break;
                        else if (newAuthorChoice == 2) {
                            int authorSize = library.getAuthors().size();
                            addAuthor();
                            if (library.getAuthors().size() == authorSize + 1) {
                                author = library.getAuthor(library.getAuthors().size() - 1);
                                break;
                            } else
                                System.out.println(UIMsg.wrongCreationMsg("Author"));
                        } else
                            throw new RuntimeException();
                    } catch (IllegalArgumentException ignored) {
                    }
                } while (true);

                newDoc = switch (choiceStr) {
                    case "Bachelor Thesis" -> (new BachelorThesis(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university));
                    case "Master Thesis" -> (new MasterThesis(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university));
                    case "Doctoral Thesis" -> (new DoctoralThesis(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university));
                    default -> newDoc;
                };
            }
            default -> throw new RuntimeException();
        }

        if (inputChoiceBoolean(UIMsg.newObjectCreatedMsg(newDoc, "Document")))
            library.addDocument(newDoc);
    }

    private static void modifyDocument() {
    }

    private static void deleteDocument() {
        Document delDoc;

        do {
            try {
                delDoc = library.getDocument(inputLine(UIMsg.findMsg("Code", "Document", "delete")));
                break;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);

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
                    break;
                case 2:
                    searchAuthor();
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

    private static void searchAuthor() {
        do {
            try {
                System.out.println(new LibraryPrint(library).printAuthor(inputLine(UIMsg.searchAuthorMsg())));
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

    private static void addAuthor() {
        String name;
        do {
            try {
                name = inputLine(UIMsg.inputMsg("Name", "Author"));
                if (library.findAuthor(name) == -1)
                    break;

                if (!inputChoiceBoolean(UIMsg.objectFoundMsg("Author")))
                    return;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);

        ZonedDateTime dateOfBirth = inputDate(UIMsg.inputMsg("Date of Birth (d-m-Y)", "Author"));
        String description = inputLine(UIMsg.inputMsg("Description", "Author"));

        Author newAuthor = new Author(name, dateOfBirth, description);
        if (inputChoiceBoolean(UIMsg.newObjectCreatedMsg(newAuthor, "Author")))
            library.addAuthor(newAuthor);
    }

    private static void modifyAuthor() {
    }

    private static void deleteAuthor() {
        Author delAuthor;

        do {
            try {
                delAuthor = library.getAuthor(inputLine(UIMsg.findMsg("Name", "Author", "delete")));
                break;
            } catch (IndexOutOfBoundsException e) {
                if (!inputChoiceBoolean(UIMsg.objectNotFoundMsg("Author")))
                    return;
            } catch (IllegalArgumentException ignored) {
            }
        } while (true);

        if (inputChoiceBoolean(UIMsg.objectDeletionMsg(delAuthor, "Author")))
            library.deleteAuthor(delAuthor.getName());
    }

}
