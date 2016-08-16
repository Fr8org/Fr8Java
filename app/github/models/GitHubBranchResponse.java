package github.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to map JSON response
 *
 * See https://developer.github.com/v3/repos/#response
 */
public class GitHubBranchResponse {

  private List<GitHubBranch> branches;

  public GitHubBranchResponse() {
    branches = new ArrayList<>();
  }

  public List<GitHubBranch> getBranches() {
    return branches;
  }

  public void setBranches(List<GitHubBranch> branches) {
    this.branches = branches;
  }

  @Override
  public String toString() {
    return "GitHubBranchResponse{" +
        "branches=" + branches +
        '}';
  }

}
