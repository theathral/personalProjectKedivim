package document.thesis;

import author.Author;
import document.Document;
import utils.MyUtilities;

/**
 * Abstract Class {@code Thesis} stores a thesis with all its details (code, title, year, number of pages,
 * number of copies, department, university), as well as an {@code Author} instance and the supervisor of the thesis.
 */
public abstract class Thesis extends Document {

    private Author author;
    private String supervisor;
    private String department;
    private String university;

    /**
     * Constructor which stores the Thesis' {@code code}, {@code title}, {@code year}, {@code numOfPages},
     * {@code numOfCopies}, {@code author}, {@code supervisor}, {@code department} and {@code university}.
     *
     * @param code        Thesis' code in the library
     * @param title       Thesis' title
     * @param year        Thesis' creation year
     * @param numOfPages  Thesis' number of pages
     * @param numOfCopies Thesis' number of copies
     * @param author      Thesis' author ({@code Author} instance)
     * @param supervisor  Thesis' author's supervisor name
     * @param department  Thesis' department of the university that has been published
     * @param university  Thesis' university of publication
     */
    public Thesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies);
        setAuthor(author);
        setSupervisor(supervisor);
        setDepartment(department);
        setUniversity(university);
    }

    /**
     * Set the Author's instance.
     * Throws {@code NullPointerException} if the {@code Author} instance is null.
     *
     * @param author Author's instance
     * @throws NullPointerException If the {@code Author} instance is null
     */
    public void setAuthor(Author author) throws NullPointerException {
        if (this.author != null)
            remove();
        this.author = author;
        add();
    }

    /**
     * Set the supervisor's name.
     * Throws {@code IllegalArgumentException} if the {@code supervisor} is an empty String.
     *
     * @param supervisor Thesis' supervisor's name
     * @throws IllegalArgumentException If the {@code supervisor} is an empty String
     */
    public void setSupervisor(String supervisor) throws IllegalArgumentException {
        this.supervisor = MyUtilities.checkString(supervisor);
    }

    /**
     * Set the department's name.
     * Throws {@code IllegalArgumentException} if the {@code department} is an empty String.
     *
     * @param department Thesis' department's name
     * @throws IllegalArgumentException If the {@code department} is an empty String
     */
    public void setDepartment(String department) throws IllegalArgumentException {
        this.department = MyUtilities.checkString(department);
    }

    /**
     * Set the university's name.
     * Throws {@code IllegalArgumentException} if the {@code university} is an empty String.
     *
     * @param university Thesis' university's name
     * @throws IllegalArgumentException If the {@code university} is an empty String
     */
    public void setUniversity(String university) throws IllegalArgumentException {
        this.university = MyUtilities.checkString(university);
    }

    /**
     * Standard procedure when a new {@code Thesis} instance is created or updated.
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    @Override
    public void add() throws IndexOutOfBoundsException {
        author.addDocument(this);
    }

    /**
     * Standard procedure when an {@code Thesis} instance is deleted
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    @Override
    public void remove() throws IndexOutOfBoundsException {
        author.removeDocument(this);
    }

    /**
     * Returns a string representation of the object.
     * It includes the Thesis' details (code, title, year, number of pages, number of copies, department, university),
     * as well as the name of the author and the supervisor of the thesis.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Thesis: " + super.toString()
                + "Author: " + author.getName() + System.lineSeparator()
                + "Supervisor: " + supervisor + System.lineSeparator()
                + "Department: " + department + System.lineSeparator()
                + "University: " + university + System.lineSeparator();
    }
}
