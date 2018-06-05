/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author adrianohrl
 */
public class CalendarFormat {
    
    private final static DateFormat dateOnlyFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private final static DateFormat dateFormatter = new SimpleDateFormat("d MMM yyyy");
    private final static DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
    
    public static String formatDate(Date date) {
        return date != null ? dateOnlyFormatter.format(date) : "";
    }
    
    public static String formatDate(Calendar date) {
        return date != null ? CalendarFormat.formatDate(date.getTime()) : "";
    }
    
    public static String formatTime(Date time) {
        return time != null ? timeFormatter.format(time) : "";
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
            return dateFormatter.format(calendar) + separator + timeFormatter.format(calendar);
        }
        return timeFormatter.format(calendar) + separator + dateFormatter.format(calendar);
    }
    
    public static String format(Calendar calendar, String separator, boolean dateFirst) {
        return CalendarFormat.format(calendar.getTime(), separator, dateFirst);
    }
    
}
