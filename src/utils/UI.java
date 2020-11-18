package utils;

import java.io.File;
import java.util.Scanner;

public class UI {

    private static final String PATH = "libraryDB.txt";

    private static final Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(welcomeMsg());

        do {
            switch (inputChoice(mainMenu(), 0, 5)) {
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
                    stats();
                    break;
                default:
                    throw new RuntimeException();
            }
        } while (true);

    }


    private static int inputChoice(String msg, int min, int max) {
        do {
            try {
                System.out.println(msg);
                return MyUtilities.checkRange(Integer.parseInt(scanner.nextLine()), min, max);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    private static String inputLine(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    private static String welcomeMsg() {
        return "Welcome to the virtual library!\n"
                + "In the virtual library you can store all the documents of the actual library.\n"
                + "Also, you can add new ones, remove destroyed or observe and change their details.\n"
                + "Furthermore, the database stores all the authors of the documents.\n"
                + "\n\n";
    }

    private static String mainMenu() {
        return "What do you want to do? (Enter a number)\n"
                + "1 -> Load Database\n"
                + "2 -> Save to Database\n"
                + "3 -> Documents\n"
                + "4 -> Authors\n"
                + "5 -> Statistics\n"
                + "0 -> EXIT PROGRAM\n";
    }

    private static boolean exit() {
        switch (inputChoice(exitMsg(), 0, 1)) {
            case 0:
                return false;
            case 1:
                saveToDBBeforeExit();
                return true;
            default:
                throw new RuntimeException();
        }
    }

    private static String exitMsg() {
        return "Are you sure you want to exit (Enter a number)?\n"
                + "0 -> Cancel\n"
                + "1 -> Yes\n";
    }

    private static void saveToDBBeforeExit() {
        switch (inputChoice(saveToDBBeforeExitMsg(), 0, 1)) {
            case 0:
                return;
            case 1:
                saveToDB();
                return;
            default:
                throw new RuntimeException();
        }
    }

    private static String saveToDBBeforeExitMsg() {
        return "Do want to save the database to file (Enter a number)?"
                + "0 -> No\n"
                + "1 -> Yes\n";
    }

    private static void loadDB() {
        do {
//            try {
            switch (inputChoice(loadDBMsg(), 0, 2)) {
                case 0:
                    return;
                case 1:
                    library.loadFile(PATH);
                    return;
                case 2:
                    library.loadFile(inputLine(loadCustomDBMsg()));
                    return;
                default:
                    throw new RuntimeException();
            }
//            } catch (IOException e) {
//                System.out.println(wrongFileMsg());
//            }
        } while (true);
    }

    private static String loadDBMsg() {
        return "Do you want to load the default file (Enter a number)?\n"
                + "0 -> Cancel\n"
                + "1 -> Default file\n"
                + "2 -> Custom file\n";
    }


    private static String loadCustomDBMsg() {
        return "Give the path of the file you want to load: ";
    }

    private static String wrongFileMsg() {
        return "Something went wrong or there is no such file. Try again...\n";
    }

    private static void saveToDB() {
        if (new File(PATH).isFile()) {
            switch (inputChoice(overrideDBMsg(), 0, 1)) {
                case 0:
                    return;
                case 1:
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        library.saveToFile(PATH);
    }

    private static String overrideDBMsg() {
        return "There is another database file. Do you want to override it?\n"
                + "0 -> Cancel\n"
                + "1 -> Override file\n";
    }

    private static void documents() {
        do {
            switch (inputChoice(documentsMenu(), 0, 5)) {
                case 0:
                    return;
                case 1:
                    System.out.println(new LibraryPrint(library).printDocumentWithCode(inputLine(searchDocByCode())));
                    break;
                case 2:
                    System.out.println(new LibraryPrint(library).printDocumentWithTitle(inputLine(searchDocByTitle())));
                    break;
                case 3:
                    documents();
                    break;
                case 4:
                    authors();
                    break;
                case 5:
                    stats();
                    break;
                default:
                    throw new RuntimeException();
            }
        } while (true);
    }

    private static String documentsMenu() {
        return "Actions to documents (Enter a number):\n"
                + "1 -> Print All\n"
                + "2 -> Search Document\n"
                + "3 -> Add Document\n"
                + "4 -> Modify Document\n"
                + "5 -> Delete Document\n"
                + "0 -> Back to Main Menu\n";
    }

    private static String searchDocByCode() {
        return "Enter the code of the document: ";
    }

    private static void searchDocument() {
        switch (inputChoice(searchDocumentMsg(), 0, 2)) {
            case 0:
                return;
            case 1:
                System.out.println(new LibraryPrint(library).printDocuments());
                break;
            case 2:
                break;
            default:
                throw new RuntimeException();
        }

    }

    private static String searchDocumentMsg() {
        return "Search Document by...\n"
                + "1 -> Code\n"
                + "2 -> Title\n"
                + "0 -> Cancel\n";
    }

    private static void authors() {
    }

    private static void stats() {
    }
}
