package document;

import utils.MyUtilities;

import java.io.Serializable;

public abstract class Document implements Serializable {

    private String title;
    private int year;
    private int numOfPages;
    private int numOfCopies;
    private String code;

    public Document(String title, int year, int numOfPages, int numOfCopies, String code) {
        setTitle(title);
        setYear(year);
        setNumOfPages(numOfPages);
        setNumOfCopies(numOfCopies);
        setCode(code);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws IllegalArgumentException {
        this.year = MyUtilities.checkYear(year);
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) throws IllegalArgumentException {
        this.numOfPages = MyUtilities.checkMin(numOfPages, 1);
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) throws IllegalArgumentException {
        this.numOfCopies = MyUtilities.checkMin(numOfCopies, 0);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws IllegalArgumentException {
        this.code = MyUtilities.checkString(code);
    }

    public abstract void add();

    public abstract void remove();

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Document))
            throw new IllegalArgumentException();

        return code.equals(((Document) obj).code.trim().toUpperCase());
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
