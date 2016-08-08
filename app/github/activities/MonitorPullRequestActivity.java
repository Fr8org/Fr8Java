package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.data.interfaces.manifests.CrateDescriptionCM;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.play.ApplicationConstants;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import github.activities.ui.MonitorPullRequestActivityUI;
import github.service.GitHubService;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

import static github.util.GitHubTerminalConstants.MONITOR_PULL_REQUEST_DTO;

/**
 * TODO: Implement
 */
public class MonitorPullRequestActivity extends AbstractTerminalActivity<MonitorPullRequestActivityUI> {

  /**
   *
   */
  public MonitorPullRequestActivity() {
    super(MONITOR_PULL_REQUEST_DTO);
    this.activityUI = new MonitorPullRequestActivityUI();
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
        getRepositoriesAsListItems( getActivityContext().getAuthorizationToken().getToken());
    List<FieldDTO> fieldNames = new ArrayList<>();
    fieldNames.add(new FieldDTO("Number", "Number", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("PullRequestName", "PullRequestName", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("RequesterGitHubName", "RequesterGitHubName", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("Status", "Status", AvailabilityTypeEnum.Always));
    CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
    crateDescriptionDTO.setLabel("Repository Properties");
    crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
    crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
    crateDescriptionDTO.setProducedBy(MONITOR_PULL_REQUEST_DTO.getName());
    crateDescriptionDTO.setFields(fieldNames);
    CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
    crateDescriptionCM.addOrUpdate(crateDescriptionDTO);
    Crate crate = new Crate<>(MT.CrateDescription, crateDescriptionCM);
    getStorage().add(crate);
    Logger.debug("Setting " + repoListItems.size() + " repositories");
    DropDownList repoListDropDown = getActivityUI().getRepoList();
    repoListDropDown.setListItems(repoListItems);
//    repoListDropDown.getEvents().add(ControlEvent.getRequestConfigOnChange());
    Crate uiCrate = generateStandardConfigurationControlsCrate();
    getStorage().add(uiCrate);
//    getStorage().add(CrateManager.createStandardEventSubscriptionsCrate("Standard Event Subscriptions", "GitHub", new String[]{"GitHub repository monitor"}));
  }

  /**
   *
   */
  @Override
  public void followUp() {
    List<ListItem> repoListItems = GitHubService.getInstance().
        getRepositoriesAsListItems( getActivityContext().getAuthorizationToken().getToken());
    DropDownList repoListDropDown = getActivityUI().getRepoList();
    repoListDropDown.setListItems(repoListItems);
    List<FieldDTO> fieldNames = new ArrayList<>();
    fieldNames.add(new FieldDTO("Number", "Number", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("PullRequestName", "PullRequestName", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("RequesterGitHubName", "RequesterGitHubName", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("Status", "Status", AvailabilityTypeEnum.Always));
    CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
    crateDescriptionDTO.setLabel("Repository Properties");
    crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
    crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
    crateDescriptionDTO.setProducedBy(MONITOR_PULL_REQUEST_DTO.getName());
    crateDescriptionDTO.setFields(fieldNames);
    CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
    crateDescriptionCM.addOrUpdate(crateDescriptionDTO);
    Crate crate = new Crate<>(MT.CrateDescription, crateDescriptionCM);
    getStorage().add(crate);
    Crate uiCrate = generateStandardConfigurationControlsCrate();
    getStorage().add(uiCrate);
  }

  /**
   *
   * @return
   */
  @Override
  public ActivityFunctionalInterface run() {

    Logger.debug("Run placeholder");
    Logger.debug("Executing run ActivityFunctionalInterface");
    Logger.debug("ENTERING HERE CENK");
    Logger.debug(getActivityContext().getActivityPayload().toString());
    return () -> {
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
