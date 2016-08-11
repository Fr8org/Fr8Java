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
public class ActivityControllerTests extends AbstractFakeApplication {

  @Mock
  private Http.Request request;

  @Before
  public void setUp() throws Exception {
    Http.RequestBuilder rb = new Http.RequestBuilder();
    rb.bodyJson(jsonForRun);
    Http.Context context = new Http.Context(rb);
    Http.Context.current.set(context);
  }

  @Test
  public void testConfigure() {
    Result result = new ActivitiesController().configureActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  @Test
  public void testRun() {
    Result result = new ActivitiesController().runActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

//  @Test
//  public void testDefaultHubCommunicator() {
//    DefaultHubCommunicator hubCommunicator = new DefaultHubCommunicator();
//    Fr8HubSecurityDTO securityDTO = new Fr8HubSecurityDTO("c28a3267-53ae-4d19-bbfd-59080a66b4d3", "", "10182f5701e3429e8c72ea33006eb1ab");
//    hubCommunicator.configure(securityDTO);
//    System.out.println(hubCommunicator.getPayload(UUID.fromString("FF6C793B-381E-46E3-925A-80F747468F6A")));
//  }

  /**
   * You have to get the example requests from the Heroku logs.
   */
  private static JsonNode jsonForConfigure = Json.parse("{\"ActivityDTO\":{\"label\":null,\"name\":\"Get all repositories\",\"activityTemplate\":{\"id\":\"41d3adac-1509-4639-be11-9684e3459c5b\",\"name\":\"Git GET Example\",\"version\":\"5\",\"label\":\"Get all repositories\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"5\",\"endpoint\":\"http://fr8java.herokuapp.com\"},\"tags\":\"\",\"category\":\"Receivers\",\"type\":\"Standard\",\"minPaneWidth\":330,\"needsAuthentication\":true,\"webService\":{\"id\":848,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"rootPlanNodeId\":\"f7664a1e-442b-482a-8b14-8bdedab8be80\",\"parentPlanNodeId\":\"3745494f-04b0-491f-ae17-817ba419f813\",\"currenView\":null,\"ordering\":1,\"id\":\"c84a3bf0-c6b4-4531-b405-dc273c779361\",\"crateStorage\":{\"crates\":[]},\"childrenActivities\":[],\"authTokenId\":null,\"authToken\":{\"id\":\"2f657f20-cfed-4de9-95c0-c907c1df60df\",\"token\":\"4718a5ab3c970688e2479ad0549dd791d284fa6b\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"1116432\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"284cbafb-b0c3-43b3-be83-257ec5c98d4c\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":0},\"documentation\":null},\"ContainerId\":\"00000000-0000-0000-0000-000000000000\",\"ExplicitData\":null}");
  private static JsonNode jsonForRun = Json.parse("{\"ActivityDTO\":{\"label\":null,\"activityTemplate\":{\"name\":\"List GitHub Repositories Activity\",\"version\":\"5\",\"terminalName\":\"terminalGithub\",\"terminalVersion\":\"5\"},\"planId\":\"96f97b21-f42b-4fe2-a49f-d4ad7efb8cca\",\"parentPlanNodeId\":\"d82760a5-6a83-4673-afb7-1563963621c5\",\"ordering\":1,\"id\":\"7200aea0-8d46-44fb-83c4-824695e74bc2\",\"crateStorage\":{\"crates\":[{\"manifestType\":\"Crate Description\",\"manifestId\":32,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"1c8faa0c-0772-4ef7-8424-a732b8c4b337\",\"label\":null,\"contents\":{\"crateDescriptions\":[{\"manifestId\":5,\"manifestType\":\"Standard Payload Data\",\"label\":\"Repository Properties\",\"producedBy\":\"List GitHub Repositories Activity\",\"selected\":false,\"availability\":2,\"fields\":[{\"key\":\"Number\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"PullRequestName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"RequesterGitHubName\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3},{\"key\":\"Status\",\"label\":null,\"fieldType\":null,\"isRequired\":false,\"availability\":3}]}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null},{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"919d7988-4285-4137-984c-fb1d5ed16779\",\"label\":\"Configuration_Controls\",\"contents\":{\"Controls\":[{\"listItems\":[{\"selected\":false,\"key\":\"chalish\",\"value\":\"cenkozan/chalish\"},{\"selected\":false,\"key\":\"Codename_base\",\"value\":\"cenkozan/Codename_base\"},{\"selected\":false,\"key\":\"coffee-mob\",\"value\":\"cenkozan/coffee-mob\"},{\"selected\":false,\"key\":\"doom3.gpl\",\"value\":\"cenkozan/doom3.gpl\"},{\"selected\":false,\"key\":\"external-merge-with-quicksort\",\"value\":\"cenkozan/external-merge-with-quicksort\"},{\"selected\":false,\"key\":\"flux\",\"value\":\"cenkozan/flux\"},{\"selected\":false,\"key\":\"number26-demo\",\"value\":\"cenkozan/number26-demo\"},{\"selected\":false,\"key\":\"slate\",\"value\":\"cenkozan/slate\"},{\"selected\":false,\"key\":\"vimconfig\",\"value\":\"cenkozan/vimconfig\"},{\"selected\":false,\"key\":\"Fr8.NET\",\"value\":\"Fr8org/Fr8.NET\"},{\"selected\":false,\"key\":\"Fr8.Python\",\"value\":\"Fr8org/Fr8.Python\"},{\"selected\":false,\"key\":\"Fr8.Ruby\",\"value\":\"Fr8org/Fr8.Ruby\"},{\"selected\":false,\"key\":\"Fr8Core\",\"value\":\"Fr8org/Fr8Core\"},{\"selected\":false,\"key\":\"Fr8Java\",\"value\":\"Fr8org/Fr8Java\"}],\"selectedKey\":\"Fr8Java\",\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":\"Fr8org/Fr8Java\",\"label\":\"Select a repository to monitor\",\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null}]},\"childrenActivities\":[],\"authTokenId\":\"e322838c-bbba-4a43-9e1b-02e743e68bd1\",\"authToken\":{\"id\":\"e322838c-bbba-4a43-9e1b-02e743e68bd1\",\"token\":\"bf0e4ef0e1d8659bd96c5305813cccc4d95e8863\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"1116432\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"c28a3267-53ae-4d19-bbfd-59080a66b4d3\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":0},\"documentation\":null},\"ContainerId\":\"b7471a65-f789-40e9-950a-da7b1f3a04ec\",\"ExplicitData\":null}");

}
