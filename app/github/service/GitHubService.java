package github.service;

import co.fr8.data.controls.ListItem;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.request.CreateGithubIssueRequest;
import github.activities.request.UpdateGithubIssueRequest;
import github.activities.request.WebhookRequest;
import github.models.GitHubBranch;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static co.fr8.play.ApplicationConstants.*;

/**
 * TODO: Change singleton pattern into Injection using Guice
 * Helper class that performs actions specifically for GitHub
 */
public class GitHubService {

  private static GitHubService instance;
//  private HttpUtils httpUtils;

  private GitHubService() {
//    httpUtils = new HttpUtils();
  }

  public static GitHubService getInstance() {
    if (instance == null)
      instance = new GitHubService();
    return instance;
  }

  public List<ListItem> getRepositoriesAsListItems(String authToken) {
    JsonNode repoJson = getRepositoryJsonForUser(authToken);
    List<ListItem> repoListItems = new ArrayList<>();
    if (repoJson != null && repoJson.isArray()) {
      Iterator<JsonNode> repositories = repoJson.elements();
      repositories.forEachRemaining(repo -> {
        Logger.debug("Adding " + repo.get("name").asText());
        repoListItems.add(new ListItem(repo.get("name").asText(), repo.get("full_name").asText()));
      });
    }
    return repoListItems;
  }

  public List<ListItem> getBranchesAsListItems(String authToken, String selectedRepo) {
    GitHubBranch[] branchesForRepo = getBranchesForRepo(false, authToken, selectedRepo);
    List<ListItem> repoListItems = new ArrayList<>();
    if (branchesForRepo != null && branchesForRepo.length > 0) {
      for (GitHubBranch branch : branchesForRepo) {
        repoListItems.add(new ListItem(branch.getName(), branch.getName()));
      }
    }
    return repoListItems;
  }

//  public List<GitHubRepo> getRepositoriesForUser(String authToken) {
//    String response = fetchRepositories(authToken);
//    List<GitHubRepo> ret = new ArrayList<>();
//    if (StringUtils.isNotBlank(response)) {
//      GitHubRepoResponse repoResponse =
//          JsonUtils.writeStringToObject(response, GitHubRepoResponse.class);
//      if (repoResponse != null) {
//        ret = repoResponse.getRepositories();
//      }
//    }
//    return ret;
//  }


  public String createGithubIssue(CreateGithubIssueRequest createGithubIssueRequest) {
    String patchGithubIssueUrl = REPOS_URL + "/" + createGithubIssueRequest.getRepo() + "/issues" + "?access_token=" +
        createGithubIssueRequest.getAuthToken();
    Logger.debug("Calling post for auth token with url: " + patchGithubIssueUrl +
        " and parameters: title: " + createGithubIssueRequest.getTitle() + ", body: " + createGithubIssueRequest.getBody());
    return HttpUtils.postJson(patchGithubIssueUrl, JsonUtils.writeObjectToJsonNode(createGithubIssueRequest));
  }

  public String updateGithubIssue(UpdateGithubIssueRequest updateGithubIssueRequest) {
    String patchGithubIssueUrl = REPOS_URL + "/" + updateGithubIssueRequest.getRepo() + "/issues" + "/" +
        updateGithubIssueRequest.getIssueId() + "?access_token=" + updateGithubIssueRequest.getAuthToken();
    Logger.debug("Calling patch for issue: " + updateGithubIssueRequest.getIssueId() + ", with url: " + patchGithubIssueUrl +
        " and parameters: state " + updateGithubIssueRequest.getState() + ", title: " +
        updateGithubIssueRequest.getTitle() + ", body: " + updateGithubIssueRequest.getBody());
    return HttpUtils.patchJson(patchGithubIssueUrl, JsonUtils.writeObjectToJsonNode(updateGithubIssueRequest));
  }

  public String postWebhookToGithub(String repoName, String authToken, String... webhooks){
    Logger.debug("Creating webhook to monitor Github changes in repo: " + repoName + "for issues: " + Arrays.toString(webhooks));
    String githubWebhookUrl = REPOS_URL + "/" + repoName + "/hooks" + "?access_token=" + authToken;
    WebhookRequest webhookRequest = new WebhookRequest("GithubEventReport", "active", webhooks,
        new WebhookRequest.Config(WEBHOOK_URL, "json"));
    return HttpUtils.postJson(githubWebhookUrl, JsonUtils.writeObjectToJsonNode(webhookRequest));
  }

  /**
   *
   * @param protectedBool this parameter is supported by github, but we have no use for it.
   * @param authToken
   * @param selectedRepo
   * @return
   */
  public GitHubBranch[] getBranchesForRepo(boolean protectedBool, String authToken, String selectedRepo) {
    Logger.debug("About to get branches of the repo using token " + authToken);
    String response = HttpUtils.get(REPOS_URL + "/" + selectedRepo + "/branches?access_token=" + authToken);
//    GitHubBranch> ret = new ArrayList<>();
    GitHubBranch[] branchResponse = null;
    if (StringUtils.isNotBlank(response)) {
      branchResponse = JsonUtils.writeStringToObject(response, GitHubBranch[].class);
//      if (branchResponse != null) {
//        ret = branchResponse[0].getBranches();
//      }
    }
    return branchResponse;
  }

//  public GitHubRepo getPullRequestedRepo(String ouathUser, String repoName) {
//    Logger.debug("Getting pull requested repo");
////    HttpUtils.post()
//  }

  public JsonNode getRepositoryJsonForUser(String authToken) {
    String response = fetchRepositories(authToken);
    return (StringUtils.isBlank(response)) ? Json.newObject() :
        JsonUtils.writeStringToObject(response);
  }

  private String fetchRepositories(String authToken) {
    Logger.debug("About to get repositories using token " + authToken);
    return HttpUtils.get(USER_REPOS_URL + "?access_token=" + authToken);
  }
}
