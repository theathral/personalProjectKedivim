package utils;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static int checkMax(int num, int max) throws IllegalArgumentException {
        if (num > max)
            throw new IllegalArgumentException();

        return num;
    }

    public static int checkYear(int year) throws IllegalArgumentException {
        if (year > Year.now().getValue() || year < 1500)
            throw new IllegalArgumentException();

        return year;
    }

    public static Date checkDate(String date) throws IllegalArgumentException {
        String[] tokens = date.split("-");

        if (tokens.length != 3)
            throw new IllegalArgumentException();

        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);

        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    public static String checkInArrayList(String str, ArrayList<String> list) throws IllegalArgumentException, NullPointerException {
        String s = str.trim().toUpperCase();
        if (!list.contains(s))
            throw new IllegalArgumentException();

        return s;
    }

}
