package github.activities.ui;

import co.fr8.data.controls.impl.DropDownList;
import co.fr8.data.controls.impl.TextBlock;
import co.fr8.data.controls.impl.TextSource;
import co.fr8.terminal.base.ui.AbstractActivityUI;

/**
 */
public class CreateGithubIssueActivityUI extends AbstractActivityUI {

  private TextBlock information;
  private DropDownList repositories;
  private TextSource title;
  private TextSource body;

  public CreateGithubIssueActivityUI() {
    information = new TextBlock("", "information", "Update which Github Issue");
    repositories = new DropDownList("Repo Name");
    title = new TextSource("Title", " ", "title");
    body = new TextSource("Body", " ", "body");
  }

  @Override
  protected void consolidateControls() {
    if (!controls.contains(information))
      controls.add(information);
    if (!controls.contains(repositories))
      controls.add(repositories);
    if (!controls.contains(title))
      controls.add(title);
    if (!controls.contains(body))
      controls.add(body);
  }

  public TextBlock getInformation() {
    return information;
  }

  public void setInformation(TextBlock information) {
    this.information = information;
  }

  public DropDownList getRepositories() {
    return repositories;
  }

  public void setRepositories(DropDownList repositories) {
    this.repositories = repositories;
  }

  public TextSource getTitle() {
    return title;
  }

  public void setTitle(TextSource title) {
    this.title = title;
  }

  public TextSource getBody() {
    return body;
  }

  public void setBody(TextSource body) {
    this.body = body;
  }
}
