package utils;

import java.time.Year;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class MyUtilities {

    /**
     * Checks if a {@code String} is not empty and return the trimmed and uppercase version of it.
     * If the {@code String} is empty, it throws IllegalArgumentException.
     *
     * @param str The {@code String} that the function checks
     * @return The trimmed and uppercase version of the {@code String}
     * @throws IllegalArgumentException If the {@code String} is empty
     */
    public static String checkString(String str) throws IllegalArgumentException {
        String s = str.trim().toUpperCase();
        if (s.equals(""))
            throw new IllegalArgumentException();

        return s;
    }

    /**
     * Checks if an {@code Integer} is allowed based on its value and return the same number.
     * If {@code num < min}, it throws IllegalArgumentException.
     *
     * @param num The number that is checked
     * @param min The min number that is allowed
     * @return The same number
     * @throws IllegalArgumentException If {@code num < min}
     */
    public static int checkMin(int num, int min) throws IllegalArgumentException {
        if (num < min)
            throw new IllegalArgumentException();

        return num;
    }

    /**
     * Checks if an {@code Integer} is allowed based on its value and return the same number.
     * If {@code num > max}, it throws IllegalArgumentException.
     *
     * @param num The number that is checked
     * @param max The max number that is allowed
     * @return The same number
     * @throws IllegalArgumentException If {@code num > max}
     */
    public static int checkMax(int num, int max) throws IllegalArgumentException {
        if (num > max)
            throw new IllegalArgumentException();

        return num;
    }

    /**
     * Checks if an {@code Integer} is allowed based on its value and return the same number.
     * If {@code min < num < max}, it throws IllegalArgumentException.
     *
     * @param num The number that is checked
     * @param min The min number that is allowed
     * @param max The max number that is allowed
     * @return The same number
     * @throws IllegalArgumentException If {@code min < num < max}
     */
    public static int checkRange(int num, int min, int max) throws IllegalArgumentException {
        checkMin(num, min);
        return checkMax(num, max);
    }

    /**
     * Checks if a {@code year} is allowed based on its value and return the same number.
     * If {@code 1500 < year < current_year}, it throws IllegalArgumentException.
     *
     * @param year The year that is checked
     * @return The same year
     * @throws IllegalArgumentException If {@code 1500 < year < current_year}
     */
    public static int checkYear(int year) throws IllegalArgumentException {
        if (year > Year.now().getValue() || year < 1500)
            throw new IllegalArgumentException();

        return year;
    }

    /**
     * Converts a {@code String} date (dd-mm-YYYY) to {@code ZonedDateTime}.
     *
     * @param date The year that is checked
     * @return The date in as an {@code ZonedDateTime} instance
     */
    public static ZonedDateTime convertToDate(String date) throws IllegalArgumentException {
        String[] tokens = date.split("-");

        if (tokens.length != 3)
            throw new IllegalArgumentException();

        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);

        return new GregorianCalendar(year, month - 1, day).toZonedDateTime();
    }
}
