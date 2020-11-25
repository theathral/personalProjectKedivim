package document.thesis;

import author.Author;

/**
 * Class {@code DoctoralThesis} stores a doctoral thesis with all its details (code, title, year, number of pages,
 * number of copies, department, university), as well as an {@code Author} instance and the supervisor of the thesis.
 */
public class DoctoralThesis extends Thesis {

    /**
     * Constructor which stores the Doctoral Thesis' {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code author}, {@code supervisor}, {@code department} and {@code university}.
     *
     * @param code        Doctoral Thesis' code in the library
     * @param title       Doctoral Thesis' title
     * @param year        Doctoral Thesis' creation year
     * @param numOfPages  Doctoral Thesis' number of pages
     * @param numOfCopies Doctoral Thesis' number of copies
     * @param author      Doctoral Thesis' author ({@code Author} instance)
     * @param supervisor  Doctoral Thesis' author's supervisor name
     * @param department  Doctoral Thesis' department of the university that has been published
     * @param university  Doctoral Thesis' university of publication
     */
    public DoctoralThesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
    }

    /**
     * Returns a string representation of the object.
     * It includes the Doctoral Thesis' details (code, title, year, number of pages, number of copies, department, university),
     * as well as the name of the author and the supervisor of the thesis.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Doctoral " + super.toString();
    }
}
