package github.activities.request;

public class UpdateGithubIssueRequest {
  private final String authToken;
  private final String repo;
  private final String issueId;
  private final String state;
  private final String title;
  private final String body;

  public UpdateGithubIssueRequest(String authToken, String repo, String issueId, String state, String title, String body) {
    this.authToken = authToken;
    this.repo= repo;
    this.issueId = issueId;
    this.state = state;
    this.title = title;
    this.body = body;
  }

  public String getAuthToken() {
    return authToken;
  }

  public String getRepo() {
    return repo;
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
