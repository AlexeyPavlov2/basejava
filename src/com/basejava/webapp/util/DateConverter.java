package com.basejava.webapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static final String formatString = "dd.MM.yyyy";

    private DateConverter() {
    }


    public static String formatLocalDate(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate stringToLocalDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(formatString));
    }
}
