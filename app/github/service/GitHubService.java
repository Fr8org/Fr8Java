package github.service;

import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import github.activities.request.WebhookRequest;
import github.models.GitHubRepo;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;

import java.util.ArrayList;
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

  public String postWebhookToGithubPullRequests(String repoName, String authToken){
    Logger.debug("Creating webhook to monitor Github pull requests");
    String githubWebhookUrl = REPOS_URL + "/" + authToken + "/" + repoName + "/hooks";
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
