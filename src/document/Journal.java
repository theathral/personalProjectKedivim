package document;

import utils.MyUtilities;

public class Journal extends Paper {

    private int volume;
    private int issue;

    public Journal(String code, String title, int year, int numOfPages, int numOfCopies, String publisher, String ISBN, int volume, int issue) {
        super(code, title, year, numOfPages, numOfCopies, publisher, ISBN);
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
