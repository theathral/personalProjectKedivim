package utils;

import java.time.Year;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class MyUtilities {

    public static String checkString(String str) throws IllegalArgumentException {
        String s = str.trim().toUpperCase();
        if (s.equals(""))
            throw new IllegalArgumentException();

        return s;
    }

    public static int checkMin(int num, int min) throws IllegalArgumentException {
        if (num < min)
            throw new IllegalArgumentException();

        return num;
    }

    public static int checkMax(int num, int max) throws IllegalArgumentException {
        if (num > max)
            throw new IllegalArgumentException();

        return num;
    }

    public static int checkRange(int num, int min, int max) throws IllegalArgumentException {
        checkMin(num, min);
        return checkMax(num, max);
    }

    public static int checkYear(int year) throws IllegalArgumentException {
        if (year > Year.now().getValue() || year < 1500)
            throw new IllegalArgumentException();

        return year;
    }

    public static ZonedDateTime checkDate(String date) throws IllegalArgumentException {
        String[] tokens = date.split("-");

        if (tokens.length != 3)
            throw new IllegalArgumentException();

        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);

        return new GregorianCalendar(year, month - 1, day).toZonedDateTime();
    }
}
