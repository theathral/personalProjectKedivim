package document;

import utils.MyUtilities;

public class Journal extends Paper {

    private int volume;
    private int issue;

    public Journal(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, int volume, int issue) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        setVolume(volume);
        setIssue(issue);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) throws IllegalArgumentException {
        this.volume = MyUtilities.checkMin(volume, 1);
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) throws IllegalArgumentException {
        this.issue = MyUtilities.checkMin(issue, 1);
    }

    @Override
    public void add() {
    }

    @Override
    public void remove() {
    }

    @Override
    public String toString() {
        return "Journal: " + super.toString()
                + "Volume: " + volume + System.lineSeparator()
                + "Issue: " + issue + System.lineSeparator();
    }
}
