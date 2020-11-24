package document;

import utils.MyUtilities;

import java.io.Serializable;

public abstract class Document implements DocInterface, Serializable {

    private String code;
    private String title;
    private int year;
    private int numOfPages;
    private int numOfCopies;

    public Document(String code, String title, int year, int numOfPages, int numOfCopies) {
        setTitle(title);
        setYear(year);
        setNumOfPages(numOfPages);
        setNumOfCopies(numOfCopies);
        setCode(code);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) throws IllegalArgumentException {
        this.year = MyUtilities.checkYear(year);
    }

    public void setNumOfPages(int numOfPages) throws IllegalArgumentException {
        this.numOfPages = MyUtilities.checkMin(numOfPages, 1);
    }

    public void setNumOfCopies(int numOfCopies) throws IllegalArgumentException {
        this.numOfCopies = MyUtilities.checkMin(numOfCopies, 0);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) throws IllegalArgumentException {
        this.code = MyUtilities.checkString(code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocInterface))
            throw new IllegalArgumentException();

        return code.equals(((DocInterface) obj).getCode().trim().toUpperCase());
    }

    @Override
    public String toString() {
        return code + System.lineSeparator()
                + "Title: " + title + System.lineSeparator()
                + "Year: " + year + System.lineSeparator()
                + "Number of Pages: " + numOfPages + System.lineSeparator()
                + "Number of Copies: " + numOfCopies + System.lineSeparator();
    }

}
