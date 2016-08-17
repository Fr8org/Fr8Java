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

  /**
   * You have to get the example requests from the Heroku logs.
   */
  private static JsonNode json= Json.parse("{\"ActivityDTO\":{\"label\":null,\"activityTemplate\":{\"name\":\"Monitor Github Repository\",\"version\":\"5\",\"terminalName\":\"terminalGithub\",\"terminalVersion\":\"5\"},\"planId\":\"209ba0d9-01e4-4cb0-ac60-0a3b4f611902\",\"parentPlanNodeId\":\"3db6950a-ddc1-4838-82d3-338e0dfa6647\",\"ordering\":1,\"id\":\"ad7b8c48-4fe2-4913-9b6e-9048007d5355\",\"crateStorage\":{\"crates\":[{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"fcad04b9-2c7c-4187-98b4-901177445803\",\"label\":\"Configuration_Controls\",\"contents\":{\"Controls\":[{\"listItems\":[{\"selected\":false,\"key\":\"chalish\",\"value\":\"cenkozan/chalish\"},{\"selected\":false,\"key\":\"Codename_base\",\"value\":\"cenkozan/Codename_base\"},{\"selected\":false,\"key\":\"coffee-mob\",\"value\":\"cenkozan/coffee-mob\"},{\"selected\":false,\"key\":\"doom3.gpl\",\"value\":\"cenkozan/doom3.gpl\"},{\"selected\":false,\"key\":\"external-merge-with-quicksort\",\"value\":\"cenkozan/external-merge-with-quicksort\"},{\"selected\":false,\"key\":\"flux\",\"value\":\"cenkozan/flux\"},{\"selected\":false,\"key\":\"number26-demo\",\"value\":\"cenkozan/number26-demo\"},{\"selected\":false,\"key\":\"slate\",\"value\":\"cenkozan/slate\"},{\"selected\":false,\"key\":\"vimconfig\",\"value\":\"cenkozan/vimconfig\"},{\"selected\":false,\"key\":\"Fr8.NET\",\"value\":\"Fr8org/Fr8.NET\"},{\"selected\":false,\"key\":\"Fr8.Python\",\"value\":\"Fr8org/Fr8.Python\"},{\"selected\":false,\"key\":\"Fr8.Ruby\",\"value\":\"Fr8org/Fr8.Ruby\"},{\"selected\":false,\"key\":\"Fr8Core\",\"value\":\"Fr8org/Fr8Core\"},{\"selected\":false,\"key\":\"Fr8Java\",\"value\":\"Fr8org/Fr8Java\"}],\"selectedKey\":\"Fr8Java\",\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":\"repoList\",\"required\":false,\"value\":\"Fr8org/Fr8Java\",\"label\":\"Select a repository to monitor\",\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"groupName\":\"Specify Branch\",\"radios\":[{\"selected\":true,\"name\":\"All\",\"value\":\"all\",\"controls\":[]},{\"selected\":false,\"name\":\"Only\",\"value\":\"only\",\"controls\":[{\"listItems\":[],\"selectedKey\":null,\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":null,\"label\":null,\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]}],\"name\":null,\"required\":false,\"value\":\"all\",\"label\":null,\"type\":\"RadioButtonGroup\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"class\":\"\",\"name\":\"detectEvents\",\"required\":false,\"value\":\"Detect events involving an\",\"label\":null,\"type\":\"TextBlock\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"name\":\"issue\",\"required\":false,\"value\":null,\"label\":\"Issue\",\"type\":\"CheckBox\",\"selected\":true,\"events\":[{\"name\":\"onChange\",\"handler\":\"requestConfig\"},{\"name\":\"onChange\",\"handler\":\"requestConfig\"}],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"name\":\"pullRequest\",\"required\":false,\"value\":null,\"label\":\"Pull Request\",\"type\":\"CheckBox\",\"selected\":false,\"events\":[{\"name\":\"onChange\",\"handler\":\"requestConfig\"},{\"name\":\"onChange\",\"handler\":\"requestConfig\"}],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null}]},\"childrenActivities\":[],\"authTokenId\":null,\"authToken\":{\"id\":\"0695f6e3-2819-4283-ae0f-89857f3f83e8\",\"token\":\"0b869d4c11b00cd87c3a144dd538f5d10754de53\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"cenkozan\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"c28a3267-53ae-4d19-bbfd-59080a66b4d3\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":\"00000000-0000-0000-0000-000000000000\"},\"documentation\":null},\"ContainerId\":\"00000000-0000-0000-0000-000000000000\",\"ExplicitData\":null}");

}
