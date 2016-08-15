package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ControlEvent;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.CheckBox;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.data.interfaces.manifests.CrateDescriptionCM;
import co.fr8.data.interfaces.manifests.ValidationResultCM;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.hub.managers.CrateManager;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.ui.TriggerGithubRepositoryActivityUI;
import github.service.GitHubService;

import java.util.ArrayList;
import java.util.List;

import static github.util.GitHubTerminalConstants.TRIGGER_GITHUB_REPOSITORY_DTO;

/**
 * TODO: Implement
 */
public class TriggerGithubRepositoryActivity extends AbstractTerminalActivity<TriggerGithubRepositoryActivityUI> {

  public TriggerGithubRepositoryActivity() {
    super(TRIGGER_GITHUB_REPOSITORY_DTO);
    this.activityUI = new TriggerGithubRepositoryActivityUI();
  }

  @Override
  protected SolutionPageDTO getDocumentation(String documentationType) {
    Logger.debug("Process getDocumentation placeholder returns null");
    return null;
  }

  @Override
  protected boolean checkAuthentication() {
    Logger.debug("process check authentication placeholder returns true");
    return true;
  }

  @Override
  public void initialize() {
    List<ListItem> repoListItems = GitHubService.getInstance().
        getRepositoriesAsListItems(getActivityContext().getAuthorizationToken().getToken());
    Logger.debug("Setting " + repoListItems.size() + " repositories");
    DropDownList repoList = getActivityUI().getRepoList();
    repoList.setListItems(repoListItems);
    CheckBox issue = getActivityUI().getIssue();
    issue.getEvents().add(ControlEvent.getRequestConfigOnChange());
    CheckBox pullRequest = getActivityUI().getPullRequest();
    pullRequest.getEvents().add(ControlEvent.getRequestConfigOnChange());
    Crate uiCrate = generateStandardConfigurationControlsCrate();
    getStorage().add(uiCrate);
  }

  @Override
  public void followUp() {
    List<ListItem> repoListItems = GitHubService.getInstance().
        getRepositoriesAsListItems(getActivityContext().getAuthorizationToken().getToken());
    DropDownList repoListDropDown = getActivityUI().getRepoList();
    repoListDropDown.setListItems(repoListItems);
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
              if (ControlTypeEnum.CHECKBOX.getFriendlyName().equalsIgnoreCase(control.get("type").asText())) {
                if (control.get("name").textValue().equals(
                    getActivityUI().getIssue().getName()) && control.get("selected").asBoolean() == Boolean.TRUE) {
                  List<FieldDTO> fieldNames = new ArrayList<>();
                  fieldNames.add(new FieldDTO("IssueName", "IssueName", AvailabilityTypeEnum.Always));
                  fieldNames.add(new FieldDTO("IssueDescription", "IssueDescription", AvailabilityTypeEnum.Always));
                  fieldNames.add(new FieldDTO("IssueStatus", "IssueStatus", AvailabilityTypeEnum.Always));
                  CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
                  crateDescriptionDTO.setLabel("Issue Properties");
                  crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
                  crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
                  crateDescriptionDTO.setProducedBy(TRIGGER_GITHUB_REPOSITORY_DTO.getName());
                  crateDescriptionDTO.setFields(fieldNames);
                  CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
                  crateDescriptionCM.addOrUpdate(crateDescriptionDTO);
                  Crate crateDescription = new Crate<>(MT.CrateDescription, crateDescriptionCM);
                  getStorage().add(crateDescription);
                }// end of if
                if (ControlTypeEnum.CHECKBOX.getFriendlyName().equalsIgnoreCase(control.get("type").asText())) {
                  if (control.get("name").textValue().equals(
                      getActivityUI().getPullRequest().getName()) && control.get("selected").asBoolean() == Boolean.TRUE) {
                    List<FieldDTO> fieldNames = new ArrayList<>();
                    fieldNames.add(new FieldDTO("PullRequestNumber", "PullRequestNumber", AvailabilityTypeEnum.Always));
                    fieldNames.add(new FieldDTO("PullRequestNumber", "PullRequestNumber", AvailabilityTypeEnum.Always));
                    fieldNames.add(new FieldDTO("PullRequesterGitHubName", "PullRequesterGitHubName", AvailabilityTypeEnum.Always));
                    fieldNames.add(new FieldDTO("PullRequestStatus", "PullRequestStatus", AvailabilityTypeEnum.Always));
                    CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
                    crateDescriptionDTO.setLabel("Pull Request Properties");
                    crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
                    crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
                    crateDescriptionDTO.setProducedBy(TRIGGER_GITHUB_REPOSITORY_DTO.getName());
                    crateDescriptionDTO.setFields(fieldNames);
                    CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
                    crateDescriptionCM.addOrUpdate(crateDescriptionDTO);
                    Crate crateDescription = new Crate<>(MT.CrateDescription, crateDescriptionCM);
                    getStorage().add(crateDescription);
                  }
                }
              }
            }//end of for
          }//end of if
        } else {
          Logger.warn("No content in the crate: " + crate);
        }
      }//end of if
    }//end of for
//    Crate uiCrate = generateStandardConfigurationControlsCrate();
//    getStorage().add(uiCrate);
  }

  @Override
  public ActivityFunctionalInterface run() {
    Logger.debug("Run placeholder");
    return () -> {
      Logger.debug("Run placeholder git forward");
      return getContainerExecutionContext();
    };
  }

  @Override
  public ActivityFunctionalInterface runChildActivities() {
    Logger.debug("runChildActivities placeholder");
    return () -> {
      Logger.debug("Run placeholder git forward");
      return getContainerExecutionContext();
    };
  }

  @Override
  public void activate() {
    getStorage().add(CrateManager.createStandardEventSubscriptionsCrate("Standard Event Subscriptions", "GitHub", new String[]{"GithubEventReport"}));
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
              if (ControlTypeEnum.CHECKBOX.getFriendlyName().equalsIgnoreCase(control.get("type").asText())) {
                if (control.get("name").textValue().equals(
                    getActivityUI().getIssue().getName()) && control.get("selected").asBoolean() == Boolean.FALSE &&
                    control.get("name").textValue().equals(
                    getActivityUI().getPullRequest().getName()) && control.get("selected").asBoolean() == Boolean.FALSE) {
                  ValidationResultCM validationResultCM = new ValidationResultCM("You must check at least one type of event", "issue", "pullRequest");
                  Crate validationResultCMCrate = new Crate<>(MT.ValidationResults, validationResultCM);
                  getStorage().add(validationResultCMCrate);
                }// end of if
              }
            }//end of for
          }//end of if
        } else {
          Logger.warn("No content in the crate: " + crate);
        }
      }//end of if
    }//end of for
    Logger.debug("MonitorRepositoriesActivity.activate() called");
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
   * <p>
   * Called from AbstractTerminalService#initializeInternalState
   */
  @Override
  protected void initializeActivityState(ActionNameEnum actionName) {
    Logger.debug("initializeActivityState placeholder");
  }
}
