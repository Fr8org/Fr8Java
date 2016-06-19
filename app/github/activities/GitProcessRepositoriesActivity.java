package github.activities;

import co.fr8.data.interfaces.dto.SolutionPageDTO;
import co.fr8.terminal.base.AbstractTerminalActivity;
import co.fr8.terminal.base.ActionNameEnum;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;
import co.fr8.util.logging.Logger;
import github.activities.ui.GitProcessActivityUI;

import static github.util.GitHubTerminalConstants.GITHUB_PROCESS_EXAMPLE_DTO;

/**
 * TODO: Implement
 */
public class GitProcessRepositoriesActivity extends AbstractTerminalActivity<GitProcessActivityUI> {

  public GitProcessRepositoriesActivity() {
    super(GITHUB_PROCESS_EXAMPLE_DTO);
    this.activityUI = new GitProcessActivityUI();
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
    Logger.debug("About to get repositories using token " +
        getActivityContext().getAuthorizationToken().getToken());

  }

  @Override
  public void followUp() {
    Logger.debug("Followup placeholder");
  }

  @Override
  public ActivityFunctionalInterface run() {
    Logger.debug("Run placeholder");
    return () -> {
      Logger.debug("Run placeholder git process");
      getActivityContext().getActivityPayload();
    };
  }

  @Override
  public ActivityFunctionalInterface runChildActivities() {
    Logger.debug("runChildActivities placeholder");
    return () -> {
      Logger.debug("RunChildActivities placeholder git process");
      getActivityContext().getActivityPayload();
    };
  }

  @Override
  public void activate() {

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
   *
   * Called from AbstractTerminalService#initializeInternalState
   */
  @Override
  protected void initializeActivityState(ActionNameEnum actionName) {
    Logger.debug("initializeActivityState placeholder");
  }
}
