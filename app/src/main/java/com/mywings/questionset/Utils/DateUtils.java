package com.mywings.questionset.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Tatyabhau Chavan on 2/24/2016.
 */
public class DateUtils {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

    public static String formatDate(final Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatDate(final String milliseconds) {
        Date date = new Date(Long.parseLong(milliseconds));
        return DATE_FORMAT.format(date);
    }

    public static Date parseDate(final String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
