package github.activities.request;

public class CreateGithubIssueRequest {
  private final String authToken;
  private final String repo;
  private final String title;
  private final String body;

  public CreateGithubIssueRequest(String authToken, String repo, String title, String body) {
    this.authToken = authToken;
    this.repo = repo;
    this.title = title;
    this.body = body;
  }

  public String getAuthToken() {
    return authToken;
  }

  public String getRepo() {
    return repo;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }
}
