package github.service;

import co.fr8.play.ApplicationConstants;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import github.models.GitHubRepo;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

/**
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

  public JsonNode getRepositoryJsonForUser(String authToken) {
    String response = fetchRepositories(authToken);

    return (StringUtils.isBlank(response)) ? Json.newObject() :
        JsonUtils.writeStringToObject(response);

  }

  private String fetchRepositories(String authToken) {
    Logger.debug("About to get repositories using token " + authToken);

    return HttpUtils.get(ApplicationConstants.USER_REPOS_URL + "?access_token=" +
        authToken);

  }
}
