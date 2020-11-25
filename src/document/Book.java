package document;

import author.Author;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class {@code Book} stores a book with all its details (code, title, year, number of pages,
 * number of copies, publisher, ISBN), as well as an {@code ArrayList} instance of {@code Author} instances.
 */
public class Book extends Paper {

    /**
     * Max {@code Author} instances of for every {@code Book} instance.
     */
    private static final int MAX_AUTHORS = 5;
    private ArrayList<Author> authors;

    /**
     * Constructor which stores the Book's {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code publisher}, {@code ISBN} and {@code authors}.
     *
     * @param code        Book's code in the library
     * @param title       Book's title
     * @param year        Book's creation year
     * @param numOfPages  Book's number of pages
     * @param numOfCopies Book's number of copies
     * @param publisher   Book's publisher's name
     * @param ISBN        Book's ISBN
     * @param authors     Book's authors ({@code ArrayList<Author>} instance)
     */
    public Book(String code, String title, int year, int numOfPages, int numOfCopies, String publisher, String ISBN, ArrayList<Author> authors) {
        super(code, title, year, numOfPages, numOfCopies, publisher, ISBN);
        setAuthors(authors);
    }

    /**
     * Getter for the Book's authors {@code ArrayList} instance.
     *
     * @return The Book's authors
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Set the {@code ArrayList} instance of {@code Author} instances.
     * Throws {@code IndexOutOfBoundsException} if the authors of the {@code Book} are more than 5.
     * Throws {@code NullPointerException} if the {@code ArrayList} instance or at least one {@code Author} instance is null.
     *
     * @param authors Author's instance
     * @throws IndexOutOfBoundsException If the authors of the {@code Book} are more than 5
     * @throws NullPointerException      If the {@code ArrayList} instance or at least one {@code Author} instance is null
     */
    private void setAuthors(ArrayList<Author> authors) throws IndexOutOfBoundsException, NullPointerException {
        if (authors.size() >= MAX_AUTHORS)
            throw new IndexOutOfBoundsException();

        authors.stream().filter(Objects::isNull).forEach(author -> {
            throw new NullPointerException();
        });

        this.authors = authors;
        add();
    }

    /**
     * Adds an Author's instance to the {@code Book}.
     * Throws {@code IndexOutOfBoundsException} if the authors of the {@code Book} are already 5.
     * Throws {@code NullPointerException} if the {@code ArrayList} instance or at least one {@code Author} instance is null.
     *
     * @param author Author's instance
     * @throws IndexOutOfBoundsException If the authors of the {@code Book} are already 5
     * @throws NullPointerException      If the {@code ArrayList} instance or at least one {@code Author} instance is null
     */
    public void addAuthor(Author author) throws IndexOutOfBoundsException, NullPointerException {
        if (this.authors.size() >= MAX_AUTHORS)
            throw new IndexOutOfBoundsException();

        author.addPublisher(getPublisher());
        author.addDocument(this);

        this.authors.add(author);
    }

    /**
     * Removes an Author's instance from the {@code Book}.
     * Throws {@code IndexOutOfBoundsException} if the author does not exist.
     *
     * @param author Author's instance
     * @throws IndexOutOfBoundsException If the author does not exist
     */
    public void removeAuthor(Author author) throws IndexOutOfBoundsException {
        removeAuthor(authors.indexOf(author));
    }

    /**
     * Removes an Author's instance from the {@code Book}.
     * Throws {@code IndexOutOfBoundsException} if the {@code index} is out of list range.
     *
     * @param index Author's index in the list of authors
     * @throws IndexOutOfBoundsException If the {@code index} is out of list range
     */
    public void removeAuthor(int index) throws IndexOutOfBoundsException {
        authors.get(index).deceaseOrRemovePublisher(getPublisher());
        authors.get(index).removeDocument(this);
        authors.remove(index);
    }

    /**
     * Standard procedure when a new {@code Book} instance is created or updated.
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    @Override
    public void add() throws IndexOutOfBoundsException {
        authors.forEach(author -> {
            author.addPublisher(getPublisher());
            author.addDocument(this);
        });
    }

    /**
     * Standard procedure when an {@code Book} instance is deleted
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    @Override
    public void remove() throws IndexOutOfBoundsException {
        for (int i = 0; i < authors.size(); i++) {
            removeAuthor(i);
        }
    }

    /**
     * Returns a string representation of the object.
     * It includes the Book's details (code, title, year, number of pages, number of copies, publisher, ISBN),
     * as well as the names of the authors of the {@code Book}.
     *
     * @return A string representation of the object.
     */
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
