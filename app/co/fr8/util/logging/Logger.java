package co.fr8.util.logging;

import org.slf4j.LoggerFactory;

/**
 * TODO: Implement
 */
public class Logger {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger("application");

  public static boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  public static void debug(String message) {
    if (logger.isDebugEnabled())
      logger.debug(message);
  }

  public static void debug(String message, Object... args) {
    if (logger.isDebugEnabled())
      logger.debug(message, args);
  }

  public static void debug(String message, Throwable exception) {
    if (logger.isDebugEnabled())
      logger.debug(message, exception);
  }

  public static boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }

  public static void trace(String message) {
    if (logger.isTraceEnabled())
      logger.trace(message);
  }

  public static void trace(String message, Object... args) {
    if (logger.isTraceEnabled())
      logger.trace(message, args);
  }

  public static void trace(String message, Throwable exception) {
    if (logger.isTraceEnabled())
      logger.trace(message, exception);
  }

  public static boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  public static void warn(String message) {
    if (logger.isWarnEnabled())
      logger.warn(message);
  }

  public static void warn(String message, Object... args) {
    if (logger.isWarnEnabled())
      logger.warn(message, args);
  }

  public static void warn(String message, Throwable exception) {
    if (logger.isWarnEnabled())
      logger.warn(message, exception);
  }

  public static boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  public static void error(String message) {
    if (logger.isErrorEnabled())
      logger.error(message);
  }

  public static void error(String message, Throwable exception) {
    if (logger.isErrorEnabled())
      logger.error(message, exception);
  }

  public static boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  public static void info(String message) {
    if (logger.isInfoEnabled())
      logger.info(message);
  }

  public static void info(String message, Object... args) {
    if (logger.isInfoEnabled())
      logger.info(message, args);
  }

  public static void info(String message, Throwable exception) {
    if (logger.isInfoEnabled())
      logger.info(message, exception);
  }
}
