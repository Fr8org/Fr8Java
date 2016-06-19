package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.data.interfaces.manifests.CrateDescriptionCM;
import co.fr8.data.interfaces.manifests.StandardPayloadDataCM;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.hub.managers.CrateManager;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.service.GitHubService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static github.util.GitHubTerminalConstants.GITHUB_GET_EXAMPLE_DTO;

/**
 * TODO: Implement
 */
public class GitGetRepositoriesActivity extends AbstractRepositoryRetrievalActivity {

  public GitGetRepositoriesActivity() {
    super(GITHUB_GET_EXAMPLE_DTO);
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
  public void initialize() {
    JsonNode repoJson =
        GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());

    List<FieldDTO> fieldNames = new ArrayList<>();
    if (repoJson != null && repoJson.isArray()) {
      Iterator<JsonNode> repos = repoJson.elements();

      repos.forEachRemaining(repo -> {
        Logger.debug("Adding " + repo.get("name").asText());
        fieldNames.add(new FieldDTO("name", repo.get("full_name").asText(), AvailabilityTypeEnum.Always));
      });

      CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
      crateDescriptionDTO.setLabel("Repositories");
      crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
      crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
      crateDescriptionDTO.setProducedBy(GITHUB_GET_EXAMPLE_DTO.getName());
      crateDescriptionDTO.setFields(fieldNames);

      CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
      crateDescriptionCM.addOrUpdate(crateDescriptionDTO);

      Crate crate = new Crate<>(MT.CrateDescription, crateDescriptionCM);

      getStorage().add(crate);
    }

    Logger.debug("Setting " + fieldNames.size() + " repositories");

//    getActivityUI().getRepoList().setListItems(repoList);
    getStorage().add(CrateManager.createStandardEventSubscriptionsCrate("Standard Event Subscriptions", "GitHub", new String[]{"GitHub repository monitor"}));

  }

  @Override
  public void followUp() {
    Logger.debug("Followup placeholder");
  }

  @Override
  public ActivityFunctionalInterface run() {
    return () -> {
      Logger.debug("Running git get");
//      getActivityContext().getActivityPayload();
      // query for all the repositories and put them in to a standard crate
      JsonNode repoJson =
          GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());


      List<FieldDTO> fieldNames = new ArrayList<>();
      if (repoJson != null && repoJson.isArray()) {
        Iterator<JsonNode> repos = repoJson.elements();

        repos.forEachRemaining(repo -> {
          Logger.debug("Adding " + repo.get("name").asText());
          fieldNames.add(new FieldDTO("name", repo.get("full_name").asText(), AvailabilityTypeEnum.Always));
        });

        StandardPayloadDataCM repoPayloadData = new StandardPayloadDataCM(fieldNames);

        Crate repositoriesCrate = new Crate(MT.StandardPayloadData);
        repositoriesCrate.setAvailability(AvailabilityTypeEnum.RunTime);
        repositoriesCrate.putContent(repoPayloadData);
        repositoriesCrate.setLabel("GitHub Repositories");

        getActivityPayload().getCrateStorage().add(repositoriesCrate);
      }
    };
  }

  @Override
  public ActivityFunctionalInterface runChildActivities() {
    Logger.debug("runChildActivities placeholder");
    return () -> {
      Logger.debug("RunChildActivities placeholder git get");
    };
  }

  @Override
  public void activate() {

    Logger.debug("GitGetRepositoriesActivity.activate() called");
    JsonNode repoJson =
        GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());


    List<FieldDTO> fieldNames = new ArrayList<>();
    if (repoJson != null && repoJson.isArray()) {
      Iterator<JsonNode> repos = repoJson.elements();

      repos.forEachRemaining(repo -> {
        Logger.debug("Adding " + repo.get("name").asText());
        fieldNames.add(new FieldDTO("name", repo.get("full_name").asText(), AvailabilityTypeEnum.Always));
      });

      StandardPayloadDataCM repoPayloadData = new StandardPayloadDataCM(fieldNames);

      Crate repositoriesCrate = new Crate(MT.StandardPayloadData);
      repositoriesCrate.setAvailability(AvailabilityTypeEnum.RunTime);
      repositoriesCrate.putContent(repoPayloadData);
      repositoriesCrate.setLabel("GitHub Repositories");

      getStorage().add(repositoriesCrate);
    }

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
   * Method abstraction to configure settings unique to the activity implementation
   * <p>
   * Called from AbstractTerminalService#initializeInternalState
   */
  @Override
  protected void initializeActivityState(ActionNameEnum actionName) {
    Logger.debug("initializeActivityState placeholder");
  }
}
