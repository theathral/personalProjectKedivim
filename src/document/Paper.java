package document;

import utils.MyUtilities;

/**
 * Abstract Class {@code Paper} stores a paper with all its details (code, title, year, number of pages,
 * number of copies, publisher, ISBN).
 */
public abstract class Paper extends Document {

    private String publisher;
    private String ISBN;

    /**
     * Constructor which stores the Book's {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code publisher} and {@code ISBN}.
     *
     * @param code        Paper's code in the library
     * @param title       Paper's title
     * @param year        Paper's creation year
     * @param numOfPages  Paper's number of pages
     * @param numOfCopies Paper's number of copies
     * @param publisher   Paper's publisher's name
     * @param ISBN        Paper's ISBN
     */
    public Paper(String code, String title, int year, int numOfPages, int numOfCopies, String publisher, String ISBN) {
        super(code, title, year, numOfPages, numOfCopies);
        setPublisher(publisher);
        setISBN(ISBN);
    }

    /**
     * Getter for the Paper's publisher.
     *
     * @return The Paper's publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set the Paper's publisher.
     *
     * @param publisher Paper's issue
     * @throws IllegalArgumentException If the {@code publisher} is an empty String
     */
    public void setPublisher(String publisher) throws IllegalArgumentException {
        this.publisher = MyUtilities.checkString(publisher);
    }

    /**
     * Set the Paper's ISBN.
     *
     * @param ISBN Paper's issue
     * @throws IllegalArgumentException If the {@code ISBN} is an empty String
     */
    public void setISBN(String ISBN) throws IllegalArgumentException {
        this.ISBN = MyUtilities.checkString(ISBN);
    }

    /**
     * Indicates whether a {@code Paper} object is "equal to" this one according to the object's ISBN.
     * If the parameter {@code obj} is not Paper instance, the function calls the super function of {@code equals}.
     *
     * @param obj The reference object with which to compare.
     * @return {@code True} if this object is the same as the obj argument; {@code False} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Paper))
            return super.equals(obj);

        return super.equals(obj) && ISBN.equals(((Paper) obj).ISBN.trim().toUpperCase());
    }

    /**
     * Returns a string representation of the object.
     * It includes the Paper's details (code, title, year, number of pages, number of copies, publisher, ISBN).
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Publisher: " + publisher + System.lineSeparator()
                + "ISBN: " + ISBN + System.lineSeparator();
    }
}
