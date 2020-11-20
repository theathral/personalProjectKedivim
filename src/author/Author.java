package author;

import document.Document;
import document.Paper;
import utils.MyUtilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Author {

    private String name;
    private Date dateOfBirth;
    private ArrayList<Document> documents;
    private String description;
    private HashMap<String, Integer> publishers;

    public Author(String name, String dateOfBirth, ArrayList<Document> documents, String description) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setDocuments(documents);
        setDescription(description);
        publishers = new HashMap<>();
    }

    public Author(String name, String dateOfBirth, ArrayList<Document> documents, String description, String publisher) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setDocuments(documents);
        setDescription(description);
        publishers = new HashMap<>();
        addPublisher(publisher);
    }

    public Author(String name, String dateOfBirth, ArrayList<Document> documents, String description, HashMap<String, Integer> publishers) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setDocuments(documents);
        setDescription(description);
        setPublishers(publishers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        this.name = MyUtilities.checkString(name);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        this.dateOfBirth = MyUtilities.checkDate(dateOfBirth);
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) throws IllegalArgumentException {
        this.documents = documents;
    }

    public void addDocument(Document document) throws IndexOutOfBoundsException {
        if (documents.contains(document))
            throw new IllegalArgumentException();

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
        return super.toString();
    }
}
