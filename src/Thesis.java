import java.util.ArrayList;
import java.util.Arrays;

public class Thesis extends Article {

    private Writer writer;
    private String supervisor;
    private String type;
    private String department;
    private String university;

    public Thesis(String title, int year, int numOfPages, int numOfCopies, String code, Writer writer, String supervisor, String type, String department, String university) {
        super(title, year, numOfPages, numOfCopies, code);
        setWriter(writer);
        setSupervisor(supervisor);
        setType(type);
        setDepartment(department);
        setUniversity(university);
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) throws NullPointerException {
        this.writer = writer;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String t = type.trim().toLowerCase();

        if (!t.equals("bachelor") && !t.equals("master") && !t.equals("doctoral"))
            throw new IllegalArgumentException();

        this.type = MyUtilities.checkInArrayList(type, new ArrayList<>(Arrays.asList("BACHELOR", "MASTER", "DOCTORAL")));
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) throws IllegalArgumentException {
        this.department = MyUtilities.checkString(department);
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) throws IllegalArgumentException {
        this.university = MyUtilities.checkString(university);
    }

    @Override
    public String toString() {
        return "Thesis{" +
                "writer=" + writer +
                ", supervisor='" + supervisor + '\'' +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}
