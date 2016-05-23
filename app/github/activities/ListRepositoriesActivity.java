package github.activities;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.terminal.base.CrateSignaller;
import co.fr8.terminal.base.TerminalActivity;
import co.fr8.terminal.infrastructure.ValidationManager;
import co.fr8.terminal.infrastructure.states.ConfigurationRequestType;

/**
 * TODO: Implement
 */
public class ListRepositoriesActivity extends TerminalActivity {

  public ListRepositoriesActivity() {
    super("GitHub Subscribe", false);
  }

  @Override
  protected void initialize(CrateSignaller crateSignaller) {

  }

  @Override
  protected void configure(CrateSignaller crateSignaller, ValidationManager validationManager) {

  }

  @Override
  protected ConfigurationRequestType configurationEvaluationResult(ActivityDTO activityDTO) {
    return ConfigurationRequestType.FOLLOWUP;
  }

  @Override
  protected void registerConfigurationControls() {

  }

  @Override
  protected void runChildActivities() {

  }

  @Override
  protected void runCurrentActivity() {

  }

  @Override
  protected void activate() {

  }

  @Override
  protected void deactivate() {

  }
}
