package co.fr8.util.net;

import java.util.Map;

/**
 * Interface for the HttpUtils class. This interface defines the methods
 * available to classes which send HTTP requests using a concrete
 * implementation of IHttpUtils
 */
public interface IHttpUtils {

  String post(String url, Map<String, String> Params);

  String get(String url);

}
