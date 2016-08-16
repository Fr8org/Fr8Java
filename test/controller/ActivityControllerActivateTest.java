package controller;

import base.AbstractFakeApplication;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.ActivitiesController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerActivateTest extends AbstractFakeApplication {

  @Mock
  private Http.Request request;

  @Before
  public void setUp() throws Exception {
    Http.RequestBuilder rb = new Http.RequestBuilder();
    rb.bodyJson(json);
    Http.Context context = new Http.Context(rb);
    Http.Context.current.set(context);
  }

  @Test
  public void testActivate() {
    Result result = new ActivitiesController().activateActivities();
    assertEquals(Http.Status.OK, result.status());
  }

  /**
   * You have to get the example requests from the Heroku logs.
   */
  private static JsonNode json= Json.parse("{\"ActivityDTO\":{\"label\":null,\"activityTemplate\":{\"name\":\"Monitor Github Repository\",\"version\":\"5\",\"terminalName\":\"terminalGithub\",\"terminalVersion\":\"5\"},\"planId\":\"fd05cc45-1c82-49a6-b88d-6e491c84f697\",\"parentPlanNodeId\":\"22566db7-6dc5-44d8-a962-5d8bfd4ffdc0\",\"ordering\":1,\"id\":\"30b4da4b-965b-4e20-9492-46490925b195\",\"crateStorage\":{\"crates\":[{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"5316b2a8-728a-4505-9b18-3e999e2d3bfe\",\"label\":null,\"contents\":{\"crateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Issue Properties\",\"producedBy\":\"Monitor Github Repository\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"IssueName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"IssueDescription\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"IssueStatus\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"e1c9bf3a-d6ef-4fad-8965-efe729b12643\",\"label\":null,\"contents\":{\"crateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Pull Request Properties\",\"producedBy\":\"Monitor Github Repository\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"PullRequestNumber\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestNumber\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequesterGitHubName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestStatus\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"fcca2545-3889-43e0-bb29-4666a6abc438\",\"label\":\"Configuration_Controls\",\"contents\":{\"Controls\":[{\"listItems\":[{\"selected\":false,\"key\":\"chalish\",\"value\":\"cenkozan/chalish\"},{\"selected\":false,\"key\":\"Codename_base\",\"value\":\"cenkozan/Codename_base\"},{\"selected\":false,\"key\":\"coffee-mob\",\"value\":\"cenkozan/coffee-mob\"},{\"selected\":false,\"key\":\"doom3.gpl\",\"value\":\"cenkozan/doom3.gpl\"},{\"selected\":false,\"key\":\"external-merge-with-quicksort\",\"value\":\"cenkozan/external-merge-with-quicksort\"},{\"selected\":false,\"key\":\"flux\",\"value\":\"cenkozan/flux\"},{\"selected\":false,\"key\":\"number26-demo\",\"value\":\"cenkozan/number26-demo\"},{\"selected\":false,\"key\":\"slate\",\"value\":\"cenkozan/slate\"},{\"selected\":false,\"key\":\"vimconfig\",\"value\":\"cenkozan/vimconfig\"},{\"selected\":false,\"key\":\"Fr8.NET\",\"value\":\"Fr8org/Fr8.NET\"},{\"selected\":false,\"key\":\"Fr8.Python\",\"value\":\"Fr8org/Fr8.Python\"},{\"selected\":false,\"key\":\"Fr8.Ruby\",\"value\":\"Fr8org/Fr8.Ruby\"},{\"selected\":false,\"key\":\"Fr8Core\",\"value\":\"Fr8org/Fr8Core\"},{\"selected\":false,\"key\":\"Fr8Java\",\"value\":\"Fr8org/Fr8Java\"}],\"selectedKey\":\"Fr8Java\",\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":\"repoList\",\"required\":false,\"value\":\"Fr8org/Fr8Java\",\"label\":\"Select a repository to monitor\",\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"groupName\":\"Specify Branch\",\"radios\":[{\"selected\":true,\"name\":\"All\",\"value\":\"all\",\"controls\":[]},{\"selected\":false,\"name\":\"Only\",\"value\":\"only\",\"controls\":[{\"listItems\":[{\"selected\":false,\"key\":\"dev\",\"value\":\"dev\"},{\"selected\":false,\"key\":\"fr-5043\",\"value\":\"fr-5043\"},{\"selected\":false,\"key\":\"fr-5174-1\",\"value\":\"fr-5174-1\"},{\"selected\":false,\"key\":\"fr-5174\",\"value\":\"fr-5174\"},{\"selected\":false,\"key\":\"fr-5175\",\"value\":\"fr-5175\"},{\"selected\":false,\"key\":\"fr-5521\",\"value\":\"fr-5521\"},{\"selected\":false,\"key\":\"master\",\"value\":\"master\"}],\"selectedKey\":null,\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":null,\"label\":null,\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]}],\"name\":null,\"required\":false,\"value\":\"all\",\"label\":null,\"type\":\"RadioButtonGroup\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"class\":\"\",\"name\":\"detectEvents\",\"required\":false,\"value\":\"Detect events involving an\",\"label\":null,\"type\":\"TextBlock\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"name\":\"issue\",\"required\":false,\"value\":null,\"label\":\"Issue\",\"type\":\"CheckBox\",\"selected\":false,\"events\":[{\"name\":\"onChange\",\"handler\":\"requestConfig\"},{\"name\":\"onChange\",\"handler\":\"requestConfig\"}],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"name\":\"pullRequest\",\"required\":false,\"value\":null,\"label\":\"Pull Request\",\"type\":\"CheckBox\",\"selected\":true,\"events\":[{\"name\":\"onChange\",\"handler\":\"requestConfig\"},{\"name\":\"onChange\",\"handler\":\"requestConfig\"}],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null}]},\"childrenActivities\":[],\"authTokenId\":\"1183c1c2-718d-44e5-95b6-0c1f8b10921c\",\"authToken\":{\"id\":\"1183c1c2-718d-44e5-95b6-0c1f8b10921c\",\"token\":\"5ad3f004418cd2d6139133e2440eae7a0d6ef776\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"cenkozan\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"c28a3267-53ae-4d19-bbfd-59080a66b4d3\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":\"00000000-0000-0000-0000-000000000000\"},\"documentation\":null},\"ContainerId\":\"00000000-0000-0000-0000-000000000000\",\"ExplicitData\":null}");

}
