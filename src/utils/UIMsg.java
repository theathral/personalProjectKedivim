package utils;

import java.util.ArrayList;

public class UIMsg {

    static String line() {
        return System.lineSeparator();
    }

    static String inputMsg(String type, String category) {
        return "Enter the " + type + " of the " + category + ": ";
    }

    static String actionMsg(String type, String category, String action) {
        return "Enter the " + type + " of the " + category + " that you want to " + action + ": ";
    }

    static String wrongMsg() {
        return "Something went wrong. Try again..." + line();
    }


    static String objectCannotActionMsg(String type, String action, String reason) {
        return "This " + type + " cannot be " + action + " because " + reason + "." + line()
                + "Do you want to try another one? (Enter a number)" + line()
                + "1 -> Try something else" + line()
                + "0 -> Cancel" + line();
    }

    static String objectFoundMsg(String type) {
        return "This " + type + " already exists. Do you want to try another one? (Enter a number)" + line()
                + "1 -> Try something else" + line()
                + "0 -> Cancel" + line();
    }

    static String objectNotFoundMsg(String type) {
        return "This " + type + " does not exist. Do you want to try another one? (Enter a number)" + line()
                + "1 -> Try something else" + line()
                + "0 -> Cancel" + line();
    }

    static String objectNotFoundAndAddMsg(String type, ArrayList<String> list) {
        return "This " + type + " does not exist. Do you want to try another one? (Enter a number)" + line()
                + objListMsg(list);
    }

    static String newObjectCreatedMsg(Object obj, String type) {
        return "The new " + type + " with the following details has been created:" + line()
                + obj.toString()
                + "Do you want to add it to the list? (Enter a number)" + line()
                + "1 -> Add to List" + line()
                + "0 -> Cancel" + line();
    }

    static String objectDeletionMsg(Object obj, String type) {
        return "The " + type + " with the following details:" + line()
                + obj.toString()
                + "is about to be deleted. Do you want to remove it from the list? (Enter a number)" + line()
                + "1 -> Delete from List" + line()
                + "0 -> Cancel" + line();
    }


    static String objListMsg(ArrayList<String> list) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < list.size() - 1; i++) {
            str.append(i + 1).append(" -> ").append(list.get(i)).append(line());
        }
        str.append("0 -> ").append(list.get(list.size() - 1)).append(line());

        return str.toString();
    }

    static String welcomeMsg() {
        return "Welcome to the virtual library!" + line()
                + "In the virtual library you can store all the documents of the actual library." + line()
                + "Also, you can add new ones, remove destroyed or observe and change their details." + line()
                + "Furthermore, the database stores all the authors of the documents." + line()
                + line() + line();
    }

    static String mainMenu(ArrayList<String> list) {
        return "What do you want to do? (Enter a number)" + line()
                + objListMsg(list);
    }

    static String subMenus(String type, ArrayList<String> list) {
        return "Actions to " + type + " (Enter a number):" + line()
                + objListMsg(list);
    }


    static String exitMsg() {
        return "Are you sure you want to exit? (Enter a number)" + line()
                + "1 -> Exit" + line()
                + "0 -> Cancel" + line();
    }

    static String saveToDBMsg(String type) {
        return "Do want to save the " + type + " to a file? (Enter a number)" + line()
                + "1 -> Save" + line()
                + "0 -> No, don't Save" + line();
    }

    static String loadDBMsg(ArrayList<String> list) {
        return "Do you want to load the default file? (Enter a number)" + line()
                + objListMsg(list);
    }

    static String wrongFileMsg() {
        return "Something went wrong or there is no such file. Try again..." + line();
    }

    static String libraryNotEmptyMsg() {
        return "The library is not empty. You cannot proceed this action!" + line();
    }

    static String overrideDBMsg() {
        return "There is another database file. Do you want to override it? (Enter a number)" + line()
                + "1 -> Override file" + line()
                + "0 -> Cancel" + line();
    }


    static String searchDocumentMsg(ArrayList<String> list) {
        return "Search Document by..." + line()
                + objListMsg(list);
    }

    static String modifyAuthorsDocument(ArrayList<String> list) {
        return "Actions to authors of the Book (Enter a number):" + line()
                + objListMsg(list);
    }

    static String typesListMsg(ArrayList<String> types) {
        return "What kind of document do you want to add? (Enter a number)" + line()
                + objListMsg(types);
    }

    static String addMoreAuthors(ArrayList<String> list) {
        return "Do you want to add another Author? (Enter a number)" + line()
                + objListMsg(list);
    }

    static String attributesListMsg(ArrayList<String> attributes) {
        return "Which attribute do you want to modify? (Enter a number)" + line()
                + objListMsg(attributes);
    }
}
