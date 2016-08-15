package infrastructure;

import base.AbstractFakeApplication;
import co.fr8.util.json.JsonUtils;
import github.models.GitHubOwner;
import org.junit.Test;

public class JsonUtilsTest extends AbstractFakeApplication {

  @Test
  public void testWriteStringToObject() {
    String githubString = "{\"login\":\"cenkozan\",\"id\":1116432,\"avatar_url\":\"https://avatars.githubusercontent.com/u/1116432?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/cenkozan\",\"html_url\":\"https://github.com/cenkozan\",\"followers_url\":\"https://api.github.com/users/cenkozan/followers\",\"following_url\":\"https://api.github.com/users/cenkozan/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/cenkozan/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/cenkozan/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/cenkozan/subscriptions\",\"organizations_url\":\"https://api.github.com/users/cenkozan/orgs\",\"repos_url\":\"https://api.github.com/users/cenkozan/repos\",\"events_url\":\"https://api.github.com/users/cenkozan/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/cenkozan/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Cenk Ozan Kahraman\",\"company\":\"2GD Application Solutions\",\"blog\":null,\"location\":\"Istanbul\",\"email\":null,\"hireable\":true,\"bio\":null,\"public_repos\":9,\"public_gists\":0,\"followers\":4,\"following\":6,\"created_at\":\"2011-10-10T13:33:57Z\",\"updated_at\":\"2016-08-09T17:40:01Z\"}";
    JsonUtils.writeStringToObject(githubString, GitHubOwner.class);
  }
}
