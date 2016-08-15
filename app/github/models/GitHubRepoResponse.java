package github.models;

import java.util.List;

/**
 * Model to map JSON response
 *
 * See https://developer.github.com/v3/repos/#response
 */
public class GitHubRepoResponse {

  private List<GitHubRepo> repositories;

  public GitHubRepoResponse() {
  }

  public List<GitHubRepo> getRepositories() {
    return repositories;
  }

  public void setRepositories(List<GitHubRepo> repositories) {
    this.repositories = repositories;
  }

  @Override
  public String toString() {
    return "GitHubRepoResponse{" +
        "repositories=" + repositories +
        '}';
  }
}
