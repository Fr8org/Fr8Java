package github.activities.ui;

import co.fr8.data.controls.impl.DropDownList;
import co.fr8.terminal.base.ui.AbstractActivityUI;

/**
 * TODO: Implement
 */
public class TriggerPullRequestActivityUI extends AbstractActivityUI {

  private DropDownList repoList;

  public TriggerPullRequestActivityUI() {
    this.repoList = new DropDownList("Select a repository to monitor");
  }

  public TriggerPullRequestActivityUI(DropDownList repolist) {
    this.repoList = repolist;
  }

  public DropDownList getRepoList() {
    return repoList;
  }

  public void setRepoList(DropDownList repoList) {
    this.repoList = repoList;
  }

  @Override
  protected void consolidateControls() {
    if (!controls.contains(repoList))
      controls.add(repoList);
  }
}
