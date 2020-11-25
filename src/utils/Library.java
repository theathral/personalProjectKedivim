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

/**
 * Class {@code Library} stores a library with a list of Documents and a list of Authors.
 */
public class Library implements Serializable {

    /**
     * Seperation String between Documents' or Authors' instances
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


    private static ArrayList<DocInterface> copySortDocumentsByYear(ArrayList<DocInterface> oldList) {
        ArrayList<DocInterface> sortedList = new ArrayList<>(oldList);
        sortedList.sort(Comparator.comparing(DocInterface::getYear));

        return sortedList;
    }

    private int countDocumentsClass(Class<?> myClass) {
        int counter = 0;

        for (DocInterface doc : documents) {
            if (doc.getClass() == myClass)
                counter++;
        }

        return counter;
    }


    public int findDocumentByCode(String code) throws IllegalArgumentException {
        String c = MyUtilities.checkString(code);

        for (int i = 0; i < documents.size(); i++) {
            if (c.equals(documents.get(i).getCode()))
                return i;
        }

        return -1;
    }

    public ArrayList<Integer> findDocumentByTitle(String title) throws IllegalArgumentException {
        String t = MyUtilities.checkString(title);
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getTitle().contains(t))
                indexes.add(i);
        }

        return indexes;
    }

    public int findAuthor(String name) throws IllegalArgumentException {
        String n = MyUtilities.checkString(name);

        for (int i = 0; i < authors.size(); i++) {
            if (n.equals(authors.get(i).getName()))
                return i;
        }

        return -1;
    }


    public void saveToFile(String result, String filePath) throws IOException {
        FileWriter writer = new FileWriter(new File(filePath));
        writer.write(result);
        writer.close();
    }

    public void writeToBinaryFile(String path) throws IOException {
        new ObjectOutputStream(new FileOutputStream(new File(path))).writeObject(this);
    }

    public Library loadFile(String path) throws IllegalArgumentException, IOException, ClassNotFoundException {
        if (!getDocuments().isEmpty() || !getAuthors().isEmpty())
            throw new IllegalArgumentException();

        return (Library) new ObjectInputStream(new FileInputStream(new File(path))).readObject();
    }


    public String printStatsStr() {
        StringBuilder str = new StringBuilder();

        str.append("Stats of the Library:").append(System.lineSeparator());
        str.append("Total number of Documents: ").append(documents.size()).append(System.lineSeparator());

        typeOfDocuments.forEach((key, value) ->
                str.append("\t").append(key).append(": ").append(countDocumentsClass(value)).append(System.lineSeparator()));

        return str.toString();
    }

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

    public String printDocumentWithCodeStr(String code) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + getDocument(code)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }

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

    public String printAuthorStr(String name) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + getAuthor(name)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }
}
