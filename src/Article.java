public abstract class Article {

    private String title;
    private int year;
    private int numOfPages;
    private int numOfCopies;
    private String code;

    public Article(String title, int year, int numOfPages, int numOfCopies, String code) {
        this.title = title;
        this.year = year;
        this.numOfPages = numOfPages;
        this.numOfCopies = numOfCopies;
        this.code = code;
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

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
