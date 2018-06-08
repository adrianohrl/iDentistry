package tech.adrianohrl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class CalendarFormat {
    
    private final static DateFormat DATE_ONLY_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    private final static DateFormat DATE_FORMATTER = new SimpleDateFormat("d MMM yyyy");
    private final static DateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
    
    public static String formatDate(Date date) {
        return date != null ? DATE_ONLY_FORMATTER.format(date) : "";
    }
    
    public static String formatDate(Calendar date) {
        return date != null ? CalendarFormat.formatDate(date.getTime()) : "";
    }
    
    public static String formatTime(Date time) {
        return time != null ? TIME_FORMATTER.format(time) : "";
    }
    
    public static String formatTime(Calendar time) {
        return time != null ? CalendarFormat.formatTime(time.getTime()) : "";
    }
    
    public static String format(Date calendar) {
        return calendar != null ? CalendarFormat.format(calendar, " at ", true) : "";
    }
    
    public static String format(Calendar calendar) {
        return calendar != null ? CalendarFormat.format(calendar, " at ", true) : "";
    }
    
    public static String format(Date calendar, String separator, boolean dateFirst) {
        if (calendar == null) {
            return "";
        }
        if (dateFirst) {
            return DATE_FORMATTER.format(calendar) + separator + TIME_FORMATTER.format(calendar);
        }
        return TIME_FORMATTER.format(calendar) + separator + DATE_FORMATTER.format(calendar);
    }
    
    public static String format(Calendar calendar, String separator, boolean dateFirst) {
        return CalendarFormat.format(calendar.getTime(), separator, dateFirst);
    }
    
}
