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
public class ActivityControllerConfigureTest extends AbstractFakeApplication {

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
  public void testConfigure() {
    Result result = new ActivitiesController().configureActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  @Test
  public void testActivate() {
    Result result = new ActivitiesController().activateActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  @Test
  public void testDeactivate() {
    Result result = new ActivitiesController().deactivateActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  /**
   * You have to get the example requests from the Heroku logs.
   */
  private static JsonNode json= Json.parse("{\"ActivityDTO\":{\"label\":null,\"activityTemplate\":{\"name\":\"Monitor Github Repository\",\"version\":\"5\",\"terminalName\":\"terminalGithub\",\"terminalVersion\":\"5\"},\"planId\":\"094bc829-445c-4deb-b4ff-5b6497bf147d\",\"parentPlanNodeId\":\"e46ce6d7-23af-441e-9558-38ec4a21f2c3\",\"ordering\":1,\"id\":\"530ef0c5-aa6f-415a-a3f2-aaccf736bded\",\"crateStorage\":{\"crates\":[{\"manifestType\":\"Standard Event Subscription\",\"manifestId\":8,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"2a5aa466-eaa4-449d-a3a1-d01eed7f74f4\",\"label\":\"Standard Event Subscriptions\",\"contents\":{\"Subscriptions\":[\"GithubEventReport\"],\"Manufacturer\":\"GitHub\"},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"2fa63601-6dfd-41a0-82cf-b801c8171b12\",\"label\":null,\"contents\":{\"crateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Pull Request Properties\",\"producedBy\":\"Monitor Github Repository\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"PullRequestNumber\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestNumber\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequesterGitHubName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestStatus\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"77b4868d-95cd-40d2-8198-20816858ca05\",\"label\":\"Configuration_Controls\",\"contents\":{\"Controls\":[{\"listItems\":[{\"selected\":false,\"key\":\"chalish\",\"value\":\"cenkozan/chalish\"},{\"selected\":false,\"key\":\"Codename_base\",\"value\":\"cenkozan/Codename_base\"},{\"selected\":false,\"key\":\"coffee-mob\",\"value\":\"cenkozan/coffee-mob\"},{\"selected\":false,\"key\":\"doom3.gpl\",\"value\":\"cenkozan/doom3.gpl\"},{\"selected\":false,\"key\":\"external-merge-with-quicksort\",\"value\":\"cenkozan/external-merge-with-quicksort\"},{\"selected\":false,\"key\":\"flux\",\"value\":\"cenkozan/flux\"},{\"selected\":false,\"key\":\"number26-demo\",\"value\":\"cenkozan/number26-demo\"},{\"selected\":false,\"key\":\"slate\",\"value\":\"cenkozan/slate\"},{\"selected\":false,\"key\":\"vimconfig\",\"value\":\"cenkozan/vimconfig\"},{\"selected\":false,\"key\":\"Fr8.NET\",\"value\":\"Fr8org/Fr8.NET\"},{\"selected\":false,\"key\":\"Fr8.Python\",\"value\":\"Fr8org/Fr8.Python\"},{\"selected\":false,\"key\":\"Fr8.Ruby\",\"value\":\"Fr8org/Fr8.Ruby\"},{\"selected\":false,\"key\":\"Fr8Core\",\"value\":\"Fr8org/Fr8Core\"},{\"selected\":false,\"key\":\"Fr8Java\",\"value\":\"Fr8org/Fr8Java\"}],\"selectedKey\":\"Fr8Java\",\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":\"Fr8org/Fr8Java\",\"label\":\"Select a repository to monitor\",\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"listItems\":[],\"selectedKey\":null,\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":null,\"label\":null,\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"groupName\":\"Specify Branch\",\"radios\":[{\"selected\":false,\"name\":\"All\",\"value\":\"all\",\"controls\":[]},{\"selected\":true,\"name\":\"Only\",\"value\":\"only\",\"controls\":[{\"listItems\":[],\"selectedKey\":null,\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":null,\"label\":null,\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]}],\"name\":null,\"required\":false,\"value\":\"only\",\"label\":null,\"type\":\"RadioButtonGroup\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"class\":\"\",\"name\":\"detectEvents\",\"required\":false,\"value\":\"Detect events involving an\",\"label\":null,\"type\":\"TextBlock\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"name\":\"issue\",\"required\":false,\"value\":null,\"label\":\"Issue\",\"type\":\"CheckBox\",\"selected\":true,\"events\":[{\"name\":\"onChange\",\"handler\":\"requestConfig\"},{\"name\":\"onChange\",\"handler\":\"requestConfig\"}],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"name\":\"pullRequest\",\"required\":false,\"value\":null,\"label\":\"Pull Request\",\"type\":\"CheckBox\",\"selected\":false,\"events\":[{\"name\":\"onChange\",\"handler\":\"requestConfig\"},{\"name\":\"onChange\",\"handler\":\"requestConfig\"}],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"8c81f1bf-98f1-4200-9e7b-2f389c5f71dd\",\"label\":null,\"contents\":{\"crateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Pull Request Properties\",\"producedBy\":\"Monitor Github Repository\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"PullRequestNumber\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestNumber\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequesterGitHubName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestStatus\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"9e4cbc75-1df3-48a3-a1ad-acce31e41969\",\"label\":null,\"contents\":{\"crateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Issue Properties\",\"producedBy\":\"Monitor Github Repository\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"IssueName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"IssueDescription\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"IssueStatus\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null}]},\"childrenActivities\":[],\"authTokenId\":null,\"authToken\":{\"id\":\"e6d34ba5-7cdf-4b33-a1e3-955149e08d41\",\"token\":\"c862f2bf0c7728e9aa5a255821cc5ce02c26a228\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"cenkozan\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"c28a3267-53ae-4d19-bbfd-59080a66b4d3\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":\"00000000-0000-0000-0000-000000000000\"},\"documentation\":null},\"ContainerId\":\"00000000-0000-0000-0000-000000000000\",\"ExplicitData\":null}");

}
