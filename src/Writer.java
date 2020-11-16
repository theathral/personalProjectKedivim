import java.util.Date;

public class Writer {

    private String name;
    private Date dateOfBirth;
    private int bookCounter; // remove
    private String description;
    private int publisherCounter; // remove

    public Writer(String name, Date dateOfBirth, int bookCounter, String description, int publisherCounter) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setBookCounter(bookCounter);
        setDescription(description);
        setPublisherCounter(publisherCounter);
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

    public void setDateOfBirth(Date dateOfBirth) throws IllegalArgumentException {
        this.dateOfBirth = MyUtilities.checkDate(dateOfBirth);
    }

    public int getBookCounter() {
        return bookCounter;
    }

    public void setBookCounter(int bookCounter) throws IllegalArgumentException {
        this.bookCounter = MyUtilities.checkMin(bookCounter, 0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublisherCounter() {
        return publisherCounter;
    }

    public void setPublisherCounter(int publisherCounter) throws IllegalArgumentException {
        this.publisherCounter = MyUtilities.checkMin(publisherCounter, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Writer))
            throw new IllegalArgumentException();

        return name.equals(((Writer) obj).name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
