package document;

import utils.MyUtilities;

public abstract class Paper extends Document {

    private String publisher;
    private String ISBN;

    public Paper(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN) {
        super(title, year, numOfPages, numOfCopies, code);
        setPublisher(publisher);
        setISBN(ISBN);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) throws IllegalArgumentException {
        this.publisher = MyUtilities.checkString(publisher);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) throws IllegalArgumentException {
        this.ISBN = MyUtilities.checkString(ISBN);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Paper))
            return super.equals(obj);

        return ISBN.equals(((Paper) obj).ISBN.trim().toUpperCase());
    }

    @Override
    public String toString() {
        return "Documents.Paper{" +
                "publisher='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}