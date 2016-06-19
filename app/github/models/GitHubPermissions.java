package github.models;

/**
 * Model to map JSON response
 *
 * See https://developer.github.com/v3/repos/#response
 */
public class GitHubPermissions {

  private boolean admin;
  private boolean push;
  private boolean pull;

  public GitHubPermissions() {
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public boolean isPush() {
    return push;
  }

  public void setPush(boolean push) {
    this.push = push;
  }

  public boolean isPull() {
    return pull;
  }

  public void setPull(boolean pull) {
    this.pull = pull;
  }

  @Override
  public String toString() {
    return "GitHubPermissions{" +
        "admin=" + admin +
        ", push=" + push +
        ", pull=" + pull +
        '}';
  }
}
