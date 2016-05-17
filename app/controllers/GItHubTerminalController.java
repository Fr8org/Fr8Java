package controllers;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.AuthorizationTokenDTO;
import co.fr8.data.interfaces.dto.ExternalAuthDTO;
import co.fr8.data.interfaces.dto.Fr8DataDTO;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import github.GitHubTerminal;
import org.apache.commons.lang3.StringUtils;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import static co.fr8.play.ApplicationConstants.AUTH_TOKEN_PATH;
import static co.fr8.play.ApplicationConstants.GITHUB_OAUTH_BASE_URL;
import static co.fr8.play.ApplicationConstants.TERMINAL_NAME;

/**
 * TODO: Implement
 */
public class GitHubTerminalController extends Controller {

  private GitHubTerminal gitHubTerminal = new GitHubTerminal();

  public Result discover() {
    Logger.debug("Call to terminals/discover");

    StandardFr8TerminalCM discoverCrateManifest = gitHubTerminal.discover();

    Logger.debug("Returning terminal info: " + discoverCrateManifest);

    return ok(JsonUtils.writeManifestJsonString(discoverCrateManifest));
  }

  public Result activities(String activity) {
    return ok("activity placeholder");
  }

  public Result authenticate() {
    return ok("authenticate placeholder");
  }

  public Result authenticateInternal() {
    return ok("authenticate placeholder");
  }

  public Result initialUrl() {
    Logger.debug("Call to initialUrl");
    return ok(JsonUtils.writeObjectAsString(gitHubTerminal.generateExternalAuthUrl()));
  }

  public Result authenticateToken() {
    Logger.debug("Call to authenticateToken");
    DynamicForm form = Form.form().bindFromRequest();

    debugForm(form);

    // TODO: Make constants
    String requestQueryString = form.get("RequestQueryString");

    AuthorizationTokenDTO response;
    if (StringUtils.isBlank(requestQueryString)) {
      Logger.warn("No RequestQueryString sent in /authenticate/token request");

      response = new AuthorizationTokenDTO();
      response.setError("An error occurred while trying to authorize, please try again later.");

      return badRequest(JsonUtils.writeObjectAsString(response));
    }

    String fr8UserId = form.get("Fr8UserId");
    response().setHeader("Authorization", gitHubTerminal.generateHMACHeader(fr8UserId));

    return ok(JsonUtils.writeObjectAsString(gitHubTerminal.authenticateToken(
        // TODO: Make constants
        new ExternalAuthDTO(requestQueryString, fr8UserId))));
  }

  public Result configureActivities() {
    Logger.debug("Call to /activities/configure");
    DynamicForm form = Form.form().bindFromRequest();
    JsonNode jsonBody = request().body().asJson();

    debugForm(form);

    String activityDTOString = form.get("ActivityDTO");
    String containerId = form.get("ContainerId");

    Logger.debug("ContainerId: " + containerId + " activityDTOString " + activityDTOString + " JsonBody " + jsonBody);

    if (jsonBody == null) {
      Logger.error("There is no activityDTO in the form: " + form);
      return badRequest(createErrorResponse("Unable to extract activity DTO"));
    }

    ActivityDTO activityDTO = JsonUtils.writeStringToActivityDTO(jsonBody.get("ActivityDTO").toString());

    if (activityDTO == null) {
      Logger.error("Unable to extract ActivityDTO");
      return badRequest(createErrorResponse( "Unable to parse form data to ActivityDTO"));
    }

    ActivityDTO resultDTO =
        (ActivityDTO) gitHubTerminal.handleFr8Request(TERMINAL_NAME, "configure",
            new Fr8DataDTO(activityDTO, containerId));

    return ok(JsonUtils.writeObjectAsString(resultDTO));
  }

  private void debugForm(DynamicForm form) {
    if (Logger.isDebugEnabled()) {
      form.data().forEach((k,v) -> {
        Logger.debug("form contains key " + k + " with value " + v);
      });
    }
  }

  private ObjectNode createErrorResponse(String message) {
    return Json.newObject().put("ErrorMessage", message);

  }
}
