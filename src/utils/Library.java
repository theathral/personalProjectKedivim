package utils;

import author.Author;
import document.Book;
import document.DocInterface;
import document.Journal;
import document.thesis.BachelorThesis;
import document.thesis.DoctoralThesis;
import document.thesis.MasterThesis;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class {@code Library} stores a library with a list of Documents and a list of Authors.
 */
public class Library implements Serializable {

    /**
     * Separation String between Documents' or Authors' instances
     */
    private static final String sep = "||--------------------||";

    private TreeMap<String, Class<?>> typeOfDocuments;
    private final ArrayList<DocInterface> documents;
    private final ArrayList<Author> authors;

    /**
     * Constructor which initiates a Library instance.
     */
    public Library() {
        setTypeOfDocuments();
        authors = new ArrayList<>();
        documents = new ArrayList<>();
    }

    /**
     * Getter for the Library's type of Documents.
     *
     * @return The Library's type of Documents with their String class name representation
     */
    public TreeMap<String, Class<?>> getTypeOfDocuments() {
        return typeOfDocuments;
    }

    /**
     * Set the Library's type of Documents.
     */
    private void setTypeOfDocuments() {
        this.typeOfDocuments = new TreeMap<>();

        typeOfDocuments.put("Book", Book.class);
        typeOfDocuments.put("Journal", Journal.class);
        typeOfDocuments.put("Bachelor Thesis", BachelorThesis.class);
        typeOfDocuments.put("Master Thesis", MasterThesis.class);
        typeOfDocuments.put("Doctoral Thesis", DoctoralThesis.class);
    }


    /**
     * Getter for the Library's Documents.
     *
     * @return The Library's Documents' instances
     */
    public ArrayList<DocInterface> getDocuments() {
        return documents;
    }

    /**
     * Getter for the Library's Document in position {@code index}.
     *
     * @param index position of the {@code Document}
     * @return The Library's Document's instance in position {@code index}
     */
    public DocInterface getDocument(int index) {
        return documents.get(index);
    }

    /**
     * Getter for the Library's Document with code value equals {@code code}.
     *
     * @param code Code of the {@code Document}
     * @return The Library's Document's instance with code value equals {@code code}
     * @throws IndexOutOfBoundsException If the document does not exist
     */
    public DocInterface getDocument(String code) throws IndexOutOfBoundsException {
        return documents.get(findDocumentByCode(code));
    }

    /**
     * Getter for the Library's Documents with title value contains {@code title}.
     *
     * @param title Title of the {@code Document}
     * @return The Library's Documents' instances with title value contains {@code title}
     * @throws IllegalArgumentException If no document has found
     */
    public ArrayList<DocInterface> getDocumentWithTitle(String title) throws IllegalArgumentException {
        ArrayList<DocInterface> docs = new ArrayList<>();

        findDocumentByTitle(title).forEach(idx -> docs.add(getDocument(idx)));

        return docs;
    }

    /**
     * Getter for the Library's Authors.
     *
     * @return The Library's Authors' instances
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Getter for the Library's Author with name value equals {@code name}.
     *
     * @param name Name of the {@code Author}
     * @return The Library's Author's instance with code value equals {@code code}
     * @throws IndexOutOfBoundsException If the {@code author} does not exist
     */
    public Author getAuthor(String name) throws IndexOutOfBoundsException {
        return authors.get(findAuthor(name));
    }


    /**
     * Adds a new document to the documents' {@code ArrayList} instance.
     * Throws {@code IllegalArgumentException} if the {@code document} already exists in the {@code ArrayList} instance.
     *
     * @param document The {@code Document} that will be added
     * @throws IllegalArgumentException If the {@code document} already exists in the {@code ArrayList} instance
     */
    public void addDocument(DocInterface document) throws IllegalArgumentException {
        if (documents.contains(document))
            throw new IllegalArgumentException();

        documents.add(document);
    }

    /**
     * Adds a new author to the authors' {@code ArrayList} instance.
     * Throws {@code IllegalArgumentException} if the {@code author} already exists in the {@code ArrayList} instance.
     *
     * @param author The {@code Author} that will be added
     * @throws IllegalArgumentException If the {@code author} already exists in the {@code ArrayList} instance
     */
    public void addAuthor(Author author) throws IllegalArgumentException {
        if (authors.contains(author))
            throw new IllegalArgumentException();

        authors.add(author);
    }


