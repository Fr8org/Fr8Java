package github.activities;

import co.fr8.data.controls.ListItem;
import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.logging.Logger;
import github.activities.ui.UpdateGithubIssueActivityUI;
import github.service.GitHubService;

import java.util.List;

import static github.util.GitHubTerminalConstants.UPDATE_GITHUB_ISSUE_DTO;

/**
 * TODO: Implement
 */
public class UpdateGithubIssueActivity extends AbstractTerminalActivity<UpdateGithubIssueActivityUI> {

  /**
   *
   */
  public UpdateGithubIssueActivity() {
    super(UPDATE_GITHUB_ISSUE_DTO);
    this.activityUI = new UpdateGithubIssueActivityUI();
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
//    GitHubService.getInstance().updateGithubIssue(getActivityContext().getAuthorizationToken().getToken(),
//        getActivityContext().getAuthorizationToken().getExternalAccountName(), );
    return () -> {
    };
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
    Logger.debug("initializeActivityState placeholder");
  }


}
