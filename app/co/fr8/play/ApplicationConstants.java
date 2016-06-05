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

  public static final String HUB_API_VERSION =
      Play.application().configuration().getString("fr8.hub.apiVersion");

  public static final String HUB_API_PATH =
      CORE_WEBSERVER_URL + "/api/" + HUB_API_VERSION;

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

  public static final String API_HOST =
      Play.application().configuration().getString("github.api.host");

  public static final String USER_URL =
      Play.application().configuration().getString("github.api.userPath");

  public static final String REPOS_URL =
      Play.application().configuration().getString("github.api.reposPath");

  public static final String OAUTH_CODE_PARAM = "code";

  public static final String OAUTH_STATE_PARAM = "state";

  public static final String LIST_REPOSITORIES_ACTIVITY_NAME =
      Play.application().configuration().getString("github.terminals.subscribe.name");

}
