package controllers;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.Fr8DataDTO;
import co.fr8.data.interfaces.dto.Fr8HubSecurityDTO;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.GitHubTerminal;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Map;

import static co.fr8.play.ApplicationConstants.*;

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
    ActivityDTO resultDTO = getResultActivityDTO(request(), ActionNameEnum.ACTIVATE);
    return ok(JsonUtils.writeObjectAsString(resultDTO));
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result deactivateActivities() {
    ActivityDTO resultDTO = getResultActivityDTO(request(), ActionNameEnum.DEACTIVATE);
    return ok(JsonUtils.writeObjectAsString(resultDTO));
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result runActivities() {
    ActivityDTO resultDTO = getResultActivityDTO(request(), ActionNameEnum.RUN);
    return ok(JsonUtils.writeObjectAsString(resultDTO.getContainerExecutionContext()));
  }

  /**
   * TODO: DOCUMENT
   * @return
   */
  public Result configureActivities() {
    ActivityDTO resultDTO = getResultActivityDTO(request(), ActionNameEnum.CONFIGURE);
    return ok(JsonUtils.writeObjectAsString(resultDTO));
//    Logger.debug("Call to /activities/configure");
//    JsonNode jsonBody = request().body().asJson();
//    if (jsonBody == null) {
//      return ok(createErrorResponse("Unable to extract activity DTO"));
//    }
//    Logger.debug("Configure body: " + jsonBody);
//    JsonNode activityJson = jsonBody.get("ActivityDTO");
//    String containerId = jsonBody.get("ContainerId").asText();
//    Logger.debug("ContainerId: " + containerId + " activityDTOString " + activityJson + " JsonBody " + jsonBody);
//    ActivityDTO activityDTO =
//        JsonUtils.writeStringToActivityDTO(activityJson.toString());
//    if (activityDTO == null) {
//      Logger.error("Unable to extract ActivityPayload");
//      return ok(createErrorResponse( "Unable to parse form data to ActivityPayload"));
//    }
//    ActivityDTO resultDTO = terminal.handleFr8Request(ActionNameEnum.CONFIGURE, new Fr8DataDTO(activityDTO, containerId), request().queryString());
//    String responseString = JsonUtils.writeObjectAsString(resultDTO);
//    Logger.debug("handleFr8Request returned: " + responseString);
//    Logger.debug("Configure result DTO: " + resultDTO.toString());
//    return ok(responseString);
  }

  /**
   * Handle the request to /documentation
   *
   * @return a JSON response containing a crate with a DocumentationDTO
   */
  public Result documentation() {
    ActivityDTO resultDTO = getResultActivityDTO(request(), ActionNameEnum.DOCUMENTATION);
    return ok(JsonUtils.writeObjectAsString(resultDTO));
//    Logger.warn("Documentation Placeholder method called");
//    JsonNode jsonBody = request().body().asJson();
//    if (jsonBody == null) {
//      return ok(createErrorResponse("Unable to extract activity DTO"));
//    }
//    Logger.debug("Configure body: " + jsonBody);
//    JsonNode activityJson = jsonBody.get("ActivityDTO");
//    String containerId = jsonBody.get("ContainerId").asText();
//    ActivityDTO activityDTO = JsonUtils.writeStringToActivityDTO(activityJson.toString());
//    if (activityDTO == null) {
//      Logger.error("Unable to extract ActivityPayload");
//      return ok(createErrorResponse( "Unable to parse form data to ActivityPayload"));
//    }
//    ActivityDTO resultDTO = terminal.handleFr8Request(ActionNameEnum.DOCUMENTATION, new Fr8DataDTO(activityDTO, containerId), request().queryString());
//    return ok("message", "Placeholder");
  }

  private ActivityDTO getResultActivityDTO(Http.Request request, ActionNameEnum actionNameEnum) {
    JsonNode requestBody = request.body().asJson();
    Logger.debug(actionNameEnum.getPrettyActionName() + " Activities called: " + requestBody);
    ActivityDTO actDTO =
        JsonUtils.writeNodeAsObject(requestBody.get("ActivityDTO"), ActivityDTO.class);
    ActivityDTO resultDTO;
    if (actionNameEnum != ActionNameEnum.RUN)
      resultDTO = terminal.handleFr8Request(actionNameEnum, null,
          new Fr8DataDTO(actDTO, requestBody.get("ContainerId").toString()), request().queryString());
    else
      resultDTO = terminal.handleFr8Request(actionNameEnum, getFr8HubSecurity(request().headers()),
          new Fr8DataDTO(actDTO, requestBody.get("ContainerId").toString()), request().queryString());
    if (resultDTO == null) {
      Logger.warn("handleFr8Request returned null\n returning request body ActivityDTO " +
          actDTO);
      resultDTO = actDTO;
    }
    Logger.debug(actionNameEnum.getPrettyActionName() + " Activities result DTO: " + resultDTO.toString());
    return resultDTO;
  }

  private Fr8HubSecurityDTO getFr8HubSecurity(Map<String, String[]> requestHeader) {
    //TODO Add error checking here.
    return new Fr8HubSecurityDTO(requestHeader.get(FR8_USER_ID)[0], requestHeader.get(FR8_HUB_CALLBACK_URL)[0], requestHeader.get(FR8HUBCALLBACKSECRET)[0]);
  }

}
