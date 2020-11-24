package document.thesis;

import author.Author;
import document.Document;
import utils.MyUtilities;

public abstract class Thesis extends Document {

    private Author author;
    private String supervisor;
    private String department;
    private String university;

    public Thesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies);
        setAuthor(author);
        setSupervisor(supervisor);
        setDepartment(department);
        setUniversity(university);
    }

    public void setAuthor(Author author) throws NullPointerException {
        if (this.author != null)
            remove();
        this.author = author;
        add();
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public void setDepartment(String department) throws IllegalArgumentException {
        this.department = MyUtilities.checkString(department);
    }

    public void setUniversity(String university) throws IllegalArgumentException {
        this.university = MyUtilities.checkString(university);
    }

    @Override
    public void add() throws IndexOutOfBoundsException {
        author.addDocument(this);
    }

    @Override
    public void remove() throws IndexOutOfBoundsException {
        author.removeDocument(this);
    }

    @Override
    public String toString() {
        return "Thesis: " + super.toString()
                + "Author: " + author.getName() + System.lineSeparator()
                + "Supervisor: " + supervisor + System.lineSeparator()
                + "Department: " + department + System.lineSeparator()
                + "University: " + university + System.lineSeparator();
    }
}
