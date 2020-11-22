package document.thesis;

import author.Author;
import document.Document;
import utils.MyUtilities;

public abstract class Thesis extends Document {

    private Author author;
    private String supervisor;
    private String department;
    private String university;

    public Thesis(String title, int year, int numOfPages, int numOfCopies, String code, String supervisor, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code);
        setSupervisor(supervisor);
        setDepartment(department);
        setUniversity(university);
    }

    public Thesis(String title, int year, int numOfPages, int numOfCopies, String code, Author author, String supervisor, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code);
        setAuthor(author);
        setSupervisor(supervisor);
        setDepartment(department);
        setUniversity(university);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) throws NullPointerException {
        this.author = author;
        add();
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) throws IllegalArgumentException {
        this.department = MyUtilities.checkString(department);
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) throws IllegalArgumentException {
        this.university = MyUtilities.checkString(university);
    }

    @Override
    public void add() {
        author.addDocument(this);
    }

    @Override
    public void remove() {
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
