package document;

import utils.MyUtilities;

public abstract class Paper extends Document {

    private String publisher;
    private String ISBN;

    public Paper(String code, String title, int year, int numOfPages, int numOfCopies, String publisher, String ISBN) {
        super(code, title, year, numOfPages, numOfCopies);
        setPublisher(publisher);
        setISBN(ISBN);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) throws IllegalArgumentException {
        this.publisher = MyUtilities.checkString(publisher);
    }

    public void setISBN(String ISBN) throws IllegalArgumentException {
        this.ISBN = MyUtilities.checkString(ISBN);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Paper))
            return super.equals(obj);

        return super.equals(obj) && ISBN.equals(((Paper) obj).ISBN.trim().toUpperCase());
    }

    @Override
    public String toString() {
        return super.toString()
                + "Publisher: " + publisher + System.lineSeparator()
                + "ISBN: " + ISBN + System.lineSeparator();
    }
}
