package document.thesis;

import author.Author;

public class DoctoralThesis extends Thesis {

    public DoctoralThesis(String title, int year, int numOfPages, int numOfCopies, String code, Author author, String supervisor, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code, author, supervisor, department, university);
    }
}
