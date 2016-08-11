package github.models;

import java.util.Date;

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
  private String company;
  private String blog;
  private String location;
  private String email;
  private boolean hireable;
  private String bio;
  private String publicRepos;
  private String publicGists;
  private int followers;
  private int following;
  private Date createdAt;
  private Date updatedAt;

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

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getBlog() {
    return blog;
  }

  public void setBlog(String blog) {
    this.blog = blog;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isHireable() {
    return hireable;
  }

  public void setHireable(boolean hireable) {
    this.hireable = hireable;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getPublicRepos() {
    return publicRepos;
  }

  public void setPublicRepos(String publicRepos) {
    this.publicRepos = publicRepos;
  }

  public String getPublicGists() {
    return publicGists;
  }

  public void setPublicGists(String publicGists) {
    this.publicGists = publicGists;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public int getFollowing() {
    return following;
  }

  public void setFollowing(int following) {
    this.following = following;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
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
        ", company='" + company + '\'' +
        ", blog='" + blog + '\'' +
        ", location='" + location + '\'' +
        ", email='" + email + '\'' +
        ", hireable=" + hireable +
        ", bio='" + bio + '\'' +
        ", publicRepos='" + publicRepos + '\'' +
        ", publicGists='" + publicGists + '\'' +
        ", followers=" + followers +
        ", following=" + following +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
