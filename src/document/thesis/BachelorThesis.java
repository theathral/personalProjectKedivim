package document.thesis;

import author.Author;

public class BachelorThesis extends Thesis {

    public BachelorThesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
    }

    @Override
    public String toString() {
        return "Bachelor " + super.toString();
    }
}
