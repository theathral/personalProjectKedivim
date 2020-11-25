package author;

import document.DocInterface;
import utils.MyUtilities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class {@code Author} stores an author with all its personal information (name, date of birth, description),
 * documents and publishers.
 */
public class Author implements Serializable {

    private String name;
    private ZonedDateTime dateOfBirth;
    private final ArrayList<DocInterface> documents;
    private String description;
    private final HashMap<String, Integer> publishers;

    /**
     * Constructor which stores the Author's {@code name}, {@code dateOfBirth} and {@code description}.
     *
     * @param name        Author's name
     * @param dateOfBirth Author's date of birth
     * @param description Author's description
     */
    public Author(String name, ZonedDateTime dateOfBirth, String description) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        documents = new ArrayList<>();
        setDescription(description);
        publishers = new HashMap<>();
    }

    /**
     * Getter for the Author's name.
     *
     * @return The Author's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Author's name.
     * Throws {@code IllegalArgumentException} if the {@code name} is an empty String.
     *
     * @param name Author's name
     * @throws IllegalArgumentException If the {@code name} is an empty String
     */
    public void setName(String name) throws IllegalArgumentException {
        this.name = MyUtilities.checkString(name);
    }

    /**
     * Set the Author's date of birth.
     *
     * @param dateOfBirth Author's date of birth
     */
    public void setDateOfBirth(ZonedDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter for the Author's documents {@code ArrayList} instance.
     *
     * @return The Author's documents
     */
    public ArrayList<DocInterface> getDocuments() {
        return documents;
    }

    /**
     * Adds a new document to the documents' {@code ArrayList} instance.
     * Throws {@code IllegalArgumentException} if the {@code document} already exists in the {@code ArrayList} instance.
     *
     * @param document The {@code Document} that will be added
     * @throws IndexOutOfBoundsException If the {@code document} already exists in the {@code ArrayList} instance
     */
    public void addDocument(DocInterface document) throws IndexOutOfBoundsException {
        if (documents.contains(document))
            throw new IndexOutOfBoundsException();

        documents.add(document);
    }

    /**
     * Removes an existing document from the documents {@code ArrayList} instance.
     * Throws {@code IndexOutOfBoundsException} if the {@code document} does not exist in the {@code ArrayList} instance.
     *
     * @param document The {@code Document} that will be removed
     * @throws IndexOutOfBoundsException If the {@code document} does not exist in the {@code ArrayList} instance
     */
    public void removeDocument(DocInterface document) throws IndexOutOfBoundsException {
        if (!documents.contains(document))
            throw new IndexOutOfBoundsException();

        documents.remove(document);
    }

    /**
     * Set the Author's description.
     * Throw {@code IllegalArgumentException} if the {@code description} is an empty string.
     *
     * @param description Author's description
     * @throws IllegalArgumentException If the {@code description} is an empty string
     */
    public void setDescription(String description) throws IllegalArgumentException {
        this.description = MyUtilities.checkString(description);
    }

    /**
     * Adds a new publisher to the publishers' {@code HashMap} instance.
     * If the publisher is new, it add a new {@code (key, value)} set to the {@code HashMap}.
     * Otherwise, it increases the publisher's value by 1.
     * Throws {@code IllegalArgumentException} if the {@code description} is an empty string.
     *
     * @param name The name of the publisher that will be added
     * @throws IllegalArgumentException If the {@code description} is an empty string
     */
    public void addPublisher(String name) throws IllegalArgumentException {
        String n = MyUtilities.checkString(name);

        publishers.put(n, publishers.getOrDefault(n, 0) + 1);
    }

    /**
     * Removes an existing publisher from the publishers' {@code HashMap} instance.
     * If the publisher has value equals to 1, it removes the {@code (key, value)} from the {@code HashMap} instance.
     * Otherwise, it decreases the publisher's value by 1.
     * Throws {@code IllegalArgumentException} if the {@code description} is an empty string.
     * Throws {@code IllegalArgumentException} if the {@code description} is an empty string.
     *
     * @param name The name of the publisher that will be removed
     * @throws IllegalArgumentException If the {@code name} is an empty string
     * @throws NullPointerException     If the {@code name} does not exist in the  {@code HashMap} instance.
     */
    public void deceaseOrRemovePublisher(String name) throws IllegalArgumentException, NullPointerException {
        String n = MyUtilities.checkString(name);

        if (publishers.get(n) <= 1) {
            publishers.remove(n);
            return;
        }
        publishers.put(n, publishers.get(n) - 1);
    }

    /**
     * Indicates whether an {@code Author} object is "equal to" this one according to the object's name.
     *
     * @param obj The reference object with which to compare.
     * @return {@code True} if this object is the same as the obj argument; {@code False} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Author))
            throw new IllegalArgumentException();

        return name.equals(((Author) obj).name.trim().toUpperCase());
    }

    /**
     * Returns a string representation of the object.
     * It includes the Author's personal information (name, date of birth, description), document names and publishers.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Name: ").append(name);
        str.append(" (")
                .append(dateOfBirth.getDayOfMonth())
                .append("-")
                .append(dateOfBirth.getMonth())
                .append("-")
                .append(dateOfBirth.getYear())
                .append(")").append(System.lineSeparator());
        str.append("Description: ").append(description).append(System.lineSeparator());

        str.append("Documents (").append(documents.size()).append("): ");
        documents.forEach(doc -> str.append(doc.getCode()).append(", "));
        str.append(System.lineSeparator());

        str.append("Publishers (").append(publishers.size()).append("): ");
        publishers.keySet().forEach(pub -> str.append(pub).append(", "));
        str.append(System.lineSeparator());

        return str.toString();
    }
}