    /**
     * Removes an existing document from the documents' {@code ArrayList} instance.
     * Throws {@code IndexOutOfBoundsException} if the {@code document} does not exist in the {@code ArrayList} instance.
     *
     * @param code The code of the {@code Document} that will be removed
     * @throws IndexOutOfBoundsException If the {@code document} does not exist in the {@code ArrayList} instance
     */
    public void deleteDocument(String code) throws IndexOutOfBoundsException {
        getDocument(code).remove();
        documents.remove(findDocumentByCode(code));
    }

    /**
     * Removes all documents from the documents' {@code ArrayList} instance.
     */
    public void deleteAllDocuments() {
        for (int i = documents.size() - 1; i >= 0; i--) {
            deleteDocument(documents.get(i).getCode());
        }
    }

    /**
     * Removes an existing author from the authors' {@code ArrayList} instance.
     * Throws {@code IndexOutOfBoundsException} if the {@code author} does not exist in the {@code ArrayList} instance.
     *
     * @param name The code of the {@code Document} that will be removed
     * @throws IndexOutOfBoundsException If the {@code author} does not exist in the {@code ArrayList} instance
     */
    public void deleteAuthor(String name) throws IndexOutOfBoundsException {
        authors.remove(findAuthor(name));
    }


    /**
     * Sorts a list of {@code Document} instances and returns a new sorted list.
     *
     * @param oldList The list to be sorted
     * @return The new sorted list
     */
    private static ArrayList<DocInterface> copySortDocumentsByYear(ArrayList<DocInterface> oldList) {
        ArrayList<DocInterface> sortedList = new ArrayList<>(oldList);
        sortedList.sort(Comparator.comparing(DocInterface::getYear));

        return sortedList;
    }

    /**
     * Counts the number of {@code Document} instances of a type/class and returns the counted value
     *
     * @param myClass The class that the instances will be counted of
     * @return Number of documents of a class
     */
    private int countDocumentsClass(Class<?> myClass) {
        return (int) documents.stream().filter(doc -> doc.getClass() == myClass).count();
    }


    /**
     * Finds the Library's Document with code value equals {@code code}
     * If it exists, returns the index where the document is located in the list of documents.
     * -1, otherwise.
     *
     * @param code Code of the {@code Document}
     * @return The index where the document is located in the list of documents
     * @throws IllegalArgumentException If the code is an empty String
     */
    private int findDocumentByCode(String code) throws IllegalArgumentException {
        String c = MyUtilities.checkString(code);

        return IntStream.range(0, documents.size()).filter(i -> c.equals(documents.get(i).getCode())).findFirst().orElse(-1);
    }

