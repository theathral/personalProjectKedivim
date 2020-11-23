package document.thesis;

import author.Author;

public class MasterThesis extends Thesis {

    public MasterThesis(String code, String title, int year, int numOfPages, int numOfCopies, Author author, String supervisor, String department, String university) {
        super(code, title, year, numOfPages, numOfCopies, author, supervisor, department, university);
    }

    @Override
    public String toString() {
        return "Master " + super.toString();
    }
}
