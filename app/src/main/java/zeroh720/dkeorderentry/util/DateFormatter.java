package zeroh720.dkeorderentry.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
    public static String getMonthDayFromLong(String time){
        long timeLong = Long.parseLong(time);
        return getMonthDayFromLong(timeLong);
    }

    public static String getMonthDayFromLong(long timeLong){
        Date date = new Date(timeLong);
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd");
        return format.format(date);
    }

    public static String getDayOfWeek(long timeLong){
        Date date = new Date(timeLong);
        SimpleDateFormat format = new SimpleDateFormat("EE");
        return format.format(date);
    }

    public static String getDaysAfter(long timeLong){
        Date dateToday = new Date();
        Date date2 = new Date(timeLong);

        long diff = Math.abs(dateToday.getTime() - date2.getTime());

        long z = 60 * 60 * 1000;
        long y = 24 * 60 * 60 * 1000;
        long x = 7 * 24 * 60 * 60 * 1000;

        int diffMinutes = (int) ((diff / (60 * 1000)) % 60);
        int diffHours = (int)((diff / (60 * 60 * 1000)) % 24);
        int diffDays = (int)((diff / (24 * 60 * 60 * 1000)) % 7);
        int diffWeeks = (int)(diff / (7 * 24 * 60 * 60 * 1000));

        if(diffWeeks > 0){
            return diffWeeks + " weeks ago";
        }else if(diffDays > 0){
            return diffDays + " days ago";
        }else if(diffHours > 0){
            return diffHours + " hours ago";
        }else if(diffMinutes > 0){
            return diffMinutes + " minutes ago";
        }
        return "";
    }

    public static String getTime(long timeLong){
        Date date = new Date(timeLong);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm a");
        return format.format(date);
    }
}
