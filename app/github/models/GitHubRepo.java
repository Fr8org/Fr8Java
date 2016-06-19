package github.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Model to map JSON response
 *
 * See https://developer.github.com/v3/repos/#response
 */
public class GitHubRepo {

  private String id;
  private GitHubOwner owner;
  private String name;

//  @JsonProperty("full_name")
  private String fullName;
  private String description;

  @JsonProperty("private")
  private boolean isPrivate;
  private boolean fork;
  private String url;
  private String htmlUrl;
  private String archiveUrl;
  private String assigneesUrl;
  private String blobsUrl;
  private String branchesUrl;
  private String cloneUrl;
  private String collaboratorsUrl;
  private String commentsUrl;
  private String commitsUrl;
  private String compareUrl;
  private String contentsUrl;
  private String contributorsUrl;
  private String deploymentsUrl;
  private String downloadsUrl;
  private String eventsUrl;
  private String forksUrl;
  private String gitCommitsUrl;
  private String gitRefsUrl;
  private String gitTagsUrl;
  private String gitUrl;
  private String hooksUrl;
  private String issueCommentUrl;
  private String issueEventsUrl;
  private String issuesUrl;
  private String keysUrl;
  private String labelsUrl;
  private String languagesUrl;
  private String mergesUrl;
  private String milestonesUrl;
  private String mirrorUrl;
  private String notificationsUrl;
  private String pullsUrl;
  private String releasesUrl;
  private String sshUrl;
  private String stargazersUrl;
  private String statusesUrl;
  private String subscribersUrl;
  private String subscriptionUrl;
  private String svnUrl;
  private String tagsUrl;
  private String teamsUrl;
  private String treesUrl;
  private String homepage;
  private String language;
  private long forksCount;
  private long stargazersCount;
  private long watchersCount;
  private long size;
  private String defaultBranch;
  private long openIssuesCount;
  private boolean hasIssues;
  private boolean hasWiki;
  private boolean hasPages;
  private boolean hasDownloads;
  private Date pushedAt;
  private Date createdAt;
  private Date updatedAt;
  private GitHubPermissions permissions;

  public GitHubRepo() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public GitHubOwner getOwner() {
    return owner;
  }

  public void setOwner(GitHubOwner owner) {
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
  }

  public boolean isFork() {
    return fork;
  }

