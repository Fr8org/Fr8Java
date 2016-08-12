package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.controls.impl.TextSource;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import github.activities.request.CreateGithubIssueRequest;
import github.activities.ui.CreateGithubIssueActivityUI;
import github.service.GitHubService;
import play.libs.Json;

import java.util.List;

import static github.util.GitHubTerminalConstants.CREATE_GITHUB_ISSUE_DTO;

/**
 * TODO: Implement
 */
public class CreateGithubIssueActivity extends AbstractTerminalActivity<CreateGithubIssueActivityUI> {

  /**
   *
   */
  public CreateGithubIssueActivity() {
    super(CREATE_GITHUB_ISSUE_DTO);
    this.activityUI = new CreateGithubIssueActivityUI();
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

  @Override
  public void initialize() {
    List<ListItem> repoListItems = GitHubService.getInstance().
        getRepositoriesAsListItems(getActivityContext().getAuthorizationToken().getToken());
    Logger.debug("Setting " + repoListItems.size() + " repositories");
    DropDownList repoListDropDown = getActivityUI().getRepositories();
    repoListDropDown.setListItems(repoListItems);
    Crate uiCrate = generateStandardConfigurationControlsCrate();
    getStorage().add(uiCrate);
  }

  /**
   *
   */
  @Override
  public void followUp() {
  }

  /**
   *
   * @return
   */
  @Override
  public ActivityFunctionalInterface run() {
    Logger.debug(getActivityContext().getActivityPayload().toString());
    //1. get the crates
    //2. find the entered results.
    //3. update below code.
    ListItem selectedRepo = null;
    String title;
    String body;
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
                  selectedRepo = dropDownList.findBySelected();
                  Logger.debug("Found selected ListItem: " + selectedRepo);
                  ObjectNode jsonParams = Json.newObject();
                  jsonParams.put("subscribed", true);
                  jsonParams.put("ignored", false);
                }//end of if
              }//end of if
              title = getSpecificTextSourceTextValue(control, "title");

              body = getSpecificTextSourceTextValue(control, "body");
              if (null != selectedRepo) {
                String patchResponse = GitHubService.getInstance().createGithubIssue(
                    new CreateGithubIssueRequest(getActivityContext().getAuthorizationToken().getToken(), getActivityContext().getAuthorizationToken().getExternalAccountName(), selectedRepo.getKey(), title, body));
                Logger.debug("got response: " + patchResponse);
              }
            }//end of for
          }//end of if
        } else {
          Logger.warn("No content in the crate: " + crate);
        }
      }//end of if
    }//end of for
    return () -> {
    };
  }

  private String getSpecificTextSourceTextValue(JsonNode control, String name) {
    String toReturn = "";
    if (ControlTypeEnum.TEXT_SOURCE.getFriendlyName().equalsIgnoreCase(control.get("type").asText()) &&
        control.get("name").textValue().equals(name)) {
      TextSource issueNumberTS = JsonUtils.writeNodeAsObject(control, TextSource.class);
      if (issueNumberTS != null)
        toReturn = issueNumberTS.getTextValue();
    }
    return toReturn;
  }

  /**
   *
   * @return
   */
  @Override
  public ActivityFunctionalInterface runChildActivities() {
    return () -> {
    };
  }

  /**
   *
   */
  @Override
  public void activate() {
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
    if (getOperationalState() != null) {
      Crate operationalStateCrate = new Crate<>(MT.OperationalStatus, "OperationalStatus", getOperationalState());
      getStorage().add(operationalStateCrate);
    }
    Logger.debug("initializeActivityState placeholder");
  }


}
