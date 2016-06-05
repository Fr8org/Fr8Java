package github.activities;

import co.fr8.data.controls.ListItem;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.play.ApplicationConstants;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.ui.ListActivityUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static github.util.GithubTerminalConstants.GITHUB_LIST_REPOS_TEMPLATE_DTO;

/**
 * TODO: Implement
 */
public class ListRepositoriesActivity extends AbstractTerminalActivity<ListActivityUI> {

  public ListRepositoriesActivity() {
    super(GITHUB_LIST_REPOS_TEMPLATE_DTO);
    this.activityUI = new ListActivityUI();
  }

  @Override
  protected SolutionPageDTO getDocumentation(String documentationType) {
    Logger.debug("getDocumentation placeholder returns null");
    return null;
  }

  @Override
  protected boolean checkAuthentication() {
    Logger.debug("check authentication placeholder returns true");
    return true;
  }

  @Override
  protected ConfigurationRequestType getConfigurationRequestType() {
    Logger.debug("get configuration request type returns placeholder: " + ConfigurationRequestType.INITIAL);
    return ConfigurationRequestType.INITIAL;
  }

  @Override
  public void initialize() {
    Logger.debug("About to get repositories using token " +
        getActivityContext().getAuthorizationToken().getToken());

    String repoResponse = HttpUtils.get(ApplicationConstants.REPOS_URL + "?access_token=" +
        getActivityContext().getAuthorizationToken().getToken());

    JsonNode repoJson = JsonUtils.writeStringToObject(repoResponse);

    List<ListItem> reposList = new ArrayList<>();
    if (repoJson != null && repoJson.isArray()) {
      Iterator<JsonNode> repos = repoJson.elements();

      JsonNode repo;
      while (repos.hasNext()) {
        repo = repos.next();
        reposList.add(new ListItem(repo.get("name").asText(), repo.get("id").asText()));
      }
    }

    Logger.debug("Setting " + reposList.size() + " repositories");

    getActivityUI().getRepoList().setListItems(reposList);
  }

  @Override
  public void followUp() {
    Logger.debug("Followup placeholder");
  }

  @Override
  public void run() {
    Logger.debug("Run placeholder");
  }

  @Override
  public void runChildActivities() {
    Logger.debug("runChildActivities placeholder");
  }

  @Override
  public void activate() {
    Logger.debug("activate placeholder");
  }

  @Override
  public void deactivate() {
    Logger.debug("deactivate placeholder");
  }

  @Override
  protected void afterRun(Exception e) {
    Logger.debug("after run placeholder");
  }

  @Override
  protected boolean beforeRun() {
    Logger.debug("before run returns false placeholder");
    return false;
  }

  @Override
  protected void afterDeactivate(Exception e) {
    Logger.debug("after deactivate placeholder");
  }

  @Override
  protected boolean beforeDeactivate() {
    Logger.debug("before deactivate placeholder returns false");
    return false;
  }

  @Override
  protected boolean afterActivate(Exception e) {
    Logger.debug("after activate placeholder returns false");
    return false;
  }

  @Override
  protected boolean beforeActivate() {
    Logger.debug("before activate placeholder returns false");
    return false;
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

  @Override
  protected boolean isInvalidTokenException(Exception e) {
    Logger.debug("isInvalidTokenException placeholder returns false");
    return false;
  }

  @Override
  protected void initializeInternalState() {
    Logger.debug("isInitializeInternalState placeholder");
  }
}
