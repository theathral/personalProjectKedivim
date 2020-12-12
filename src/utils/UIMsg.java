package utils;

import java.util.ArrayList;

public class UIMsg {

    /**
     * System's line separator.
     *
     * @return The proper message
     */
    static String line() {
        return System.lineSeparator();
    }

    /**
     * The String command for typing a given {@code type} of request for a given {@code category}.
     *
     * @param type     The given {@code type}
     * @param category The given {@code category}
     * @return The proper message
     */
    static String inputMsg(String type, String category) {
        return "Enter the " + type + " of the " + category + ": ";
    }

    /**
     * The String command for typing a given {@code type} of request for a given {@code category}
     * and the action that will be performed to this.
     *
     * @param type     The given {@code type}
     * @param category The given {@code category}
     * @param action   The given {@code action}
     * @return The proper message
     */
    static String actionMsg(String type, String category, String action) {
        return "Enter the " + type + " of the " + category + " that you want to " + action + ": ";
    }

    /**
     * Error Message.
     *
     * @return The proper message
     */
    static String wrongMsg() {
        return "Something went wrong. Try again..." + line();
    }


    /**
     * The String command for error in a given {@code type} of request for a given {@code action}
     * and the reason for the error.
     * Also, provides with options for {@code Retry} and {@code Cancel}.
     *
     * @param type   The given {@code type}
     * @param action The given {@code action}
     * @param reason The given {@code reason}
     * @return The proper message
     */
    static String objectCannotActionMsg(String type, String action, String reason) {
        return "This " + type + " cannot be " + action + " because " + reason + "." + line()
                + "Do you want to try another one? (Enter a number)" + line()
                + "1 -> Try something else" + line()
                + "0 -> Cancel" + line();
    }

    /**
     * The String command for error that a given {@code type} of request has found.
     * Also, provides with options for {@code Retry} and {@code Cancel}
     *
     * @param type The given {@code type}
     * @return The proper message
     */
    static String objectFoundMsg(String type) {
        return "This " + type + " already exists. Do you want to try another one? (Enter a number)" + line()
                + "1 -> Try something else" + line()
                + "0 -> Cancel" + line();
    }

    /**
     * The String command for error that a given {@code type} of request has not benn found.
     * Also, provides with options for {@code Try something else} and {@code Cancel}
     *
     * @param type The given {@code type}
     * @return The proper message
     */
    static String objectNotFoundMsg(String type) {
        return "This " + type + " does not exist. Do you want to try another one? (Enter a number)" + line()
                + "1 -> Try something else" + line()
                + "0 -> Cancel" + line();
    }

    /**
     * The String command for error that a given {@code type} of request has not benn found.
     * Also, provides a set of option from a given {@code list}
     *
     * @param type The given {@code type}
     * @param list The given {@code list}
     * @return The proper message
     */
    static String objectNotFoundAndAddMsg(String type, ArrayList<String> list) {
        return "This " + type + " does not exist. Do you want to try another one? (Enter a number)" + line()
                + objListMsg(list);
    }

    /**
     * The String command for that a given {@code type} has been created,
     * as well as the String representation of the {@code obj}.
     * Also, provides the options for {@code Add to List} and {@code Cancel}.
     *
     * @param obj  The given {@code object}
     * @param type The given {@code type}
     * @return The proper message
     */
    static String newObjectCreatedMsg(Object obj, String type) {
        return "The new " + type + " with the following details has been created:" + line()
                + obj.toString()
                + "Do you want to add it to the list? (Enter a number)" + line()
                + "1 -> Add to List" + line()
                + "0 -> Cancel" + line();
    }

    /**
     * The String command for that a given {@code type} is about to be deleted,
     * as well as the String representation of the {@code obj}.
     * Also, provides the options for {@code Delete from List} and {@code Cancel}.
     *
     * @param obj  The given {@code object}
     * @param type The given {@code type}
     * @return The proper message
     */
    static String objectDeletionMsg(Object obj, String type) {
        return "The " + type + " with the following details:" + line()
                + obj.toString()
                + "is about to be deleted. Do you want to remove it from the list? (Enter a number)" + line()
                + "1 -> Delete from List" + line()
                + "0 -> Cancel" + line();
    }