    /**
     * Finds the Library's Documents with title value contains {@code title}.
     *
     * @param title Title of the {@code Document}
     * @return The indexes where the documents are located in the list of documents
     * @throws IllegalArgumentException If title is an empty String
     */
    private ArrayList<Integer> findDocumentByTitle(String title) throws IllegalArgumentException {
        String t = MyUtilities.checkString(title);

        return IntStream.range(0, documents.size()).filter(i -> documents.get(i).getTitle().contains(t)).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds the Library's Author with name value equals {@code name}.
     *
     * @param name Name of the {@code Author}
     * @return The index where the author is located in the list of authors
     * @throws IllegalArgumentException If name is an empty String
     */
    private int findAuthor(String name) throws IllegalArgumentException {
        String n = MyUtilities.checkString(name);

        return IntStream.range(0, authors.size()).filter(i -> n.equals(authors.get(i).getName())).findFirst().orElse(-1);
    }


    /**
     * If the document exists, returns {@code True}. {@code False} otherwise.
     *
     * @param code Code of the {@code Document}
     * @return {@code True} if the document exists, {@code False} otherwise.
     */
    public boolean documentExists(String code) {
        return findDocumentByCode(code) != -1;
    }

    /**
     * If the author exists, returns {@code True}. {@code False} otherwise.
     *
     * @param name Name of the {@code Author}
     * @return {@code True} if the author exists, {@code False} otherwise.
     */
    public boolean authorExists(String name) {
        return findAuthor(name) != -1;
    }


    /**
     * Saves to text file {@code filepath}, a given String {@code result}.
     *
     * @param result   The String that will be saved
     * @param filePath The file that will be created
     * @throws IOException If action fails
     */
    public void saveToFile(String result, String filePath) throws IOException {
        FileWriter writer = new FileWriter(new File(filePath));
        writer.write(result);
        writer.close();
    }

    /**
     * Saves to binary file {@code path}, the current {@code Library} instance.
     *
     * @param path The file that will be created
     * @throws IOException If action fails
     */
    public void writeToBinaryFile(String path) throws IOException {
        new ObjectOutputStream(new FileOutputStream(new File(path))).writeObject(this);
    }

    /**
     * Loads a {@code Library} instance from a given file {@code path}.
     *
     * @param path The file that contains the {@code Library} instance
     * @throws IOException If action fails
     */
    public Library loadFile(String path) throws IllegalArgumentException, IOException, ClassNotFoundException {
        if (!getDocuments().isEmpty() || !getAuthors().isEmpty())
            throw new IllegalArgumentException();

        return (Library) new ObjectInputStream(new FileInputStream(new File(path))).readObject();
    }


    /**
     * Prints to a String, the current statistics of the {@code Library} instance
     *
     * @return The current statistics of the {@code Library} instance
     */
    public String printStatsStr() {
        StringBuilder str = new StringBuilder();

        str.append("Stats of the Library:").append(System.lineSeparator());
        str.append("Total number of Documents: ").append(documents.size()).append(System.lineSeparator());

        typeOfDocuments.forEach((key, value) ->
                str.append("\t").append(key).append(": ").append(countDocumentsClass(value)).append(System.lineSeparator()));

        return str.toString();
    }

    /**
     * Prints to a String, all the documents of the {@code Library} instance
     *
     * @return All the documents of the {@code Library} instance
     */
    public String printDocumentsStr() {
        StringBuilder str = new StringBuilder();

        str.append("Documents of the Library:").append(System.lineSeparator());
        str.append(sep).append(System.lineSeparator()).append(System.lineSeparator());

        if (documents.isEmpty()) {
            str.append("No Documents Found!").append(System.lineSeparator()).append(System.lineSeparator());
            str.append(sep).append(System.lineSeparator());
            return str.toString();
        }

        copySortDocumentsByYear(documents).forEach(doc -> {
            str.append(doc);
            str.append(System.lineSeparator()).append(sep).append(System.lineSeparator()).append(System.lineSeparator());
        });

        return str.toString();
    }

    /**
     * Finds and prints to a String, the document of the {@code Library} instance with a given {@code code}
     *
     * @param code The given {@code code}
     * @return The document of the {@code Library} instance with the given code
     */
    public String printDocumentWithCodeStr(String code) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + getDocument(code)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }

    /**
     * Finds and prints to a String, the documents of the {@code Library} instance with a given {@code title}
     *
     * @param title The given {@code title}
     * @return The documents of the {@code Library} instance with the given title
     */
    public String printDocumentWithTitleStr(String title) throws IndexOutOfBoundsException {
        StringBuilder str = new StringBuilder();

        str.append("Documents of the Library with the title: ").append(title).append(System.lineSeparator());
        str.append(sep).append(System.lineSeparator()).append(System.lineSeparator());

        if (getDocumentWithTitle(title).isEmpty())
            throw new IndexOutOfBoundsException();

        copySortDocumentsByYear(getDocumentWithTitle(title)).forEach(doc -> {
            str.append(doc);
            str.append(System.lineSeparator()).append(sep).append(System.lineSeparator()).append(System.lineSeparator());
        });

        return str.toString();
    }

    /**
     * Prints to a String, all the authors of the {@code Library} instance
     *
     * @return All the authors of the {@code Library} instance
     */
    public String printAuthorsStr() {
        StringBuilder str = new StringBuilder();

        str.append("Authors of the Library:").append(System.lineSeparator());
        str.append(sep).append(System.lineSeparator()).append(System.lineSeparator());

        if (authors.isEmpty()) {
            str.append("No Authors Found!").append(System.lineSeparator()).append(System.lineSeparator());
            str.append(sep).append(System.lineSeparator());
            return str.toString();
        }

        authors.forEach(auth -> {
            str.append(auth);
            str.append(System.lineSeparator()).append(sep).append(System.lineSeparator()).append(System.lineSeparator());
        });

        return str.toString();
    }

    /**
     * Finds and prints to a String, the author of the {@code Library} instance with a given {@code name}
     *
     * @param name The given {@code name}
     * @return The author of the {@code Library} instance with the given name
     */
    public String printAuthorStr(String name) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + getAuthor(name)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }
}
