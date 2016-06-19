package controllers;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.Fr8DataDTO;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.GitHubTerminal;
import play.mvc.Result;

/**
 * TODO: Document
 */
public class ActivitiesController extends AbstractTerminalController<GitHubTerminal> {

  public ActivitiesController() {
    terminal = new GitHubTerminal();
  }


  /**
   * TODO: DOCUMENT
   * @param activity
   * @return
   */
  public Result activities(String activity) {
    return ok("activity placeholder");
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result activateActivities() {
    JsonNode requestBody = request().body().asJson();

    Logger.debug("Activate Activities called: \n " + requestBody);

    ActivityDTO actDTO =
        JsonUtils.writeNodeAsObject(requestBody.get("ActivityDTO"), ActivityDTO.class);

    Logger.debug("ActivityDTO: \n" + actDTO);

    ActivityDTO resultDTO = terminal.handleFr8Request(ActionNameEnum.ACTIVATE,
        new Fr8DataDTO(actDTO, requestBody.get("ContainerId").toString()), request().queryString());

    if (resultDTO == null) {
      Logger.warn("handleFr8Request returned null\n returning request body ActivityDTO " +
          actDTO);

      resultDTO = actDTO;
    }

    Logger.debug("Activate result DTO: " + resultDTO.toString());

    return ok(JsonUtils.writeObjectAsString(resultDTO));
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result deactivateActivities() {
    JsonNode requestBody = request().body().asJson();

    Logger.debug("Deactivate Activities called: \n " + requestBody);

    return ok();
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result runActivities() {
    JsonNode requestBody = request().body().asJson();

    Logger.debug("Run Activities called: \n " + requestBody);

    return ok();
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result configureActivities() {
    Logger.debug("Call to /activities/configure");
//    DynamicForm form = Form.form().bindFromRequest();
    JsonNode jsonBody = request().body().asJson();

    if (jsonBody == null) {
      return ok(createErrorResponse("Unable to extract activity DTO"));
    }

    Logger.debug("Configure body: " + jsonBody);

    JsonNode activityJson = jsonBody.get("ActivityDTO");
    String containerId = jsonBody.get("ContainerId").asText();

    Logger.debug("ContainerId: " + containerId + " activityDTOString " +
        activityJson + " JsonBody " + jsonBody);

    ActivityDTO activityDTO =
        JsonUtils.writeStringToActivityDTO(activityJson.toString());

    if (activityDTO == null) {
      Logger.error("Unable to extract ActivityPayload");
      return ok(createErrorResponse( "Unable to parse form data to ActivityPayload"));
    }

    ActivityDTO resultDTO = terminal.handleFr8Request(ActionNameEnum.CONFIGURE,
        new Fr8DataDTO(activityDTO, containerId), request().queryString());

    String responseString = JsonUtils.writeObjectAsString(resultDTO);

    Logger.debug("handleFr8Request returned: " + responseString);

    return ok(responseString);
  }


}
