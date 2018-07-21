package labib.com.nudgememvp.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {

    public static String dateToReadable(long timeInMills) {
        Date dateObject = new Date(timeInMills);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        return simpleDateFormat.format(dateObject);
    }

    public static String timeToReadable(long timeInMills) {
        Date dateObject = new Date(timeInMills);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        return simpleDateFormat.format(dateObject);
    }
}
