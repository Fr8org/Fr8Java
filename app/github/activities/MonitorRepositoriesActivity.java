package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.play.ApplicationConstants;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import java.util.List;

import static github.util.GitHubTerminalConstants.GITHUB_LIST_REPOS_TEMPLATE_DTO;

/**
 * TODO: Implement
 */
public class MonitorRepositoriesActivity extends AbstractRepositoryRetrievalActivity {

  /**
   *
   */
  public MonitorRepositoriesActivity() {
    super(GITHUB_LIST_REPOS_TEMPLATE_DTO);
  }

  /**
   *
   * @param documentationType
   * @return
   */
  @Override
  protected SolutionPageDTO getDocumentation(String documentationType) {
    Logger.debug("getDocumentation placeholder returns null");
    return null;
  }

  /**
   *
   * @return
   */
  @Override
  protected boolean checkAuthentication() {
    Logger.debug("check authentication placeholder returns true");
    return true;
  }
  /**
   *
   */
  @Override
  public void followUp() {
    Logger.debug("Followup placeholder");
  }

  /**
   *
   * @return
   */
  @Override
  public ActivityFunctionalInterface run() {

    Logger.debug("Run placeholder");
    return () -> {
      Logger.debug("Executing run ActivityFunctionalInterface");
      getActivityContext().getActivityPayload();
    };
  }

  /**
   *
   * @return
   */
  @Override
  public ActivityFunctionalInterface runChildActivities() {
    Logger.debug("runChildActivities placeholder");
    return () -> {
      Logger.debug("Executing runChildActivities ActivityFunctionalInterface");
      getActivityContext().getActivityPayload();
    };
  }

  /**
   *
   */
  @Override
  public void activate() {

    Logger.debug("MonitorRepositoriesActivity.activate() called");

    List<Crate> crates =
        getActivityContext().getActivityPayload().getCrateStorage().getCratesAsList();

    for (Crate crate : crates) {
      Logger.debug("Looking for StandardConfigurationControls in " + crate.getCrateManifestType());
      if (MT.StandardConfigurationControls.equals(crate.getCrateManifestType())) {
        if (crate.getRawContent() != null) {
          JsonNode controls = crate.getRawContent();

          if (controls.has("Controls")) {
            for (JsonNode control : controls.get("Controls")) {

              Logger.debug("Checking control of type: " + control.get("type"));
              if (ControlTypeEnum.DROPDOWN_LIST.getFriendlyName().equalsIgnoreCase(control.get("type").asText())) {
                DropDownList dropDownList =
                    JsonUtils.writeNodeAsObject(control, DropDownList.class);

                if (dropDownList != null) {
                  ListItem selectedRepo = dropDownList.findBySelected();

                  Logger.debug("Found selected ListItem: " + selectedRepo);

                  ObjectNode jsonParams = Json.newObject();
                  jsonParams.put("subscribed", true);
                  jsonParams.put("ignored", false);

                  String putResponse =
                      HttpUtils.putJson(ApplicationConstants.REPOS_URL + "/" + selectedRepo.getValue() + "/subscription?access_token=" + getActivityContext().getAuthorizationToken().getToken(), jsonParams);

                  Logger.debug("got response: " + putResponse);

                }
              }
            }

          }

        } else {
          Logger.warn("No content in the crate: " + crate);
        }

      }
    }
  }

  @Override
  protected void afterRun() {

  }

  @Override
  protected void afterDeactivate() {

  }

  @Override
  protected boolean afterActivate() {
    return false;
  }

  @Override
  public void deactivate() {
    Logger.debug("deactivate placeholder");
  }

  @Override
  protected boolean beforeRun() {
    Logger.debug("before run returns false placeholder");
    return false;
  }

  @Override
  protected boolean beforeDeactivate() {
    Logger.debug("before deactivate placeholder returns false");
    return false;
  }

  @Override
  protected boolean beforeActivate() {
    Logger.debug("before activate placeholder returns true");
    return true;
  }

  @Override
  protected void afterConfigure(ConfigurationRequestType configurationRequestType, Exception e) {
    Logger.debug("after configure placeholder calls super");
    super.afterConfigure(configurationRequestType, e);
  }

  @Override
  protected boolean beforeConfigure(ConfigurationRequestType configurationRequestType) {
    Logger.debug("before configure returns true placeholder");
    return true;
  }


  /**
   * Method abstraction to configure settings unique to the activity implementation
   *
   * Called from AbstractTerminalService#initializeInternalState
   */
  @Override
  protected void initializeActivityState(ActionNameEnum actionName) {
    Logger.debug("initializeActivityState placeholder");
  }
}
