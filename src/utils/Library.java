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
import java.util.TreeMap;

public class Library implements Serializable {

    private TreeMap<String, Class<?>> typeOfDocuments;
    private final ArrayList<DocInterface> documents;
    private final ArrayList<Author> authors;

    public Library() {
        setTypeOfDocuments();
        authors = new ArrayList<>();
        documents = new ArrayList<>();
    }

    public TreeMap<String, Class<?>> getTypeOfDocuments() {
        return typeOfDocuments;
    }

    private void setTypeOfDocuments() {
        this.typeOfDocuments = new TreeMap<>();

        typeOfDocuments.put("Book", Book.class);
        typeOfDocuments.put("Journal", Journal.class);
        typeOfDocuments.put("Bachelor Thesis", BachelorThesis.class);
        typeOfDocuments.put("Master Thesis", MasterThesis.class);
        typeOfDocuments.put("Doctoral Thesis", DoctoralThesis.class);
    }


    public ArrayList<DocInterface> getDocuments() {
        return documents;
    }

    public DocInterface getDocument(int index) {
        return documents.get(index);
    }

    public DocInterface getDocument(String code) throws IndexOutOfBoundsException {
        return documents.get(findDocumentByCode(code));
    }

    public ArrayList<DocInterface> getDocumentWithTitle(String title) {
        ArrayList<DocInterface> docs = new ArrayList<>();

        findDocumentByTitle(title).forEach(idx -> docs.add(getDocument(idx)));

        return docs;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public Author getAuthor(String name) throws IndexOutOfBoundsException {
        return authors.get(findAuthor(name));
    }


    public void addDocument(DocInterface document) throws IllegalArgumentException {
        if (documents.contains(document))
            throw new IllegalArgumentException();

        documents.add(document);
    }

    public void addAuthor(Author author) throws IllegalArgumentException {
        if (authors.contains(author))
            throw new IllegalArgumentException();

        authors.add(author);
    }


    public void deleteDocument(String code) throws IndexOutOfBoundsException {
        getDocument(code).remove();
        documents.remove(findDocumentByCode(code));
    }

    public void deleteAuthor(String name) throws IndexOutOfBoundsException {
        authors.remove(findAuthor(name));
    }


    public int countDocumentsClass(Class<?> myClass) {
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
            if (t.contains(documents.get(i).getTitle()))
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


    public void saveToFile(String path) {

    }

    public void writeToBinaryFile(String path) throws IOException {
        new ObjectOutputStream(new FileOutputStream(new File(path))).writeObject(this);
    }

    public Library loadFile(String path) throws IllegalArgumentException, IOException, ClassNotFoundException {
        if (!getDocuments().isEmpty() || !getAuthors().isEmpty())
            throw new IllegalArgumentException();

        return (Library) new ObjectInputStream(new FileInputStream(new File(path))).readObject();
    }
}
