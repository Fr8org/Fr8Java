package co.fr8.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility class that provides helper methods for handling Collections
 */
public class CollectionUtils {

  /**
   * Helper method that evaluates emptiness of an array by checking for null
   * value or the array length is equal to 0
   *
   * @param array the array to evaluate
   * @return true if the array is null or the length is equal to 0, false
   * otherwise
   */
  public static boolean isEmpty(Object[] array) {
    return (array == null) || (array.length <= 0);
  }

  /**
   * Helper method that evaluates if an array is not empty by checking that
   * it is not null and has a length greater than 0
   * <p>
   * This method negates the return value of the isEmpty() method
   *
   * @param array the array to evaulate
   * @return true if the array is not null or the array length is greater than
   * 0, false otherwise
   */
  public static boolean isNotEmpty(Object[] array) {
    return !isEmpty(array);
  }

  public static boolean isEmpty(Collection collection) {
    return collection == null || collection.size() <= 0;
  }

  public static boolean isNotEmpty(Collection collection) {
    return !isEmpty(collection);
  }

  public static boolean isEmpty(Map map) {
    return map == null || map.size() <= 0;
  }

  public static boolean isNotEmpty(Map map) {
    return !isEmpty(map);
  }

  public static <T> String toString(List<T> list) {
    String ret = "";
    if (isEmpty(list))
      return ret;
    for (T t : list) {
      ret += t.toString() + "\n";
    }
    return ret;
  }

}
