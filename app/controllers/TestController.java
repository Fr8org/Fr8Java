package controllers;

import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.Fr8DataDTO;
import co.fr8.terminal.base.ActivityPayload;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import github.GitHubTerminal;
import play.mvc.Controller;
import play.mvc.Result;

import static co.fr8.play.ApplicationConstants.TERMINAL_NAME;

public class TestController extends Controller {

  public Result configTest() {
    GitHubTerminal terminal = new GitHubTerminal();

    ActivityDTO activityDTO =
        JsonUtils.writeStringToActivityDTO(CONFIGURE_INPUT);

    Object resultDTO = terminal.handleFr8Request(TERMINAL_NAME, "configure",
        new Fr8DataDTO(activityDTO, CID));

    if (resultDTO instanceof ActivityPayload) {
      resultDTO = new ActivityDTO((ActivityPayload) resultDTO);
//      return ok(JsonUtils.writeObjectAsString(resultDTO));
    }

    String responseString = JsonUtils.prettyPrintObjectString(resultDTO);

    Logger.debug("handleFr8Request returned: " + responseString);

    return ok(responseString);

  }

  private static final String CID = "00000000-0000-0000-0000-000000000000";

  private static final String CONFIGURE_INPUT = "{\"Label\":null,\"Name\":\"Subscribe to GitHub Repository\",\"activityTemplate\":{\"id\":\"f5af5dfb-0a67-416c-99b9-611485fd11ca\",\"name\":\"List GitHub Repositories Activity\",\"version\":\"1\",\"label\":\"Subscribe to GitHub Repository\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"1\",\"endpoint\":\"http://localhost:9000\"},\"tags\":null,\"category\":\"Monitors\",\"type\":\"Standard\",\"minPaneWidth\":380,\"needsAuthentication\":true,\"webService\":{\"id\":17,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"RootPlanNodeId\":\"d833b668-3d49-4d98-8109-692183ad8cf7\",\"ParentPlanNodeId\":\"5dcea9d5-db98-46af-ac1a-0f4ce3eea38d\",\"CurrentView\":null,\"Ordering\":1,\"Id\":\"76f69cd4-15dd-449e-a8bb-92e572a4c715\",\"CrateStorage\":{\"crates\":[]},\"ChildrenActivities\":[],\"AuthTokenId\":null,\"AuthToken\":{\"Id\":\"48479d3f-221d-4e30-b353-c1c0d620084a\",\"Token\":\"d2bb85051aeb50485805b6d353ea47d31514739f\",\"ExternalAccountId\":\"3037127\",\"ExternalAccountName\":\"3037127\",\"ExternalDomainId\":null,\"ExternalDomainName\":null,\"UserId\":\"a90aee0a-7c7b-4501-bede-f8ab20325b61\",\"ExternalStateToken\":null,\"AdditionalAttributes\":null,\"Error\":null,\"AuthCompletedNotificationRequired\":false,\"TerminalID\":0},\"documentation\":null}\n" +
      "[debug] - application - Looking for activity template with ";
}
