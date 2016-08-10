package controllers;

import co.fr8.data.interfaces.dto.AuthorizationToken;
import co.fr8.data.interfaces.dto.ExternalAuthDTO;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import github.GitHubTerminal;
import org.apache.commons.lang3.StringUtils;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

/**
 * TODO: Document
 */
public class AuthenticationController extends AbstractTerminalController<GitHubTerminal> {

  public AuthenticationController() {
    this.terminal = new GitHubTerminal();
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result authenticate() {
    return ok("authenticate placeholder");
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result authenticateInternal() {
    return ok("authenticate placeholder");
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result initialUrl() {
    Logger.debug("Call to initialUrl");
    return ok(JsonUtils.writeObjectAsString(terminal.generateExternalAuthUrl()));
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result authenticateToken() {
    Logger.debug("Call to authenticateToken");
    DynamicForm form = Form.form().bindFromRequest();

    debugForm(form);

    // TODO: Make constants
    String requestQueryString = form.get("RequestQueryString");

    if (StringUtils.isBlank(requestQueryString)) {
      Logger.warn("No RequestQueryString sent in /authenticate/token request");

      AuthorizationToken response = new AuthorizationToken();
      response.setError("An error occurred while trying to authorize, please try again later.");

      return badRequest(JsonUtils.writeObjectAsString(response));
    }

    // TODO: Make constants
    String fr8UserId = form.get("Fr8UserId");
    response().setHeader("Authorization", terminal.generateHMACHeader(fr8UserId));

    return ok(JsonUtils.writeObjectAsString(terminal.authenticateToken(
        new ExternalAuthDTO(requestQueryString, fr8UserId))));
  }

  /**
   * TODO: Document
   *
   * @return
   */
  public Result requestUrl() {
    Logger.debug("Call to request url");
    return ok(JsonUtils.writeObjectAsString(terminal.generateExternalAuthUrl()));
  }
}
