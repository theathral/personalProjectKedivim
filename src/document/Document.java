package document;

import utils.MyUtilities;

import java.io.Serializable;

/**
 * Abstract Class {@code Document} stores a document with all its details (code, title, year, number of pages
 * number of copies).
 */
public abstract class Document implements DocInterface, Serializable {

    private String code;
    private String title;
    private int year;
    private int numOfPages;
    private int numOfCopies;

    /**
     * Constructor which stores the Document's {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}.
     *
     * @param code        Document's code in the library
     * @param title       Document's title
     * @param year        Document's creation year
     * @param numOfPages  Document's number of pages
     * @param numOfCopies Document's number of copies
     */
    public Document(String code, String title, int year, int numOfPages, int numOfCopies) {
        setTitle(title);
        setYear(year);
        setNumOfPages(numOfPages);
        setNumOfCopies(numOfCopies);
        setCode(code);
    }

    /**
     * Getter for the Document's title.
     *
     * @return The Document's title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Set the Document's title.
     *
     * @param title Document's title
     * @throws IllegalArgumentException If the {@code title} is an empty String
     */
    public void setTitle(String title) {
        this.title = MyUtilities.checkString(title);
    }

    /**
     * Getter for the Document's year of creation.
     *
     * @return The Document's year of creation
     */
    @Override
    public int getYear() {
        return year;
    }

    /**
     * Set the Document's year of creation.
     *
     * @param year Document's year of creation
     * @throws IllegalArgumentException If the {@code year} is after actual today's year
     */
    public void setYear(int year) throws IllegalArgumentException {
        this.year = MyUtilities.checkYear(year);
    }

    /**
     * Set the Document's number of pages.
     *
     * @param numOfPages Document's number of pages
     * @throws IllegalArgumentException If the {@code numOfPages} is less than 1
     */
    public void setNumOfPages(int numOfPages) throws IllegalArgumentException {
        this.numOfPages = MyUtilities.checkMin(numOfPages, 1);
    }

    /**
     * Set the Document's number of copies.
     *
     * @param numOfCopies Document's number of copies
     * @throws IllegalArgumentException If the {@code numOfPages} is less than 0
     */
    public void setNumOfCopies(int numOfCopies) throws IllegalArgumentException {
        this.numOfCopies = MyUtilities.checkMin(numOfCopies, 0);
    }

    /**
     * Getter for the Document's code.
     *
     * @return The Document's code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Set the Document's code.
     *
     * @param code Document's code
     * @throws IllegalArgumentException If the {@code code} is an empty String
     */
    public void setCode(String code) throws IllegalArgumentException {
        this.code = MyUtilities.checkString(code);
    }

    /**
     * Returns a hash code value for the object based on the code of the {@code Document} instance.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return code.hashCode();
    }

    /**
     * Indicates whether a {@code Document} object is "equal to" this one according to the object's code.
     * If the parameter {@code obj} is not Document instance, the function calls the super function of {@code equals}.
     *
     * @param obj The reference object with which to compare.
     * @return {@code True} if this object is the same as the obj argument; {@code False} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Document))
            return super.equals(obj);

        return code.equals(((DocInterface) obj).getCode().trim().toUpperCase());
    }

    /**
     * Returns a string representation of the object.
     * It includes the Document's details (code, title, year, number of pages, number of copies).
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return code + System.lineSeparator()
                + "Title: " + title + System.lineSeparator()
                + "Year: " + year + System.lineSeparator()
                + "Number of Pages: " + numOfPages + System.lineSeparator()
                + "Number of Copies: " + numOfCopies + System.lineSeparator();
    }
}
