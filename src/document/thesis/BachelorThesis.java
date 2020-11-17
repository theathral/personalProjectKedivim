package document.thesis;

import author.Author;

public class BachelorThesis extends Thesis {

    public BachelorThesis(String title, int year, int numOfPages, int numOfCopies, String code, Author author, String supervisor, String type, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code, author, supervisor, type, department, university);
    }
}
