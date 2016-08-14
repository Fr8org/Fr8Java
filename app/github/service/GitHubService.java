package github.service;

import co.fr8.data.controls.ListItem;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.request.CreateGithubIssueRequest;
import github.activities.request.UpdateGithubIssueRequest;
import github.activities.request.WebhookRequest;
import github.models.GitHubRepo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import play.libs.Json;

import java.util.ArrayList;
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

  public List<GitHubRepo> getRepositoriesForUser(String authToken) {
    String response = fetchRepositories(authToken);
    List<GitHubRepo> ret = new ArrayList<>();
    if (StringUtils.isNotBlank(response)) {
      GitHubRepoResponse repoResponse =
          JsonUtils.writeStringToObject(response, GitHubRepoResponse.class);
      if (repoResponse != null) {
        ret = repoResponse.getRepositories();
      }
    }
    return ret;
  }


  public String createGithubIssue(CreateGithubIssueRequest createGithubIssueRequest) {
    String patchGithubIssueUrl = REPOS_URL + "/" + createGithubIssueRequest.getRepo() + "/issues" + "?access_token=" + createGithubIssueRequest.getAuthToken();
    Logger.debug("Calling patch for auth token with url: " + patchGithubIssueUrl +
        " and parameters: title: " + createGithubIssueRequest.getTitle() + ", body: " + createGithubIssueRequest.getBody());
    return HttpUtils.postJson(patchGithubIssueUrl, JsonUtils.writeObjectToJsonNode(createGithubIssueRequest));
  }

  public String updateGithubIssue(UpdateGithubIssueRequest updateGithubIssueRequest) {
    List<NameValuePair> nameValuePairList = new ArrayList<>(5);
    nameValuePairList.add(new BasicNameValuePair(STATE_PARAM, updateGithubIssueRequest.getState()));
    nameValuePairList.add(new BasicNameValuePair("title", updateGithubIssueRequest.getTitle()));
    nameValuePairList.add(new BasicNameValuePair("body", updateGithubIssueRequest.getBody()));
    String patchGithubIssueUrl = REPOS_URL + "/" + updateGithubIssueRequest.getRepo() + "/issues" + "/" + updateGithubIssueRequest.getIssueId() + "?access_token=" + updateGithubIssueRequest.getAuthToken();
    Logger.debug("Calling patch for auth token with url: " + patchGithubIssueUrl +
        " and parameters: state " + updateGithubIssueRequest.getState() + ", title: " + updateGithubIssueRequest.getTitle() + ", body: " + updateGithubIssueRequest.getBody());
    return HttpUtils.patch(patchGithubIssueUrl, nameValuePairList);
  }

  public String postWebhookToGithubPullRequests(String repoName, String authToken, String githubUserId){
    Logger.debug("Creating webhook to monitor Github pull requests");
    String githubWebhookUrl = REPOS_URL + "/" + githubUserId + "/" + repoName + "/hooks" + "?access_token=\"" + authToken + "\"";
    WebhookRequest webhookRequest = new WebhookRequest("webhookMonitorRepoPulls", "active", new String[]{"pull_request"}, new WebhookRequest.Config(WEBHOOK_URL, "json"));
    return HttpUtils.postJson(githubWebhookUrl, JsonUtils.writeObjectToJsonNode(webhookRequest));
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
