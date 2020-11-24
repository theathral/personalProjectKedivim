package document;

import author.Author;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

public class Book extends Paper {

    private static final int MAX_AUTHORS = 5;
    private ArrayList<Author> authors;


    public Book(String code, String title, int year, int numOfPages, int numOfCopies, String publisher, String ISBN, ArrayList<Author> authors) {
        super(code, title, year, numOfPages, numOfCopies, publisher, ISBN);
        setAuthors(authors);
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) throws IndexOutOfBoundsException, NullPointerException {
        if (authors.size() >= MAX_AUTHORS)
            throw new IndexOutOfBoundsException();

        this.authors = authors;
        add();
    }

    public void addAuthor(Author author) throws IndexOutOfBoundsException {
        if (authors.size() >= MAX_AUTHORS)
            throw new IndexOutOfBoundsException();

        authors.add(author);
    }

    public void removeAuthor(int index) throws IndexOutOfBoundsException {
        authors.get(index).deceaseOrRemovePublisher(getPublisher());
        authors.get(index).removeDocument(this);
        authors.remove(index);
    }

    @Override
    public void add() {
        authors.forEach(author -> {
            author.addPublisher(getPublisher());
            author.addDocument(this);
        });
    }

    @Override
    public void remove() {
        for (int i = 0; i < authors.size(); i++) {
            removeAuthor(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Book: ");
        str.append(super.toString());

        str.append("Authors: ");
        authors.forEach(author -> str.append(author.getName()).append(", "));
        str.append(System.lineSeparator());

        return str.toString();
    }
}
