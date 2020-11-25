package document.thesis;

import author.Author;

/**
 * Class {@code BachelorThesis} stores a bachelor thesis with all its details (code, title, year, number of pages,
 * number of copies, department, university), as well as an {@code Author} instance and the supervisor of the thesis.
 */
public class BachelorThesis extends Thesis {

    /**
     * Constructor which stores the Bachelor Thesis' {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code author}, {@code supervisor}, {@code department} and {@code university}.
     *
     * @param code        Bachelor Thesis' code in the library
     * @param title       Bachelor Thesis' title
     * @param year        Bachelor Thesis' creation year
     * @param numOfPages  Bachelor Thesis' number of pages
     * @param numOfCopies Bachelor Thesis' number of copies
     * @param author      Bachelor Thesis' author ({@code Author} instance)
     * @param supervisor  Bachelor Thesis' author's supervisor name
     * @param department  Bachelor Thesis' department of the university that has been published
     * @param university  Bachelor Thesis' university of publication
     */
    public BachelorThesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
    }

    /**
     * Returns a string representation of the object.
     * It includes the Bachelor Thesis' details (code, title, year, number of pages, number of copies, department, university),
     * as well as the name of the author and the supervisor of the thesis.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Bachelor " + super.toString();
    }
}
