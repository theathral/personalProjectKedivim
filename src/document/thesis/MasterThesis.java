package document.thesis;

import author.Author;

/**
 * Class {@code MasterThesis} stores a master thesis with all its details (code, title, year, number of pages,
 * number of copies, department, university), as well as an {@code Author} instance and the supervisor of the thesis.
 */
public class MasterThesis extends Thesis {

    /**
     * Constructor which stores the Master Thesis' {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code author}, {@code supervisor}, {@code department} and {@code university}.
     *
     * @param code        Master Thesis' code in the library
     * @param title       Master Thesis' title
     * @param year        Master Thesis' creation year
     * @param numOfPages  Master Thesis' number of pages
     * @param numOfCopies Master Thesis' number of copies
     * @param author      Master Thesis' author ({@code Author} instance)
     * @param supervisor  Master Thesis' author's supervisor name
     * @param department  Master Thesis' department of the university that has been published
     * @param university  Master Thesis' university of publication
     */
    public MasterThesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
    }

    /**
     * Returns a string representation of the object.
     * It includes the Master Thesis' details (code, title, year, number of pages, number of copies, department, university),
     * as well as the name of the author and the supervisor of the thesis.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Master " + super.toString();
    }
}
