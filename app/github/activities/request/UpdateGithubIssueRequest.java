package github.activities.request;

public class UpdateGithubIssueRequest {
  private final String authToken;
  private final String githubUserId;
  private final String repoName;
  private final String issueId;
  private final String state;
  private final String title;
  private final String body;

  public UpdateGithubIssueRequest(String authToken, String githubUserId, String repoName, String issueId, String state, String title, String body) {
    this.authToken = authToken;
    this.githubUserId = githubUserId;
    this.repoName = repoName;
    this.issueId = issueId;
    this.state = state;
    this.title = title;
    this.body = body;
  }

  public String getAuthToken() {
    return authToken;
  }

  public String getGithubUserId() {
    return githubUserId;
  }

  public String getRepoName() {
    return repoName;
  }

  public String getIssueId() {
    return issueId;
  }

  public String getState() {
    return state;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }
}