  public void setFork(boolean fork) {
    this.fork = fork;
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

  public String getArchiveUrl() {
    return archiveUrl;
  }

  public void setArchiveUrl(String archiveUrl) {
    this.archiveUrl = archiveUrl;
  }

  public String getAssigneesUrl() {
    return assigneesUrl;
  }

  public void setAssigneesUrl(String assigneesUrl) {
    this.assigneesUrl = assigneesUrl;
  }

  public String getBlobsUrl() {
    return blobsUrl;
  }

  public void setBlobsUrl(String blobsUrl) {
    this.blobsUrl = blobsUrl;
  }

  public String getBranchesUrl() {
    return branchesUrl;
  }

  public void setBranchesUrl(String branchesUrl) {
    this.branchesUrl = branchesUrl;
  }

  public String getCloneUrl() {
    return cloneUrl;
  }

  public void setCloneUrl(String cloneUrl) {
    this.cloneUrl = cloneUrl;
  }

  public String getCollaboratorsUrl() {
    return collaboratorsUrl;
  }

  public void setCollaboratorsUrl(String collaboratorsUrl) {
    this.collaboratorsUrl = collaboratorsUrl;
  }

  public String getCommentsUrl() {
    return commentsUrl;
  }

  public void setCommentsUrl(String commentsUrl) {
    this.commentsUrl = commentsUrl;
  }

  public String getCommitsUrl() {
    return commitsUrl;
  }

  public void setCommitsUrl(String commitsUrl) {
    this.commitsUrl = commitsUrl;
  }

  public String getCompareUrl() {
    return compareUrl;
  }

  public void setCompareUrl(String compareUrl) {
    this.compareUrl = compareUrl;
  }

  public String getContentsUrl() {
    return contentsUrl;
  }

  public void setContentsUrl(String contentsUrl) {
    this.contentsUrl = contentsUrl;
  }

  public String getContributorsUrl() {
    return contributorsUrl;
  }

  public void setContributorsUrl(String contributorsUrl) {
    this.contributorsUrl = contributorsUrl;
  }

  public String getDeploymentsUrl() {
    return deploymentsUrl;
  }

  public void setDeploymentsUrl(String deploymentsUrl) {
    this.deploymentsUrl = deploymentsUrl;
  }

  public String getDownloadsUrl() {
    return downloadsUrl;
  }

  public void setDownloadsUrl(String downloadsUrl) {
    this.downloadsUrl = downloadsUrl;
  }

  public String getEventsUrl() {
    return eventsUrl;
  }

  public void setEventsUrl(String eventsUrl) {
    this.eventsUrl = eventsUrl;
  }

  public String getForksUrl() {
    return forksUrl;
  }

  public void setForksUrl(String forksUrl) {
    this.forksUrl = forksUrl;
  }

  public String getGitCommitsUrl() {
    return gitCommitsUrl;
  }

  public void setGitCommitsUrl(String gitCommitsUrl) {
    this.gitCommitsUrl = gitCommitsUrl;
  }

  public String getGitRefsUrl() {
    return gitRefsUrl;
  }

  public void setGitRefsUrl(String gitRefsUrl) {
    this.gitRefsUrl = gitRefsUrl;
  }

  public String getGitTagsUrl() {
    return gitTagsUrl;
  }

  public void setGitTagsUrl(String gitTagsUrl) {
    this.gitTagsUrl = gitTagsUrl;
  }

  public String getGitUrl() {
    return gitUrl;
  }

  public void setGitUrl(String gitUrl) {
    this.gitUrl = gitUrl;
  }

  public String getHooksUrl() {
    return hooksUrl;
  }

  public void setHooksUrl(String hooksUrl) {
    this.hooksUrl = hooksUrl;
  }

  public String getIssueCommentUrl() {
    return issueCommentUrl;
  }

  public void setIssueCommentUrl(String issueCommentUrl) {
    this.issueCommentUrl = issueCommentUrl;
  }

  public String getIssueEventsUrl() {
    return issueEventsUrl;
  }

  public void setIssueEventsUrl(String issueEventsUrl) {
    this.issueEventsUrl = issueEventsUrl;
  }

  public String getIssuesUrl() {
    return issuesUrl;
  }

  public void setIssuesUrl(String issuesUrl) {
    this.issuesUrl = issuesUrl;
  }

  public String getKeysUrl() {
    return keysUrl;
  }

  public void setKeysUrl(String keysUrl) {
    this.keysUrl = keysUrl;
  }

  public String getLabelsUrl() {
    return labelsUrl;
  }

  public void setLabelsUrl(String labelsUrl) {
    this.labelsUrl = labelsUrl;
  }

  public String getLanguagesUrl() {
    return languagesUrl;
  }

  public void setLanguagesUrl(String languagesUrl) {
    this.languagesUrl = languagesUrl;
  }

  public String getMergesUrl() {
    return mergesUrl;
  }

  public void setMergesUrl(String mergesUrl) {
    this.mergesUrl = mergesUrl;
  }

  public String getMilestonesUrl() {
    return milestonesUrl;
  }

  public void setMilestonesUrl(String milestonesUrl) {
    this.milestonesUrl = milestonesUrl;
  }

  public String getMirrorUrl() {
    return mirrorUrl;
  }

  public void setMirrorUrl(String mirrorUrl) {
    this.mirrorUrl = mirrorUrl;
  }

  public String getNotificationsUrl() {
    return notificationsUrl;
  }

  public void setNotificationsUrl(String notificationsUrl) {
    this.notificationsUrl = notificationsUrl;
  }

  public String getPullsUrl() {
    return pullsUrl;
  }

  public void setPullsUrl(String pullsUrl) {
    this.pullsUrl = pullsUrl;
  }

  public String getReleasesUrl() {
    return releasesUrl;
  }

  public void setReleasesUrl(String releasesUrl) {
    this.releasesUrl = releasesUrl;
  }

  public String getSshUrl() {
    return sshUrl;
  }

  public void setSshUrl(String sshUrl) {
    this.sshUrl = sshUrl;
  }

  public String getStargazersUrl() {
    return stargazersUrl;
  }

  public void setStargazersUrl(String stargazersUrl) {
    this.stargazersUrl = stargazersUrl;
  }

  public String getStatusesUrl() {
    return statusesUrl;
  }

  public void setStatusesUrl(String statusesUrl) {
    this.statusesUrl = statusesUrl;
  }

  public String getSubscribersUrl() {
    return subscribersUrl;
  }

  public void setSubscribersUrl(String subscribesrUrl) {
    this.subscribersUrl = subscribesrUrl;
  }

  public String getSubscriptionUrl() {
    return subscriptionUrl;
  }

  public void setSubscriptionUrl(String subscriptionUrl) {
    this.subscriptionUrl = subscriptionUrl;
  }

  public String getSvnUrl() {
    return svnUrl;
  }

  public void setSvnUrl(String svnUrl) {
    this.svnUrl = svnUrl;
  }

  public String getTagsUrl() {
    return tagsUrl;
  }

  public void setTagsUrl(String tagsUrl) {
    this.tagsUrl = tagsUrl;
  }

  public String getTeamsUrl() {
    return teamsUrl;
  }

  public void setTeamsUrl(String teamsUrl) {
    this.teamsUrl = teamsUrl;
  }

  public String getTreesUrl() {
    return treesUrl;
  }

  public void setTreesUrl(String treesUrl) {
    this.treesUrl = treesUrl;
  }

  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public long getForksCount() {
    return forksCount;
  }

  public void setForksCount(long forksCount) {
    this.forksCount = forksCount;
  }

  public long getStargazersCount() {
    return stargazersCount;
  }

  public void setStargazersCount(long stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  public long getWatchersCount() {
    return watchersCount;
  }

  public void setWatchersCount(long watchersCount) {
    this.watchersCount = watchersCount;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getDefaultBranch() {
    return defaultBranch;
  }

  public void setDefaultBranch(String defaultBranch) {
    this.defaultBranch = defaultBranch;
  }

  public long getOpenIssuesCount() {
    return openIssuesCount;
  }

  public void setOpenIssuesCount(long openIssuesCount) {
    this.openIssuesCount = openIssuesCount;
  }

  public boolean isHasIssues() {
    return hasIssues;
  }

  public void setHasIssues(boolean hasIssues) {
    this.hasIssues = hasIssues;
  }

  public boolean isHasWiki() {
    return hasWiki;
  }

  public void setHasWiki(boolean hasWiki) {
    this.hasWiki = hasWiki;
  }

  public boolean isHasPages() {
    return hasPages;
  }

  public void setHasPages(boolean hasPages) {
    this.hasPages = hasPages;
  }

  public boolean isHasDownloads() {
    return hasDownloads;
  }

  public void setHasDownloads(boolean hasDownloads) {
    this.hasDownloads = hasDownloads;
  }

  public Date getPushedAt() {
    return pushedAt;
  }

  public void setPushedAt(Date pushedAt) {
    this.pushedAt = pushedAt;
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

  public GitHubPermissions getPermissions() {
    return permissions;
  }

  public void setPermissions(GitHubPermissions permissions) {
    this.permissions = permissions;
  }

  @Override
  public String toString() {
    return "GitHubRepo{" +
        "id='" + id + '\'' +
        ", owner=" + owner +
        ", name='" + name + '\'' +
        ", fullName='" + fullName + '\'' +
        ", description='" + description + '\'' +
        ", isPrivate=" + isPrivate +
        ", fork=" + fork +
        ", url='" + url + '\'' +
        ", htmlUrl='" + htmlUrl + '\'' +
        ", archiveUrl='" + archiveUrl + '\'' +
        ", assigneesUrl='" + assigneesUrl + '\'' +
        ", blobsUrl='" + blobsUrl + '\'' +
        ", branchesUrl='" + branchesUrl + '\'' +
        ", cloneUrl='" + cloneUrl + '\'' +
        ", collaboratorsUrl='" + collaboratorsUrl + '\'' +
        ", commentsUrl='" + commentsUrl + '\'' +
        ", commitsUrl='" + commitsUrl + '\'' +
        ", compareUrl='" + compareUrl + '\'' +
        ", contentsUrl='" + contentsUrl + '\'' +
        ", contributorsUrl='" + contributorsUrl + '\'' +
        ", deploymentsUrl='" + deploymentsUrl + '\'' +
        ", downloadsUrl='" + downloadsUrl + '\'' +
        ", eventsUrl='" + eventsUrl + '\'' +
        ", forksUrl='" + forksUrl + '\'' +
        ", gitCommitsUrl='" + gitCommitsUrl + '\'' +
        ", gitRefsUrl='" + gitRefsUrl + '\'' +
        ", gitTagsUrl='" + gitTagsUrl + '\'' +
        ", gitUrl='" + gitUrl + '\'' +
        ", hooksUrl='" + hooksUrl + '\'' +
        ", issueCommentUrl='" + issueCommentUrl + '\'' +
        ", issueEventsUrl='" + issueEventsUrl + '\'' +
        ", issuesUrl='" + issuesUrl + '\'' +
        ", keysUrl='" + keysUrl + '\'' +
        ", labelsUrl='" + labelsUrl + '\'' +
        ", languagesUrl='" + languagesUrl + '\'' +
        ", mergesUrl='" + mergesUrl + '\'' +
        ", milestonesUrl='" + milestonesUrl + '\'' +
        ", mirrorUrl='" + mirrorUrl + '\'' +
        ", notificationsUrl='" + notificationsUrl + '\'' +
        ", pullsUrl='" + pullsUrl + '\'' +
        ", releasesUrl='" + releasesUrl + '\'' +
        ", sshUrl='" + sshUrl + '\'' +
        ", stargazersUrl='" + stargazersUrl + '\'' +
        ", statusesUrl='" + statusesUrl + '\'' +
        ", subscribersUrl='" + subscribersUrl + '\'' +
        ", subscriptionUrl='" + subscriptionUrl + '\'' +
        ", svnUrl='" + svnUrl + '\'' +
        ", tagsUrl='" + tagsUrl + '\'' +
        ", teamsUrl='" + teamsUrl + '\'' +
        ", treesUrl='" + treesUrl + '\'' +
        ", homepage='" + homepage + '\'' +
        ", language='" + language + '\'' +
        ", forksCount=" + forksCount +
        ", stargazersCount=" + stargazersCount +
        ", watchersCount=" + watchersCount +
        ", size=" + size +
        ", defaultBranch='" + defaultBranch + '\'' +
        ", openIssuesCount=" + openIssuesCount +
        ", hasIssues=" + hasIssues +
        ", hasWiki=" + hasWiki +
        ", hasPages=" + hasPages +
        ", hasDownloads=" + hasDownloads +
        ", pushedAt=" + pushedAt +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", permissions=" + permissions +
        '}';
  }

  /*
"id": 1296269,
    "owner": {

    },
    "name": "Hello-World",
    "full_name": "octocat/Hello-World",
    "description": "This your first repo!",
    "private": false,
    "fork": false,
    "url": "https://api.github.com/repos/octocat/Hello-World",
    "html_url": "https://github.com/octocat/Hello-World",
    "archive_url": "http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}",
    "assignees_url": "http://api.github.com/repos/octocat/Hello-World/assignees{/user}",
    "blobs_url": "http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}",
    "branches_url": "http://api.github.com/repos/octocat/Hello-World/branches{/branch}",
    "clone_url": "https://github.com/octocat/Hello-World.git",
    "collaborators_url": "http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}",
    "comments_url": "http://api.github.com/repos/octocat/Hello-World/comments{/number}",
    "commits_url": "http://api.github.com/repos/octocat/Hello-World/commits{/sha}",
    "compare_url": "http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}",
    "contents_url": "http://api.github.com/repos/octocat/Hello-World/contents/{+path}",
    "contributors_url": "http://api.github.com/repos/octocat/Hello-World/contributors",
    "deployments_url": "http://api.github.com/repos/octocat/Hello-World/deployments",
    "downloads_url": "http://api.github.com/repos/octocat/Hello-World/downloads",
    "events_url": "http://api.github.com/repos/octocat/Hello-World/events",
    "forks_url": "http://api.github.com/repos/octocat/Hello-World/forks",
    "git_commits_url": "http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}",
    "git_refs_url": "http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}",
    "git_tags_url": "http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}",
    "git_url": "git:github.com/octocat/Hello-World.git",
    "hooks_url": "http://api.github.com/repos/octocat/Hello-World/hooks",
    "issue_comment_url": "http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}",
    "issue_events_url": "http://api.github.com/repos/octocat/Hello-World/issues/events{/number}",
    "issues_url": "http://api.github.com/repos/octocat/Hello-World/issues{/number}",
    "keys_url": "http://api.github.com/repos/octocat/Hello-World/keys{/key_id}",
    "labels_url": "http://api.github.com/repos/octocat/Hello-World/labels{/name}",
    "languages_url": "http://api.github.com/repos/octocat/Hello-World/languages",
    "merges_url": "http://api.github.com/repos/octocat/Hello-World/merges",
    "milestones_url": "http://api.github.com/repos/octocat/Hello-World/milestones{/number}",
    "mirror_url": "git:git.example.com/octocat/Hello-World",
    "notifications_url": "http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}",
    "pulls_url": "http://api.github.com/repos/octocat/Hello-World/pulls{/number}",
    "releases_url": "http://api.github.com/repos/octocat/Hello-World/releases{/id}",
    "ssh_url": "git@github.com:octocat/Hello-World.git",
    "stargazers_url": "http://api.github.com/repos/octocat/Hello-World/stargazers",
    "statuses_url": "http://api.github.com/repos/octocat/Hello-World/statuses/{sha}",
    "subscribers_url": "http://api.github.com/repos/octocat/Hello-World/subscribers",
    "subscription_url": "http://api.github.com/repos/octocat/Hello-World/subscription",
    "svn_url": "https://svn.github.com/octocat/Hello-World",
    "tags_url": "http://api.github.com/repos/octocat/Hello-World/tags",
    "teams_url": "http://api.github.com/repos/octocat/Hello-World/teams",
    "trees_url": "http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}",
    "homepage": "https://github.com",
    "language": null,
    "forks_count": 9,
    "stargazers_count": 80,
    "watchers_count": 80,
    "size": 108,
    "default_branch": "master",
    "open_issues_count": 0,
    "has_issues": true,
    "has_wiki": true,
    "has_pages": false,
    "has_downloads": true,
    "pushed_at": "2011-01-26T19:06:43Z",
    "created_at": "2011-01-26T19:01:12Z",
    "updated_at": "2011-01-26T19:14:43Z",
    "permissions": {
      "admin": false,
      "push": false,
      "pull": true
    }
  }
]
   */
}
