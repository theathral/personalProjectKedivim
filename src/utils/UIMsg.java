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
        return "Something went wrong. Try again..." + System.lineSeparator();
    }

    static String wrongCreationMsg(String type) {
        return "No new" + type + " has been created! Try again..." + System.lineSeparator();
    }

    static String welcomeMsg() {
        return "Welcome to the virtual library!" + System.lineSeparator()
                + "In the virtual library you can store all the documents of the actual library." + System.lineSeparator()
                + "Also, you can add new ones, remove destroyed or observe and change their details." + System.lineSeparator()
                + "Furthermore, the database stores all the authors of the documents." + System.lineSeparator()
                + System.lineSeparator() + System.lineSeparator();
    }

    static String mainMenu() {
        return "What do you want to do? (Enter a number)" + System.lineSeparator()
                + "1 -> Load Database" + System.lineSeparator()
                + "2 -> Save to Database" + System.lineSeparator()
                + "3 -> Documents" + System.lineSeparator()
                + "4 -> Authors" + System.lineSeparator()
                + "5 -> Statistics" + System.lineSeparator()
                + "0 -> EXIT PROGRAM" + System.lineSeparator();
    }

    static String exitMsg() {
        return "Are you sure you want to exit? (Enter a number)" + System.lineSeparator()
                + "1 -> Yes" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String saveToDBBeforeExitMsg() {
        return "Do want to save the database to file? (Enter a number)" + System.lineSeparator()
                + "1 -> Yes" + System.lineSeparator()
                + "0 -> No" + System.lineSeparator();
    }

    static String loadDBMsg() {
        return "Do you want to load the default file? (Enter a number)" + System.lineSeparator()
                + "1 -> Default file" + System.lineSeparator()
                + "2 -> Custom file" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String loadCustomDBMsg() {
        return "Give the path of the file you want to load: ";
    }

    static String wrongFileMsg() {
        return "Something went wrong or there is no such file. Try again..." + System.lineSeparator();
    }

    static String libraryNotEmptyMsg() {
        return "The library is not empty. You cannot proceed this action!" + System.lineSeparator();
    }

    static String overrideDBMsg() {
        return "There is another database file. Do you want to override it? (Enter a number)" + System.lineSeparator()
                + "1 -> Override file" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String documentsMenu() {
        return "Actions to documents (Enter a number):" + System.lineSeparator()
                + "1 -> Print All" + System.lineSeparator()
                + "2 -> Search Document" + System.lineSeparator()
                + "3 -> Add Document" + System.lineSeparator()
                + "4 -> Modify Document" + System.lineSeparator()
                + "5 -> Delete Document" + System.lineSeparator()
                + "0 -> Back to Main Menu" + System.lineSeparator();
    }

    static String searchDocumentMsg() {
        return "Search Document by..." + System.lineSeparator()
                + "1 -> Code" + System.lineSeparator()
                + "2 -> Title" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String searchDocByCodeMsg() {
        return "Enter the code of the document: ";
    }

    static String searchDocByTitleMsg() {
        return "Enter title (or part of it) of the document: ";
    }

    static String authorsMenu() {
        return "Actions to authors (Enter a number):" + System.lineSeparator()
                + "1 -> Print All" + System.lineSeparator()
                + "2 -> Search Author" + System.lineSeparator()
                + "3 -> Add Author" + System.lineSeparator()
                + "4 -> Modify Author" + System.lineSeparator()
                + "5 -> Delete Author" + System.lineSeparator()
                + "0 -> Back to Main Menu" + System.lineSeparator();
    }

    static String searchAuthorMsg() {
        return "Enter the full name of the author: ";
    }

    static String typesListMsg(ArrayList<String> types) {
        StringBuilder str = new StringBuilder();

        str.append("What kind of document do you want to add? (Enter a number)?").append(System.lineSeparator());
        for (int i = 0; i < types.size(); i++) {
            str.append(i).append(" -> ").append(types.get(i)).append("").append(System.lineSeparator());
        }
        str.append("0 -> Cancel");

        return str.toString();
    }

    static String addMoreAuthors() {
        return "Do you want to add another Author? (Enter a number)" + System.lineSeparator()
                + "1 -> Add more" + System.lineSeparator()
                + "2 -> No, continue with the other attributes" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String objectFoundMsg(String type) {
        return "This " + type + " already exists. Do you want to try another one? (Enter a number)" + System.lineSeparator()
                + "1 -> Try something else" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String objectNotFoundMsg(String type) {
        return "This " + type + " does not exist. Do you want to try another one? (Enter a number)" + System.lineSeparator()
                + "1 -> Try something else" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String objectNotFoundAndAddMsg(String type) {
        return "This " + type + " does not exist. Do you want to try another one? (Enter a number)" + System.lineSeparator()
                + "1 -> Try something else" + System.lineSeparator()
                + "2 -> Add a new " + type + "" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String newObjectCreatedMsg(Object obj, String type) {
        return "The new " + type + " with the following details has been created:" + System.lineSeparator()
                + obj.toString()
                + "Do you want to add it to the list? (Enter a number)" + System.lineSeparator()
                + "1 -> Add to List" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }

    static String objectDeletionMsg(Object obj, String type) {
        return "The " + type + " with the following details:" + System.lineSeparator()
                + obj.toString()
                + "is about to be deleted. Do you want to remove it from the list? (Enter a number)" + System.lineSeparator()
                + "1 -> Delete from List" + System.lineSeparator()
                + "0 -> Cancel" + System.lineSeparator();
    }


}
