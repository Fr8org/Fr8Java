package github.models;

/**
 * Model to map JSON response
 *
 * See https://developer.github.com/v3/repos/#response
 */
public class GitHubOwner {

  private String login;
  private String id;
  private String avatarUrl;
  private String gravatarId;
  private String url;
  private String htmlUrl;
  private String followersUrl;
  private String followingUrl;
  private String gistsUrl;
  private String starredUrl;
  private String subscriptionsUrl;
  private String organizationsUrl;
  private String reposUrl;
  private String eventsUrl;
  private String receivedEventsUrl;
  private String type;
  private boolean siteAdmin;

  public GitHubOwner() {
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getGravatarId() {
    return gravatarId;
  }

  public void setGravatarId(String gravatarId) {
    this.gravatarId = gravatarId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getFollowersUrl() {
    return followersUrl;
  }

  public void setFollowersUrl(String followersUrl) {
    this.followersUrl = followersUrl;
  }

  public String getFollowingUrl() {
    return followingUrl;
  }

  public void setFollowingUrl(String followingUrl) {
    this.followingUrl = followingUrl;
  }

  public String getGistsUrl() {
    return gistsUrl;
  }

  public void setGistsUrl(String gistsUrl) {
    this.gistsUrl = gistsUrl;
  }

  public String getStarredUrl() {
    return starredUrl;
  }

  public void setStarredUrl(String starredUrl) {
    this.starredUrl = starredUrl;
  }

  public String getSubscriptionsUrl() {
    return subscriptionsUrl;
  }

  public void setSubscriptionsUrl(String subscriptionsUrl) {
    this.subscriptionsUrl = subscriptionsUrl;
  }

  public String getOrganizationsUrl() {
    return organizationsUrl;
  }

  public void setOrganizationsUrl(String organizationsUrl) {
    this.organizationsUrl = organizationsUrl;
  }

  public String getReposUrl() {
    return reposUrl;
  }

  public void setReposUrl(String reposUrl) {
    this.reposUrl = reposUrl;
  }

  public String getEventsUrl() {
    return eventsUrl;
  }

  public void setEventsUrl(String eventsUrl) {
    this.eventsUrl = eventsUrl;
  }

  public String getReceivedEventsUrl() {
    return receivedEventsUrl;
  }

  public void setReceivedEventsUrl(String receivedEventsUrl) {
    this.receivedEventsUrl = receivedEventsUrl;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isSiteAdmin() {
    return siteAdmin;
  }

  public void setSiteAdmin(boolean siteAdmin) {
    this.siteAdmin = siteAdmin;
  }

  @Override
  public String toString() {
    return "GitHubOwner{" +
        "login='" + login + '\'' +
        ", id='" + id + '\'' +
        ", avatarUrl='" + avatarUrl + '\'' +
        ", gravatarId='" + gravatarId + '\'' +
        ", url='" + url + '\'' +
        ", htmlUrl='" + htmlUrl + '\'' +
        ", followersUrl='" + followersUrl + '\'' +
        ", followingUrl='" + followingUrl + '\'' +
        ", gistsUrl='" + gistsUrl + '\'' +
        ", starredUrl='" + starredUrl + '\'' +
        ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
        ", organizationsUrl='" + organizationsUrl + '\'' +
        ", reposUrl='" + reposUrl + '\'' +
        ", eventsUrl='" + eventsUrl + '\'' +
        ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
        ", type='" + type + '\'' +
        ", siteAdmin=" + siteAdmin +
        '}';
  }

  /*
      "login": "octocat",
      "id": 1,
      "avatar_url": "https://github.com/images/error/octocat_happy.gif",
      "gravatar_id": "",
      "url": "https://api.github.com/users/octocat",
      "html_url": "https://github.com/octocat",
      "followers_url": "https://api.github.com/users/octocat/followers",
      "following_url": "https://api.github.com/users/octocat/following{/other_user}",
      "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
      "organizations_url": "https://api.github.com/users/octocat/orgs",
      "repos_url": "https://api.github.com/users/octocat/repos",
      "events_url": "https://api.github.com/users/octocat/events{/privacy}",
      "received_events_url": "https://api.github.com/users/octocat/received_events",
      "type": "User",
      "site_admin": false
   */
}
