package github.util;

import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.TerminalDTO;
import co.fr8.data.interfaces.dto.WebServiceDTO;
import co.fr8.data.states.ActivityCategoryEnum;
import co.fr8.data.states.AuthenticationTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static co.fr8.play.ApplicationConstants.LIST_REPOSITORIES_ACTIVITY_NAME;
import static co.fr8.play.ApplicationConstants.TERMINAL_HOST;
import static co.fr8.play.ApplicationConstants.TERMINAL_NAME;

/**
 * TODO: DOCUMENT
 */
public class GitHubTerminalConstants {

  public static final TerminalDTO GITHUB_TERMINAL_DTO =
      new TerminalDTO(TERMINAL_NAME, "GitHub", "1", /* TODO: decouple this from play */TERMINAL_HOST,
          "GitHub Terminal which monitors commits to a repository", AuthenticationTypeEnum.EXTERNAL.getCode());

  public static final WebServiceDTO GITHUB_WEBSERVICE_DTO  =
      new WebServiceDTO("GitHub", "https://assets-cdn.github.com/favicon.ico");

  public static final ActivityTemplateDTO GITHUB_LIST_REPOS_TEMPLATE_DTO =
      new ActivityTemplateDTO(LIST_REPOSITORIES_ACTIVITY_NAME, "Subscribe to GitHub Repository",
          "1", GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, ActivityCategoryEnum.MONITORS,
          true, 380);

  public static final ActivityTemplateDTO GITHUB_GET_EXAMPLE_DTO =
      new ActivityTemplateDTO("Git GET Example", "Get all repositories", "1",
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, ActivityCategoryEnum.RECEIVERS, true, 100);

  public static final ActivityTemplateDTO GITHUB_PROCESS_EXAMPLE_DTO =
      new ActivityTemplateDTO("Git PROCESS Example", "Process git info", "1",
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, ActivityCategoryEnum.PROCESSORS, true, 380);

  public static final ActivityTemplateDTO GITHUB_FORWARD_EXAMPLE_DTO =
      new ActivityTemplateDTO("Git FORWARD Example", "Forward git info", "1",
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, ActivityCategoryEnum.FORWARDERS, true, 380);


  public static final List<ActivityTemplateDTO> ALL_ACTIVITIES = new ArrayList<>(
      Arrays.asList(GITHUB_LIST_REPOS_TEMPLATE_DTO, GITHUB_GET_EXAMPLE_DTO, GITHUB_PROCESS_EXAMPLE_DTO, GITHUB_FORWARD_EXAMPLE_DTO));
}
