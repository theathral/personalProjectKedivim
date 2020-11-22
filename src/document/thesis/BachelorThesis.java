package document.thesis;

import author.Author;

public class BachelorThesis extends Thesis {

    public BachelorThesis(String title, int year, int numOfPages, int numOfCopies, String code, String supervisor, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code, supervisor, department, university);
    }

    public BachelorThesis(String title, int year, int numOfPages, int numOfCopies, String code, Author author, String supervisor, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university);
    }

    @Override
    public String toString() {
        return "Bachelor " + super.toString();
    }
}
