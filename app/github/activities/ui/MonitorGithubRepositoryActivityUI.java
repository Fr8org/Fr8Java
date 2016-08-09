package github.activities.ui;

import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.controls.impl.RadioButtonGroup;
import co.fr8.data.controls.impl.RadioButtonOption;
import co.fr8.terminal.base.ui.AbstractActivityUI;

import java.util.Arrays;

/**
 * ActivityUI for MonitorGithubRepositoryActivity
 * https://maginot.atlassian.net/wiki/display/SH/Activity%3A+Monitor+Github+Repository
 */
public class MonitorGithubRepositoryActivityUI extends AbstractActivityUI {

  private DropDownList repoList;
  private DropDownList branchList;
  private RadioButtonGroup radioButtonGroup;
  private RadioButtonOption allBranches = new RadioButtonOption();
  private RadioButtonOption selectBranch = new RadioButtonOption();

  public MonitorGithubRepositoryActivityUI() {
    this.repoList = new DropDownList("Select a repository to monitor");
    this.branchList = new DropDownList();
    this.radioButtonGroup = new RadioButtonGroup("Specify Branch");
    allBranches = new RadioButtonOption("all", "All");
    selectBranch = new RadioButtonOption("only", "Only", Arrays.asList(branchList));
    radioButtonGroup.setRadios(Arrays.asList(allBranches, selectBranch));
  }

  @Override
  protected void consolidateControls() {
    if (!controls.contains(repoList))
      controls.add(repoList);
  }

  public DropDownList getRepoList() {
    return repoList;
  }

  public void setRepoList(DropDownList repoList) {
    this.repoList = repoList;
  }

  public RadioButtonGroup getRadioButtonGroup() {
    return radioButtonGroup;
  }

  public void setRadioButtonGroup(RadioButtonGroup radioButtonGroup) {
    this.radioButtonGroup = radioButtonGroup;
  }
}
