package com.app.studio.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Yen
 */
public class DateUtils {

    private static final String format = "yyyy-MM-dd";

    public static Date parse(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    public static String parse(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String today() {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}
