package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.hub.managers.CrateManager;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.service.GitHubService;

import java.util.List;

import static github.util.GitHubTerminalConstants.TRIGGER_GITHUB_REPOSITORY_DTO;

/**
 * @deprecated
 */
public class GitGetRepositoriesActivity extends AbstractRepositoryRetrievalActivity {

  public GitGetRepositoriesActivity() {
    super(TRIGGER_GITHUB_REPOSITORY_DTO);
  }

  @Override
  protected SolutionPageDTO getDocumentation(String documentationType) {
    Logger.debug("Get getDocumentation placeholder returns null");
    return null;
  }

  @Override
  protected boolean checkAuthentication() {
    Logger.debug("Get check authentication placeholder returns true");
    return true;
  }

  @Override
  public ActivityFunctionalInterface run() {
    // TODO here must be code creating the Standard Payload Crate
    // Let's see the contents of that crate for now.
    List<Crate> crates =
        getActivityContext().getActivityPayload().getCrateStorage().getCratesAsList();
    for (Crate crate : crates) {
      Logger.debug("Looking for Standard Payload Data in " + crate.getCrateManifestType());
      if (MT.StandardConfigurationControls.equals(crate.getCrateManifestType())) {
        Logger.debug(JsonUtils.writeObjectAsString(crate.getContent()));
      }
    }
    return () -> {
      Logger.debug("Run placeholder git forward");
      return getContainerExecutionContext();
    };
//    return () -> {
//      Logger.debug("Updating ");
//      JsonNode repoJson =
//          GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());
////      getActivityContext().getActivityPayload();
//      // query for all the repositories and put them in to a standard crate
//      List<FieldDTO> fieldNames = new ArrayList<>();
//      if (repoJson != null && repoJson.isArray()) {
//        Iterator<JsonNode> repos = repoJson.elements();
//        repos.forEachRemaining(repo -> {
//          Logger.debug("Adding " + repo.get("name").asText());
//          fieldNames.add(new FieldDTO("name", repo.get("full_name").asText(), AvailabilityTypeEnum.Always));
//        });
//        StandardPayloadDataCM repoPayloadData = new StandardPayloadDataCM(fieldNames);
//        Crate repositoriesCrate = new Crate(MT.StandardPayloadData);
//        repositoriesCrate.setAvailability(AvailabilityTypeEnum.RunTime);
//        repositoriesCrate.putContent(repoPayloadData);
//        repositoriesCrate.setLabel("GitHub Repositories");
//        getActivityPayload().getCrateStorage().add(repositoriesCrate);
//      }
//    };
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
    // Upon activation, this activity should create an EventSubscription crate looking for GithubEventReports
    getStorage().add(
        CrateManager.createStandardEventSubscriptionsCrate("Github Event Report", "", new String[]{"GithubEventReport"}));
//    GitHubService.getInstance().postWebhookToGithubPullRequests()
    String authToken = getActivityContext().getAuthorizationToken().getToken();
    List<Crate> crates =
        getActivityContext().getActivityPayload().getCrateStorage().getCratesAsList();
    for (Crate crate : crates) {
      Logger.debug("Looking for Standard Payload Data in " + crate.getCrateManifestType());
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
                  GitHubService.getInstance().postWebhookToGithubPullRequests(selectedRepo.getValue(), authToken,
                      getActivityContext().getAuthorizationToken().getExternalAccountId());
                  Logger.debug("Successfully sent the webhook to github to: ", selectedRepo.getValue(), " repository");
                }
              }
            }
          }
        } else {
          Logger.warn("No content in the crate: " + crate);
        }
      }
    }
//    Crate standardEventSubscriptionCrate = new Crate(MT.StandardEventSubscription);
//    standardEventSubscriptionCrate.setAvailability(AvailabilityTypeEnum.Always);
//    standardEventSubscriptionCrate.setLabel("GitHub Repositories");
//    Logger.debug("GitGetRepositoriesActivity.activate() called");
//    JsonNode repoJson =
//        GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());
//    List<FieldDTO> fieldNames = new ArrayList<>();
//    if (repoJson != null && repoJson.isArray()) {
//      Iterator<JsonNode> repos = repoJson.elements();
//      repos.forEachRemaining(repo -> {
//        Logger.debug("Adding " + repo.get("name").asText());
//        fieldNames.add(new FieldDTO("name", repo.get("full_name").asText(), AvailabilityTypeEnum.Always));
//      });
//      StandardPayloadDataCM repoPayloadData = new StandardPayloadDataCM(fieldNames);
//      Crate repositoriesCrate = new Crate(MT.StandardPayloadData);
//      repositoriesCrate.setAvailability(AvailabilityTypeEnum.RunTime);
//      repositoriesCrate.putContent(repoPayloadData);
//      repositoriesCrate.setLabel("GitHub Repositories");
//      getStorage().add(repositoriesCrate);
//    }
  }

  @Override
  protected void afterRun() {
    Logger.debug("GitGetRepositoriesActivity.afterRun() called");
  }

  @Override
  protected void afterDeactivate() {
    Logger.debug("GitGetRepositoriesActivity.afterDeactivate() called");
  }

  @Override
  protected boolean afterActivate() {
    Logger.debug("GitGetRepositoriesActivity.afterActivate() called returning true");
    return true;
  }

  @Override
  public void deactivate() {
    Logger.debug("Deactivation called. Removing Standard Event Subscription Crate.");
    getStorage().remove(MT.StandardEventReport.getFriendlyName());
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
  protected void afterConfigure(ConfigurationRequestType
                                    configurationRequestType, Exception e) {
    Logger.debug("after configure placeholder calls super");
    super.afterConfigure(configurationRequestType, e);
  }

  @Override
  protected boolean beforeConfigure(ConfigurationRequestType
                                        configurationRequestType) {
    Logger.debug("before configure returns true placeholder");
    return true;
  }


  /**
   * TODO Implement (First priority)
   * Method abstraction to configure settings unique to the activity implementation
   * <p>
   * Called from AbstractTerminalService#initializeInternalState
   */
  @Override
  protected void initializeActivityState(ActionNameEnum actionName) {
    Logger.debug("initializeActivityState placeholder");
  }
}
