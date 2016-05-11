package co.fr8.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility class to manipulate dates
 */
public class DateUtils {
  private static final SimpleDateFormat shortFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zz");

  public static String getCurrentShortDate() {
    return shortFormat.format(Calendar.getInstance().getTime());
  }
}
