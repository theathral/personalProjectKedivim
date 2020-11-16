import java.util.ArrayList;

public class Book extends Paper {

    private static final int MAX_WRITERS = 5;
    private ArrayList<Writer> writers;

    public Book(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, ArrayList<Writer> writers) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        setWriters(writers);
    }

    public Book(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, Writer writer) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        writers = new ArrayList<>();
        addWriter(writer);
    }

    public ArrayList<Writer> getWriters() {
        return writers;
    }

    public void setWriters(ArrayList<Writer> writers) throws IndexOutOfBoundsException, NullPointerException{
        if (writers.size() >= MAX_WRITERS)
            throw new IndexOutOfBoundsException();

        this.writers = writers;
    }

    public void addWriter(Writer writer) throws IndexOutOfBoundsException {
        if (writers.size() >= MAX_WRITERS)
            throw new IndexOutOfBoundsException();

        writers.add(writer);
    }

    public Writer removeWriter(int index) throws IndexOutOfBoundsException {
        return writers.remove(index);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
