package co.fr8.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility class to manipulate dates
 */
public class DateUtils {
  private static final SimpleDateFormat SHORT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zz");

  /**
   * Formats the date using the SHORT_FORMAT constant
   *
   * @return a String of the date in a human readable format
   */
  public static String getCurrentShortDate() {
    return SHORT_FORMAT.format(Calendar.getInstance().getTime());
  }
}
