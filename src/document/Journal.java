package document;

import utils.MyUtilities;

/**
 * Class {@code Journal} stores a journal with all its details (code, title, year, number of pages,
 * number of copies, publisher, ISBN, volume, issue).
 */
public class Journal extends Paper {

    private int volume;
    private int issue;

    /**
     * Constructor which stores the Book's {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code publisher}, {@code ISBN}, {@code volume} and {@code issue}.
     *
     * @param code        Book's code in the library
     * @param title       Book's title
     * @param year        Book's creation year
     * @param numOfPages  Book's number of pages
     * @param numOfCopies Book's number of copies
     * @param publisher   Book's publisher's name
     * @param ISBN        Book's ISBN
     * @param volume      Book's volume
     * @param issue       Book's issue
     */
    public Journal(String code, String title, int year, int numOfPages, int numOfCopies, String publisher, String ISBN, int volume, int issue) {
        super(code, title, year, numOfPages, numOfCopies, publisher, ISBN);
        setVolume(volume);
        setIssue(issue);
    }

    /**
     * Set the Journal's volume.
     *
     * @param volume Journal's volume
     * @throws IllegalArgumentException If the {@code volume} is less that 1
     */
    public void setVolume(int volume) throws IllegalArgumentException {
        this.volume = MyUtilities.checkMin(volume, 1);
    }

    /**
     * Set the Journal's issue.
     *
     * @param issue Journal's issue
     * @throws IllegalArgumentException If the {@code issue} is less that 1
     */
    public void setIssue(int issue) throws IllegalArgumentException {
        this.issue = MyUtilities.checkMin(issue, 1);
    }

    /**
     * Standard procedure when a new {@code Journal} instance is created or updated.
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    @Override
    public void add() throws IndexOutOfBoundsException {
    }

    /**
     * Standard procedure when an {@code Journal} instance is deleted
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    @Override
    public void remove() throws IndexOutOfBoundsException {
    }

    /**
     * Returns a string representation of the object.
     * It includes the Journal's details
     * (code, title, year, number of pages, number of copies, publisher, ISBN, volume, issue).
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Journal: " + super.toString()
                + "Volume: " + volume + System.lineSeparator()
                + "Issue: " + issue + System.lineSeparator();
    }
}
