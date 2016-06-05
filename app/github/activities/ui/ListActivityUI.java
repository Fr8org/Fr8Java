package github.activities.ui;

import co.fr8.data.controls.impl.DropDownList;
import co.fr8.terminal.base.ui.AbstractActivityUI;

/**
 * TODO: Implement
 */
public class ListActivityUI extends AbstractActivityUI {

  private DropDownList repoList;

  public ListActivityUI() {
    this.repoList = new DropDownList("Select a repository to monitor");
  }

  public ListActivityUI(DropDownList repolist) {
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