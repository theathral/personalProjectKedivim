import java.time.Year;

public abstract class Article {

    private String title;
    private int year;
    private int numOfPages;
    private int numOfCopies;
    private String code;

    public Article(String title, int year, int numOfPages, int numOfCopies, String code) {
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

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Article))
            throw new IllegalArgumentException();

        return code.equals(((Article) obj).code);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", numOfPages=" + numOfPages +
                ", numOfCopies=" + numOfCopies +
                ", code='" + code + '\'' +
                '}';
    }
}
