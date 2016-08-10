package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.data.interfaces.manifests.CrateDescriptionCM;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.ui.TriggerPullRequestActivityUI;
import github.service.GitHubService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static github.util.GitHubTerminalConstants.TRIGGER_GITHUB_REPOSITORY_DTO;

/**
 * @deprecated
 * Abstract class that initializes Github Activities with Authenticated user's
 * Github repository names set as a DropDownList
 */
abstract class AbstractRepositoryRetrievalActivity
    extends AbstractTerminalActivity<TriggerPullRequestActivityUI> {

  AbstractRepositoryRetrievalActivity(ActivityTemplateDTO activityTemplate) {
    super(activityTemplate);
    this.activityUI = new TriggerPullRequestActivityUI();
  }

  /**
   * Initialize method overrides AbstractTerminalActivity's abstract initialize method.
   */
  @Override
  public void initialize() {
    List<ListItem> repoListItems = getListItems();
    List<FieldDTO> fieldNames = new ArrayList<>();
    fieldNames.add(new FieldDTO("Number", "Number", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("PullRequestName", "PullRequestName", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("RequesterGitHubName", "RequesterGitHubName", AvailabilityTypeEnum.Always));
    fieldNames.add(new FieldDTO("Status", "Status", AvailabilityTypeEnum.Always));
    CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
    crateDescriptionDTO.setLabel("Repository Properties");
    crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
    crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
    crateDescriptionDTO.setProducedBy(TRIGGER_GITHUB_REPOSITORY_DTO.getName());
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
   * Initialize method overrides AbstractTerminalActivity's abstract initialize method.
   */
  @Override
  public void followUp() {
    List<ListItem> repoListItems = getListItems();
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
    crateDescriptionDTO.setProducedBy(TRIGGER_GITHUB_REPOSITORY_DTO.getName());
    crateDescriptionDTO.setFields(fieldNames);
    CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
    crateDescriptionCM.addOrUpdate(crateDescriptionDTO);
    Crate crate = new Crate<>(MT.CrateDescription, crateDescriptionCM);
    getStorage().add(crate);
    Crate uiCrate = generateStandardConfigurationControlsCrate();
    getStorage().add(uiCrate);
  }

  private List<ListItem> getListItems() {
    JsonNode repoJson =
        GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());
    List<ListItem> repoListItems = new ArrayList<>();
    if (repoJson != null && repoJson.isArray()) {
      Iterator<JsonNode> repositories = repoJson.elements();
      repositories.forEachRemaining(repo -> {
        Logger.debug("Adding " + repo.get("name").asText());
        repoListItems.add(new ListItem(repo.get("name").asText(), repo.get("full_name").asText()));
      });
    }
    return repoListItems;
  }

}
