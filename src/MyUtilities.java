import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class MyUtilities {

    public static String checkString(String str) {
        String s = str.trim().toUpperCase();
        if (s.equals("") || s.contains(";"))
            throw new IllegalArgumentException();

        return s;
    }

    public static int checkMin(int num, int min) throws IllegalArgumentException {
        if (num < min)
            throw new IllegalArgumentException();

        return num;
    }

    public static int checkYear(int year) throws IllegalArgumentException {
        if (year > Year.now().getValue() || year < 1500)
            throw new IllegalArgumentException();

        return year;
    }

    public static Date checkDate(Date date) throws IllegalArgumentException {
//        if (year > Year.now().getValue() || year < 1500)
//            throw new IllegalArgumentException();
//
//        return year;
        return null;
    }

    public static String checkInArrayList(String str, ArrayList<String> list) throws IllegalArgumentException, NullPointerException {
        String s = str.trim().toUpperCase();
        if (!list.contains(s))
            throw new IllegalArgumentException();

        return s;
    }

}
