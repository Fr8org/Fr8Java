package github.activities.request;

public class CreateGithubIssueRequest {
  private final String authToken;
  private final String githubUserId;
  private final String repoName;
  private final String title;
  private final String body;

  public CreateGithubIssueRequest(String authToken, String githubUserId, String repoName, String title, String body) {
    this.authToken = authToken;
    this.githubUserId = githubUserId;
    this.repoName = repoName;
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

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }
}
