package document;

import author.Author;

import java.util.ArrayList;

public class Book extends Paper {

    private static final int MAX_AUTHORS = 5;
    private ArrayList<Author> authors;

    public Book(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, ArrayList<Author> authors) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        setAuthors(authors);
    }

    public Book(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, Author author) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        authors = new ArrayList<>();
        addAuthor(author);
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) throws IndexOutOfBoundsException, NullPointerException {
        if (authors.size() >= MAX_AUTHORS)
            throw new IndexOutOfBoundsException();

        this.authors = authors;
    }

    public void addAuthor(Author author) throws IndexOutOfBoundsException {
        if (authors.size() >= MAX_AUTHORS)
            throw new IndexOutOfBoundsException();

        authors.add(author);
    }

    public Author removeAuthor(int index) throws IndexOutOfBoundsException {
        return authors.remove(index);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
