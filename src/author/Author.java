package author;

import document.DocInterface;
import utils.MyUtilities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Author implements Serializable {

    private String name;
    private ZonedDateTime dateOfBirth;
    private final ArrayList<DocInterface> documents;
    private String description;
    private final HashMap<String, Integer> publishers;

    public Author(String name, ZonedDateTime dateOfBirth, String description) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        documents = new ArrayList<>();
        setDescription(description);
        publishers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        this.name = MyUtilities.checkString(name);
    }

    public void setDateOfBirth(ZonedDateTime dateOfBirth) throws IllegalArgumentException {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<DocInterface> getDocuments() {
        return documents;
    }

    public void addDocument(DocInterface document) throws IndexOutOfBoundsException {
        if (documents.contains(document))
            throw new IndexOutOfBoundsException();

        documents.add(document);
    }

    public void removeDocument(DocInterface document) throws IndexOutOfBoundsException {
        documents.remove(document);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPublisher(String name) throws IllegalArgumentException {
        String n = MyUtilities.checkString(name);

        publishers.put(n, publishers.getOrDefault(n, 0) + 1);
    }

    public void deceaseOrRemovePublisher(String name) throws IllegalArgumentException, NullPointerException {
        String n = MyUtilities.checkString(name);

        if (publishers.get(n) <= 1) {
            publishers.remove(n);
            return;
        }
        publishers.put(n, publishers.get(n) - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Author))
            throw new IllegalArgumentException();

        return name.equals(((Author) obj).name.trim().toUpperCase());
    }

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
