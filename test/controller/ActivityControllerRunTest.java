package controller;

import base.AbstractFakeApplication;
import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.data.interfaces.dto.CrateStorageDTO;
import co.fr8.data.interfaces.dto.PayloadDTO;
import co.fr8.terminal.infrastructure.DefaultHubCommunicator;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.ActivitiesController;
import github.GitHubTerminal;
import github.activities.request.CreateGithubIssueRequest;
import github.service.GitHubService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.UUID;

import static co.fr8.play.ApplicationConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerRunTest extends AbstractFakeApplication {

  @Mock
  private Http.Request request;

  GitHubTerminal gitHubTerminal;
  ActivitiesController activitiesController = new ActivitiesController();

  @Mock
  private DefaultHubCommunicator defaultHubCommunicatorMock;

  @Before
  public void setUp() throws Exception {
    Http.RequestBuilder rb = new Http.RequestBuilder();
    rb.header(FR8_USER_ID, "c28a3267-53ae-4d19-bbfd-59080a66b4d3");
    rb.header(FR8HUBCALLBACKSECRET, "10182f5701e3429e8c72ea33006eb1ab");
    rb.header(FR8_HUB_CALLBACK_URL, "http://dev.fr8.co");
    rb.bodyJson(json);
    Http.Context context = new Http.Context(rb);
    Http.Context.current.set(context);
//    gitHubTerminal.
  }

  @Test
  public void testRun() {
    CreateGithubIssueRequest request = new CreateGithubIssueRequest("b678aca80bfe6525e70158b6c992413022c7ab52", "cenkozan", "Fr8Java", "denme", "deneme");
    GitHubService.getInstance().createGithubIssue(request);
    PayloadDTO payloadDTO = new PayloadDTO();
    payloadDTO.setContainerId(UUID.randomUUID());
    CrateStorageDTO storageDTO = new CrateStorageDTO();
    CrateDTO operationalStateDTO = new CrateDTO();
    operationalStateDTO.setManifestType(MT.OperationalStatus.getFriendlyName());
    storageDTO.setCrates(new CrateDTO[]{operationalStateDTO});
    payloadDTO.setCrateStorage(storageDTO);
//    when(defaultHubCommunicatorMock.getPayload(anyString())).thenReturn(payloadDTO);
//    activitiesController.
    Result result = new ActivitiesController().runActivities();
    assertEquals(Http.Status.OK, result.status());
  }

  /**
   * You have to get the example requests from the Heroku logs.
   */
  private static JsonNode json = Json.parse("{\"ActivityDTO\":{\"label\":null,\"activityTemplate\":{\"name\":\"Create Github Issue\",\"version\":\"5\",\"terminalName\":\"terminalGithub\",\"terminalVersion\":\"5\"},\"planId\":\"747391d7-dbf2-4c78-a30e-f61e0e8ed8e6\",\"parentPlanNodeId\":\"e07985d5-50d8-4584-8e61-43ed444e78e3\",\"ordering\":1,\"id\":\"f82dfb46-7519-42bd-8c81-01b01d4d0cec\",\"crateStorage\":{\"crates\":[{\"manifestType\":\"Standard UI Controls\",\"manifestId\":6,\"manufacturer\":null,\"manifestRegistrar\":\"www.fr8.co/registry\",\"id\":\"febdc04d-2e99-440a-a703-03a7e3d7aaac\",\"label\":\"Configuration_Controls\",\"contents\":{\"Controls\":[{\"class\":\"\",\"name\":\"information\",\"required\":false,\"value\":\"Update which Github Issue\",\"label\":null,\"type\":\"TextBlock\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"listItems\":[{\"selected\":false,\"key\":\"chalish\",\"value\":\"cenkozan/chalish\"},{\"selected\":false,\"key\":\"Codename_base\",\"value\":\"cenkozan/Codename_base\"},{\"selected\":false,\"key\":\"coffee-mob\",\"value\":\"cenkozan/coffee-mob\"},{\"selected\":false,\"key\":\"doom3.gpl\",\"value\":\"cenkozan/doom3.gpl\"},{\"selected\":false,\"key\":\"external-merge-with-quicksort\",\"value\":\"cenkozan/external-merge-with-quicksort\"},{\"selected\":false,\"key\":\"flux\",\"value\":\"cenkozan/flux\"},{\"selected\":false,\"key\":\"number26-demo\",\"value\":\"cenkozan/number26-demo\"},{\"selected\":false,\"key\":\"slate\",\"value\":\"cenkozan/slate\"},{\"selected\":false,\"key\":\"vimconfig\",\"value\":\"cenkozan/vimconfig\"},{\"selected\":false,\"key\":\"Fr8.NET\",\"value\":\"Fr8org/Fr8.NET\"},{\"selected\":false,\"key\":\"Fr8.Python\",\"value\":\"Fr8org/Fr8.Python\"},{\"selected\":false,\"key\":\"Fr8.Ruby\",\"value\":\"Fr8org/Fr8.Ruby\"},{\"selected\":false,\"key\":\"Fr8Core\",\"value\":\"Fr8org/Fr8Core\"},{\"selected\":false,\"key\":\"Fr8Java\",\"value\":\"Fr8org/Fr8Java\"}],\"selectedKey\":\"Fr8Java\",\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":null,\"required\":false,\"value\":\"Fr8org/Fr8Java\",\"label\":\"Repo Name\",\"type\":\"DropDownList\",\"selected\":false,\"events\":[],\"source\":null,\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":false},{\"initialLabel\":\"Title\",\"upstreamSourceLabel\":\" \",\"textValue\":\"Deneme\",\"valueSource\":\"specific\",\"groupLabelText\":null,\"HasValue\":true,\"HasUpstreamValue\":false,\"HasSpecificValue\":true,\"ValueSourceIsNotSet\":false,\"listItems\":[],\"selectedKey\":null,\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":\"title\",\"required\":false,\"value\":null,\"label\":null,\"type\":\"TextSource\",\"selected\":false,\"events\":[],\"source\":{\"manifestType\":\"Field Description\",\"label\":\" \",\"filterByTag\":null,\"requestUpstream\":false,\"availabilityType\":3},\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":true},{\"initialLabel\":\"Body\",\"upstreamSourceLabel\":\" \",\"textValue\":\"Deneme\",\"valueSource\":\"specific\",\"groupLabelText\":null,\"HasValue\":true,\"HasUpstreamValue\":false,\"HasSpecificValue\":true,\"ValueSourceIsNotSet\":false,\"listItems\":[],\"selectedKey\":null,\"hasRefreshButton\":false,\"selectedItem\":null,\"name\":\"body\",\"required\":false,\"value\":null,\"label\":null,\"type\":\"TextSource\",\"selected\":false,\"events\":[],\"source\":{\"manifestType\":\"Field Description\",\"label\":\" \",\"filterByTag\":null,\"requestUpstream\":false,\"availabilityType\":3},\"showDocumentation\":null,\"isHidden\":false,\"isCollapsed\":true}]},\"parentCrateId\":null,\"createTime\":\"\",\"sourceActivityId\":null}]},\"childrenActivities\":[],\"authTokenId\":\"e500b1fa-0232-44a8-95dd-db923fa8bdcc\",\"authToken\":{\"id\":\"e500b1fa-0232-44a8-95dd-db923fa8bdcc\",\"token\":\"b678aca80bfe6525e70158b6c992413022c7ab52\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"cenkozan\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"c28a3267-53ae-4d19-bbfd-59080a66b4d3\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":\"00000000-0000-0000-0000-000000000000\"},\"documentation\":null},\"ContainerId\":\"4a9003b0-15fd-4408-9af4-5333d8b67787\",\"ExplicitData\":null}");

}
