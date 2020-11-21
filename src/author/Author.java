package author;

import document.Document;
import utils.MyUtilities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

public class Author implements Serializable {

    private String name;
    private ZonedDateTime dateOfBirth;
    private ArrayList<Document> documents;
    private String description;
    private HashMap<String, Integer> publishers;

    public Author(String name, ZonedDateTime dateOfBirth, String description) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        documents = new ArrayList<>();
        setDescription(description);
        publishers = new HashMap<>();
    }

    public Author(String name, ZonedDateTime dateOfBirth, ArrayList<Document> documents, String description) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setDocuments(documents);
        setDescription(description);
        publishers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        this.name = MyUtilities.checkString(name);
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        this.dateOfBirth = MyUtilities.checkDate(dateOfBirth);
    }

    public void setDateOfBirth(ZonedDateTime dateOfBirth) throws IllegalArgumentException {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) throws IllegalArgumentException {
        this.documents = documents;
    }

    public void addDocument(Document document) throws IndexOutOfBoundsException {
        if (documents.contains(document))
            throw new IndexOutOfBoundsException();

        documents.add(document);
    }

    public void removeDocument(Document document) throws IndexOutOfBoundsException {
        documents.remove(document);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Integer> getPublisherCounters() {
        return publishers;
    }

    public void setPublishers(HashMap<String, Integer> publishers) {
        this.publishers = publishers;
    }

    public void addPublisher(String name) throws IllegalArgumentException {
        String n = MyUtilities.checkString(name);

        if (publishers.containsKey(n))
            publishers.put(n, publishers.getOrDefault(n, 0) + 1);
    }

    public void deceaseOrRemovePublisher(String name) throws IllegalArgumentException, NullPointerException {
        String n = MyUtilities.checkString(name);

        if (publishers.get(n) <= 1)
            publishers.remove(n);

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

        str.append("Documents: ");
        documents.forEach(doc -> str.append(doc.getCode()).append(", "));
        str.append(System.lineSeparator());

        str.append("Publishers: ");
        publishers.keySet().forEach(pub -> str.append(pub).append(", "));
        str.append(System.lineSeparator());

        return str.toString();
    }
}
