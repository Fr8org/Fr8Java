package github.activities;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ListItem;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.CrateDescriptionDTO;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.data.interfaces.manifests.CrateDescriptionCM;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.hub.managers.CrateManager;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.ui.ListActivityUI;
import github.service.GitHubService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static github.util.GitHubTerminalConstants.GITHUB_LIST_REPOS_TEMPLATE_DTO;

/**
 * TODO: Document
 */
abstract class AbstractRepositoryRetrievalActivity
    extends AbstractTerminalActivity<ListActivityUI> {

  AbstractRepositoryRetrievalActivity(ActivityTemplateDTO activityTemplate) {
    super(activityTemplate);
    this.activityUI = new ListActivityUI();
  }

  /**
   *
   */
  @Override
  public void initialize() {

    JsonNode repoJson =
        GitHubService.getInstance().getRepositoryJsonForUser(getActivityContext().getAuthorizationToken().getToken());

    List<ListItem> repoList = new ArrayList<>();
    List<FieldDTO> fieldNames = new ArrayList<>();
    if (repoJson != null && repoJson.isArray()) {
      Iterator<JsonNode> repos = repoJson.elements();

      JsonNode repo;

      while (repos.hasNext()) {

        repo = repos.next();

        if (fieldNames.size() == 0) {
          Iterator<String> fieldNameIterator = repo.fieldNames();
          String fieldName;
          while(fieldNameIterator.hasNext() && StringUtils.isNotBlank(fieldName = fieldNameIterator.next())) {
            Logger.debug("Adding field: " + fieldName);
            fieldNames.add(new FieldDTO(fieldName, fieldName, AvailabilityTypeEnum.Always));
          }
        }

        repoList.add(new ListItem(repo.get("name").asText(), repo.get("full_name").asText()));
      }
    }

    Logger.debug("Setting " + repoList.size() + " repositories");

    getActivityUI().getRepoList().setListItems(repoList);
    getStorage().add(CrateManager.createStandardEventSubscriptionsCrate("Standard Event Subscriptions", "GitHub", new String[]{"GitHub repository monitor"}));

    if (fieldNames.size() > 0) {
      CrateDescriptionDTO crateDescriptionDTO = new CrateDescriptionDTO();
      crateDescriptionDTO.setLabel("Repository Properties");
      crateDescriptionDTO.setManifestId(MT.StandardPayloadData.getId());
      crateDescriptionDTO.setManifestType(MT.StandardPayloadData.getFriendlyName());
      crateDescriptionDTO.setProducedBy(GITHUB_LIST_REPOS_TEMPLATE_DTO.getName());
      crateDescriptionDTO.setFields(fieldNames);

      CrateDescriptionCM crateDescriptionCM = new CrateDescriptionCM();
      crateDescriptionCM.addOrUpdate(crateDescriptionDTO);

      Crate crate = new Crate<>(MT.CrateDescription, crateDescriptionCM);

      getStorage().add(crate);
    }
  }


}
