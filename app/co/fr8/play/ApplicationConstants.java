package co.fr8.play;

import play.Play;

/**
 * Application constants for a terminal that uses PlayFramework for its MVC
 */
public class ApplicationConstants {

  public static final String CORE_WEBSERVER_URL =
      Play.application().configuration().getString("fr8.terminal.coreWebServerUrl");
}
