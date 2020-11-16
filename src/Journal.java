public class Journal extends Paper {

    private int volume;
    private int issue;

    public Journal(String title, int year, int numOfPages, int numOfCopies, String code, String publisher, String ISBN, int volume, int issue) {
        super(title, year, numOfPages, numOfCopies, code, publisher, ISBN);
        this.volume = volume;
        this.issue = issue;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "volume=" + volume +
                ", issue=" + issue +
                '}';
    }
}