    /**
     * The String for representing a given {@code list} of options to a standard form.
     *
     * @param list The given {@code list}
     * @return The proper message
     */
    static String objListMsg(ArrayList<String> list) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < list.size() - 1; i++) {
            str.append(i + 1).append(" -> ").append(list.get(i)).append(line());
        }
        str.append("0 -> ").append(list.get(list.size() - 1)).append(line());

        return str.toString();
    }

    /**
     * The String of the welcoming message.
     *
     * @return The proper message
     */
    static String welcomeMsg() {
        return "Welcome to the virtual library!" + line()
                + "In the virtual library you can store all the documents of the actual library." + line()
                + "Also, you can add new ones, remove destroyed or observe and change their details." + line()
                + "Furthermore, the database stores all the authors of the documents." + line()
                + line() + line();
    }

    /**
     * The String of the main menu and a {@code list} of given options.
     *
     * @param list The given {@code list}
     * @return The proper message
     */
    static String mainMenu(ArrayList<String> list) {
        return "What do you want to do? (Enter a number)" + line()
                + objListMsg(list);
    }

    /**
     * The String of the sub menus and a {@code list} of given options.
     *
     * @param list The given {@code list}
     * @return The proper message
     */
    static String subMenus(String type, ArrayList<String> list) {
        return "Actions to " + type + " (Enter a number):" + line()
                + objListMsg(list);
    }


    /**
     * The String command for confirm exit of program,
     * as well as options {@code Exit} and {@code Cancel}.
     *
     * @return The proper message
     */
    static String exitMsg() {
        return "Are you sure you want to exit? (Enter a number)" + line()
                + "1 -> Exit" + line()
                + "0 -> Cancel" + line();
    }

    /**
     * The String command for confirm save of {@code type} request,
     * as well as options {@code Save} and {@code No, don't Save}.
     *
     * @return The proper message
     */
    static String saveToDBMsg(String type) {
        return "Do want to save the " + type + " to a file? (Enter a number)" + line()
                + "1 -> Save" + line()
                + "0 -> No, don't Save" + line();
    }

    /**
     * The String command for loading a file,
     * as well as a {@code list} of options.
     *
     * @return The proper message
     */
    static String loadDBMsg(ArrayList<String> list) {
        return "Do you want to load the default file? (Enter a number)" + line()
                + objListMsg(list);
    }

    /**
     * Error message while file action.
     *
     * @return The proper message
     */
    static String wrongFileMsg() {
        return "Something went wrong or there is no such file. Try again..." + line();
    }

    /**
     * Error message while loading a file and the library is not empty.
     *
     * @return The proper message
     */
    static String libraryNotEmptyMsg() {
        return "The library is not empty. You cannot proceed this action!" + line();
    }

    /**
     * Error message for overriding the existing file while saving the Database to a file.
     *
     * @return The proper message
     */
    static String overrideDBMsg() {
        return "There is another database file. Do you want to override it? (Enter a number)" + line()
                + "1 -> Override file" + line()
                + "0 -> Cancel" + line();
    }


    /**
     * The String command for searching a document,
     * as well as a {@code list} of options.
     *
     * @return The proper message
     */
    static String searchDocumentMsg(ArrayList<String> list) {
        return "Search Document by..." + line()
                + objListMsg(list);
    }

    /**
     * The String command for modifying the authors of a {@code Book} instance,
     * as well as a {@code list} of options.
     *
     * @return The proper message
     */
    static String modifyAuthorsDocument(ArrayList<String> list) {
        return "Actions to authors of the Book (Enter a number):" + line()
                + objListMsg(list);
    }

    /**
     * The String command for choosing the type of document that will be added,
     * as well as a {@code list} of options.
     *
     * @return The proper message
     */
    static String typesListMsg(ArrayList<String> types) {
        return "What kind of document do you want to add? (Enter a number)" + line()
                + objListMsg(types);
    }

    /**
     * The String command for choosing whether an extra author will be added to the {@code Book} instance,
     * as well as a {@code list} of options.
     *
     * @return The proper message
     */
    static String addMoreAuthors(ArrayList<String> list) {
        return "Do you want to add another Author? (Enter a number)" + line()
                + objListMsg(list);
    }

    /**
     * The String command for choosing the attribute that will be modified,
     * as well as a {@code list} of options.
     *
     * @return The proper message
     */
    static String attributesListMsg(ArrayList<String> attributes) {
        return "Which attribute do you want to modify? (Enter a number)" + line()
                + objListMsg(attributes);
    }

    public static String dummyData() {
        return "Do you want to add dummy data to the program? (Enter a number)"+ line()
                + "1 -> Add Dummy Data" + line()
                + "0 -> No, don't add" + line();
    }
}
