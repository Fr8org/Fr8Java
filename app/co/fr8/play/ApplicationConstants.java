package co.fr8.play;

import play.Play;

/**
 * Application constants for a terminal that uses PlayFramework for its MVC
 */
public class ApplicationConstants {

  public static final String CORE_WEBSERVER_URL =
      Play.application().configuration().getString("fr8.terminal.coreWebServerUrl");

  public static final String TERMINAL_HOST =
      Play.application().configuration().getString("fr8.terminal.host");

  public static final String GITHUB_OAUTH_BASE_URL =
      Play.application().configuration().getString("github.oauth.url");

  public static final String TERMINAL_NAME =
      Play.application().configuration().getString("fr8.terminal.name");

  public static final String TERMINAL_ID =
      Play.application().configuration().getString("fr8.terminal.id");

  public static final String TERMINAL_SECRET =
      Play.application().configuration().getString("fr8.terminal.secret");

  public static final String AUTH_PATH =
      Play.application().configuration().getString("github.auth.path");

  public static final String AUTH_DOMAIN =
      Play.application().configuration().getString("github.auth.domain");

  public static final String AUTH_APP_KEY =
      Play.application().configuration().getString("github.auth.key");

  public static final String CLIENT_SECRET =
      Play.application().configuration().getString("github.client.secret");

  public static final String CLIENT_ID =
      Play.application().configuration().getString("github.client.id");

  public static final String AUTH_TOKEN_PATH =
      Play.application().configuration().getString("github.oauth.tokenUrl");

  public static final String OAUTH_CODE_PARAM = "code";

  public static final String OAUTH_STATE_PARAM = "state";

}
