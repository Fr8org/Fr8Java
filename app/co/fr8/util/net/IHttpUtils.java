package co.fr8.util.net;

/**
 * Interface for the HttpUtils class. This interface defines the methods
 * available to classes which send HTTP requests using a concrete
 * implementation of IHttpUtils
 */
public interface IHttpUtils {

  public String post();

  public String get();
}
