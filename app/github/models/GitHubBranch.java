package github.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubBranch {

  private String name;
  private Commit commit;
  @JsonProperty("protected")
  private boolean protectedBool;
  private String protectionUrl;

  public GitHubBranch(String name, Commit commit, boolean protectedBoolean, String protectionUrl) {
    this.name = name;
    this.commit = commit;
    this.protectedBool = protectedBoolean;
    this.protectionUrl = protectionUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Commit getCommit() {
    return commit;
  }

  public void setCommit(Commit commit) {
    this.commit = commit;
  }

  public boolean isProtectedBool() {
    return protectedBool;
  }

  public void setProtectedBool(boolean protectedBool) {
    this.protectedBool = protectedBool;
  }

  public String getProtectionUrl() {
    return protectionUrl;
  }

  public void setProtectionUrl(String protectionUrl) {
    this.protectionUrl = protectionUrl;
  }

  private class Commit {
    private String sha;
    private String url;

    public String getSha() {
      return sha;
    }

    public void setSha(String sha) {
      this.sha = sha;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}
