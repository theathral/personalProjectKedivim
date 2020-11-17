package utils;

import author.Author;
import document.*;
import document.thesis.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {

    private HashMap<String, Class<?>> typeOfDocuments;
    private ArrayList<Document> documents;
    private ArrayList<Author> authors;

    public Library() {
        setTypeOfDocuments();
        authors = new ArrayList<>();
        documents = new ArrayList<>();
    }

    public HashMap<String, Class<?>> getTypeOfDocuments() {
        return typeOfDocuments;
    }

    private void setTypeOfDocuments() {
        this.typeOfDocuments = new HashMap<>();

        typeOfDocuments.put("Book", Book.class);
        typeOfDocuments.put("Journal", Journal.class);
        typeOfDocuments.put("Bachelor Thesis", BachelorThesis.class);
        typeOfDocuments.put("Master Thesis", MasterThesis.class);
        typeOfDocuments.put("Doctoral Thesis", DoctoralThesis.class);
    }


    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public Document getDocument(int index) {
        return documents.get(index);
    }

    public Document getDocument(String code) {
        return documents.get(findDocumentByCode(code));
    }

    public ArrayList<Document> getDocumentWithTitle(String title) {
        ArrayList<Document> docs = new ArrayList<>();

        findDocumentByTitle(title).forEach(idx -> docs.add(getDocument(idx)));

        return docs;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public Author getAuthor(int index) {
        return authors.get(index);
    }

    public Author getAuthor(String name) {
        return authors.get(findAuthor(name));
    }


    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }


    public void addDocument(Document document) throws IllegalArgumentException {
        if (documents.contains(document))
            throw new IllegalArgumentException();

        documents.add(document);
    }

    public void addDocument(Document document, Author author) throws IllegalArgumentException {
        if (documents.contains(document) || authors.contains(author))
            throw new IllegalArgumentException();

        documents.add(document);
        authors.add(author);
    }

    public void addAuthor(Author author) throws IllegalArgumentException {
        if (authors.contains(author))
            throw new IllegalArgumentException();

        authors.add(author);
    }


    public void deleteDocument(String code) throws IndexOutOfBoundsException {
        documents.remove(findDocumentByCode(code));
    }

    public void deleteAuthor(String name) throws IndexOutOfBoundsException {
        authors.remove(findAuthor(name));
    }


    public int countDocumentsClass(Class<?> myClass) {
        int counter = 0;

        for (Document doc : documents) {
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
            if (t.matches(documents.get(i).getTitle()))
                indexes.add(i);
        }

        return indexes;
    }

    public ArrayList<String> findDocumentsByAuthor(String name) throws IllegalArgumentException {
        ArrayList<String> indexes = new ArrayList<>();

        authors.get(findAuthor(name)).getDocuments().forEach(doc -> indexes.add(doc.getCode()));

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



























}
