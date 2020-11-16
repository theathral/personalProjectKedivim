import java.util.ArrayList;

public class Book extends Paper {

    private static final int MAX_WRITERS = 5;
    private ArrayList<Writer> writers;

    public Book(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, ArrayList<Writer> writers) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        this.writers = writers;
    }

    public ArrayList<Writer> getWriters() {
        return writers;
    }

    public void setWriters(ArrayList<Writer> writers) {
        this.writers = writers;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
