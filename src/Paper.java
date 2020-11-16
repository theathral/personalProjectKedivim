public abstract class Paper extends Article {

    private String publisher;
    private String ISBN;

    public Paper(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN) {
        super(title, year, numOfPages, numOfCopies, code);
        this.publisher = publisher;
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Paper))
            return super.equals(obj);

        return ISBN.equals(((Paper) obj).ISBN);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "publisher='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}