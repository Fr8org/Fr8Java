package github.activities.ui;

import co.fr8.data.controls.impl.*;
import co.fr8.terminal.base.ui.AbstractActivityUI;

import java.util.Arrays;

/**
 * ActivityUI for MonitorGithubRepositoryActivity
 * https://maginot.atlassian.net/wiki/display/SH/Activity%3A+Monitor+Github+Repository
 */
public class TriggerGithubRepositoryActivityUI extends AbstractActivityUI {

  private DropDownList repoList;
  private DropDownList branchList;
  private RadioButtonGroup radioButtonGroup;
  private RadioButtonOption allBranches;
  private RadioButtonOption selectBranch;
  private TextBlock detectEvents;
  private CheckBox issue;
  private CheckBox pullRequest;

  public TriggerGithubRepositoryActivityUI() {
    this.repoList = new DropDownList("Select a repository to monitor");
    this.branchList = new DropDownList();
    this.radioButtonGroup = new RadioButtonGroup("Specify Branch");
    allBranches = new RadioButtonOption("all", "All");
    selectBranch = new RadioButtonOption("only", "Only", Arrays.asList(branchList));
    radioButtonGroup.setRadios(Arrays.asList(allBranches, selectBranch));
    detectEvents = new TextBlock("", "detectEvents", "Detect events involving an");
    issue = new CheckBox("issue", "Issue");
    pullRequest = new CheckBox("pullRequest", "Pull Request");
  }

  @Override
  protected void consolidateControls() {
    if (!controls.contains(repoList))
      controls.add(repoList);
    if (!controls.contains(branchList))
      controls.add(branchList);
    if (!controls.contains(radioButtonGroup))
      controls.add(radioButtonGroup);
    if (!controls.contains(detectEvents))
      controls.add(detectEvents);
    if (!controls.contains(issue))
      controls.add(issue);
    if (!controls.contains(pullRequest))
      controls.add(pullRequest);
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

  public DropDownList getBranchList() {
    return branchList;
  }

  public void setBranchList(DropDownList branchList) {
    this.branchList = branchList;
  }

  public RadioButtonOption getAllBranches() {
    return allBranches;
  }

  public void setAllBranches(RadioButtonOption allBranches) {
    this.allBranches = allBranches;
  }

  public RadioButtonOption getSelectBranch() {
    return selectBranch;
  }

  public void setSelectBranch(RadioButtonOption selectBranch) {
    this.selectBranch = selectBranch;
  }

  public TextBlock getDetectEvents() {
    return detectEvents;
  }

  public void setDetectEvents(TextBlock detectEvents) {
    this.detectEvents = detectEvents;
  }

  public CheckBox getIssue() {
    return issue;
  }

  public void setIssue(CheckBox issue) {
    this.issue = issue;
  }

  public CheckBox getPullRequest() {
    return pullRequest;
  }

  public void setPullRequest(CheckBox pullRequest) {
    this.pullRequest = pullRequest;
  }
}
