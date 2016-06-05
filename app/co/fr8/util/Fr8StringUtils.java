package co.fr8.util;

import co.fr8.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.xerces.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for manipulating String objects
 */
public class Fr8StringUtils {
  private static final String MD5_HASH = "MD5";

  /**
   * Helper method that hashes the input String using the MD5 algorithm and
   * returns the Base64 encoded representation of that String
   * @param rawString the String to hash and encode
   * @return a Base64 encoded String object
   */
  public static String base64MD5String(String rawString) {
    byte[] content;
    if (StringUtils.isNotBlank(rawString)) {
      content = rawString.getBytes();
    } else {
      content = new byte[] { };
    }

    try {
      MessageDigest md5 = MessageDigest.getInstance(MD5_HASH);
      md5.update(content, 0, content.length);

      Logger.debug("Converted content to md5: " + new String(content));

    } catch (NoSuchAlgorithmException e) {
      Logger.error("Unable to create MD5 hash", e);
    }

    Logger.debug("Returning base 64 hash: " + new String(content));

    return Base64.encode(content);

  }
}
