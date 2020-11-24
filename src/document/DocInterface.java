package document;

public interface DocInterface {

    String getCode();

    String getTitle();

    void add() throws IndexOutOfBoundsException;

    void remove() throws IndexOutOfBoundsException;

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();
}

