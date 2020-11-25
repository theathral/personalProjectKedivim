package document;

/**
 * Interface {@code DocInterface} creates an interface for all the documents.
 */
public interface DocInterface {

    /**
     * Getter for the DocInterface's code.
     *
     * @return The DocInterface's code
     */
    String getCode();

    /**
     * Getter for the DocInterface's title.
     *
     * @return The DocInterface's title
     */
    String getTitle();

    /**
     * Getter for the DocInterface's year of creation.
     *
     * @return The DocInterface's year of creation
     */
    int getYear();

    /**
     * Standard procedure when a new {@code DocInterface} instance is created or updated.
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    void add() throws IndexOutOfBoundsException;

    /**
     * Standard procedure when an {@code DocInterface} instance is deleted
     *
     * @throws IndexOutOfBoundsException If something gets wrong during the procedure
     */
    void remove() throws IndexOutOfBoundsException;

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    int hashCode();

    /**
     * Indicates whether a {@code DocInterface} object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return {@code True} if this object is the same as the obj argument; {@code False} otherwise.
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    String toString();
}
