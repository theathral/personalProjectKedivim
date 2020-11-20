package utils;

import java.util.ArrayList;

public class UIMsg {

    static String inputMsg(String type, String category) {
        return "Enter the " + type + " of the " + category + ": ";
    }

    static String findMsg(String type, String category, String action) {
        return "Enter the " + type + " of the " + category + " that you want to " + action + ": ";
    }

    static String wrongMsg() {
        return "Something went wrong. Try again...\n";
    }

    static String wrongCreationMsg(String type) {
        return "No new" + type + " has been created! Try again...\n";
    }

    static String welcomeMsg() {
        return "Welcome to the virtual library!\n"
                + "In the virtual library you can store all the documents of the actual library.\n"
                + "Also, you can add new ones, remove destroyed or observe and change their details.\n"
                + "Furthermore, the database stores all the authors of the documents.\n"
                + "\n\n";
    }

    static String mainMenu() {
        return "What do you want to do? (Enter a number)\n"
                + "1 -> Load Database\n"
                + "2 -> Save to Database\n"
                + "3 -> Documents\n"
                + "4 -> Authors\n"
                + "5 -> Statistics\n"
                + "0 -> EXIT PROGRAM\n";
    }

    static String exitMsg() {
        return "Are you sure you want to exit (Enter a number)?\n"
                + "0 -> Cancel\n"
                + "1 -> Yes\n";
    }

    static String saveToDBBeforeExitMsg() {
        return "Do want to save the database to file (Enter a number)?\n"
                + "0 -> No\n"
                + "1 -> Yes\n";
    }

    static String loadDBMsg() {
        return "Do you want to load the default file (Enter a number)?\n"
                + "0 -> Cancel\n"
                + "1 -> Default file\n"
                + "2 -> Custom file\n";
    }

    static String loadCustomDBMsg() {
        return "Give the path of the file you want to load: ";
    }

    static String wrongFileMsg() {
        return "Something went wrong or there is no such file. Try again...\n";
    }

    static String libraryNotEmptyMsg() {
        return "The library is not empty. You cannot proceed this action!\n";
    }

    static String overrideDBMsg() {
        return "There is another database file. Do you want to override it (Enter a number)?\n"
                + "0 -> Cancel\n"
                + "1 -> Override file\n";
    }

    static String documentsMenu() {
        return "Actions to documents (Enter a number):\n"
                + "1 -> Print All\n"
                + "2 -> Search Document\n"
                + "3 -> Add Document\n"
                + "4 -> Modify Document\n"
                + "5 -> Delete Document\n"
                + "0 -> Back to Main Menu\n";
    }

    static String searchDocumentMsg() {
        return "Search Document by...\n"
                + "1 -> Code\n"
                + "2 -> Title\n"
                + "0 -> Cancel\n";
    }

    static String searchDocByCodeMsg() {
        return "Enter the code of the document: ";
    }

    static String searchDocByTitleMsg() {
        return "Enter title (or part of it) of the document: ";
    }

    static String authorsMenu() {
        return "Actions to authors (Enter a number):\n"
                + "1 -> Print All\n"
                + "2 -> Search Author\n"
                + "3 -> Add Author\n"
                + "4 -> Modify Author\n"
                + "5 -> Delete Author\n"
                + "0 -> Back to Main Menu\n";
    }

    static String searchAuthorMsg() {
        return "Enter the full name (or part of it) of the author: ";
    }

    static String typesListMsg(ArrayList<String> types) {
        StringBuilder str = new StringBuilder();

        str.append("What kind of document do you want to add (Enter a number)?\n");
        for (int i = 0; i < types.size(); i++) {
            str.append(i).append(" -> ").append(types.get(i)).append("\n");
        }
        str.append("0 -> Cancel");

        return str.toString();
    }

    static String addMoreAuthors() {
        return "Do you want to add another Author (Enter a number)?\n"
                + "1 -> Add more\n"
                + "2 -> No, continue with the other attributes\n"
                + "0 -> Cancel\n";
    }

    static String objectFoundMsg(String type) {
        return "This " + type + " already exists. Do you want to try another one (Enter a number)?\n"
                + "1 -> Try something else\n"
                + "0 -> Cancel\n";
    }

    static String objectNotFoundMsg(String type) {
        return "This " + type + " does not exist. Do you want to try another one (Enter a number)?\n"
                + "1 -> Try something else\n"
                + "2 -> Add a new " + type + "\n"
                + "0 -> Cancel\n";
    }

    static String newObjectCreatedMsg(Object obj, String type) {
        return "The new " + type + " with the following details has been created:\n"
                + obj.toString()
                + "Do you want to add it to the list (Enter a number)?\n"
                + "1 -> Add to List\n"
                + "0 -> Cancel\n";
    }

    static String objectDeletionMsg(Object obj, String type) {
        return "The " + type + " with the following details:\n"
                + obj.toString()
                + "is about to be deleted. Do you want to remove it from the list (Enter a number)?\n"
                + "1 -> Delete from List\n"
                + "0 -> Cancel\n";
    }


}
