package github.util;

import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.CategoriesDTO;
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
 * Very important part because all the initialization is here, while you think
 */
public class GitHubTerminalConstants {

  private static String version = "5";
  private static int terminalStatus = 1;
  private static int minWidth = 330;
  private static String githubName = "GitHub";

  public static final TerminalDTO GITHUB_TERMINAL_DTO =
      new TerminalDTO(TERMINAL_NAME, "GitHub", version, terminalStatus, TERMINAL_HOST,
          "GitHub Terminal which monitors commits to a repository", AuthenticationTypeEnum.EXTERNAL.getCode());

  public static final WebServiceDTO GITHUB_WEBSERVICE_DTO  =
      new WebServiceDTO("GitHub", "https://assets-cdn.github.com/favicon.ico");

  private static CategoriesDTO githubCategory = new CategoriesDTO(githubName, "https://assets-cdn.github.com/favicon.ico");
  private static CategoriesDTO monitorCategory = new CategoriesDTO(ActivityCategoryEnum.MONITORS.getFriendlyName(), "/Content/icons/monitor-icon-64x64.png");
  private static CategoriesDTO receiverCategory = new CategoriesDTO(ActivityCategoryEnum.RECEIVERS.getFriendlyName(), "/Content/icons/get-icon-64x64.png");
  private static CategoriesDTO processCategory = new CategoriesDTO(ActivityCategoryEnum.PROCESSORS.getFriendlyName(), "/Content/icons/process-icon-64x64.png");
  private static CategoriesDTO sendersCategory = new CategoriesDTO(ActivityCategoryEnum.SENDERS.getFriendlyName(), "/Content/icons/forward-icon-64x64.png");

  private static List<CategoriesDTO> githubListRepoCategories = new ArrayList<>();
  private static List<CategoriesDTO> githubGetCategories = new ArrayList<>();
  private static List<CategoriesDTO> githubProcessCategories = new ArrayList<>();
  private static List<CategoriesDTO> githubSendersCategories = new ArrayList<>();

  private static List<CategoriesDTO> getGithubListRepoCategories() {
    githubListRepoCategories.add(githubCategory);
    githubListRepoCategories.add(monitorCategory);
    return githubListRepoCategories;
  }

  private static List<CategoriesDTO> getGithubGetCategories() {
    githubGetCategories.add(githubCategory);
    githubGetCategories.add(receiverCategory);
    return githubGetCategories;
  }

  private static List<CategoriesDTO> getGithubProcessCategories() {
    githubProcessCategories.add(githubCategory);
    githubProcessCategories.add(processCategory);
    return githubProcessCategories;
  }

  private static List<CategoriesDTO> getGithubSendersCategories() {
    githubSendersCategories.add(githubCategory);
    githubSendersCategories.add(sendersCategory);
    return githubSendersCategories;
  }

  public static final ActivityTemplateDTO GITHUB_LIST_REPO_TEMPLATE_DTO =
      new ActivityTemplateDTO("8479D60E-0777-4D4A-856A-654C4ED5D215", LIST_REPOSITORIES_ACTIVITY_NAME, "Subscribe to GitHub Repository", version,
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getGithubListRepoCategories(), ActivityCategoryEnum.MONITORS, true, minWidth);

  public static final ActivityTemplateDTO GITHUB_GET_EXAMPLE_DTO =
      new ActivityTemplateDTO("41D3ADAC-1509-4639-BE11-9684E3459C5B", "Git GET Example", "Get all repositories", version,
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getGithubGetCategories(), ActivityCategoryEnum.RECEIVERS, true, minWidth);

  public static final ActivityTemplateDTO GITHUB_PROCESS_EXAMPLE_DTO =
      new ActivityTemplateDTO("B2804A47-67E9-4305-B012-D5A154AA4DA2", "Git PROCESS Example", "Process git info", version,
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getGithubProcessCategories(), ActivityCategoryEnum.PROCESSORS, true, minWidth);

  //public static final ActivityTemplateDTO GITHUB_FORWARD_EXAMPLE_DTO = new ActivityTemplateDTO("Git FORWARD Example", "Forward git info", version, GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getGithubSendersCategories(), ActivityCategoryEnum.SENDERS, true, minWidth);


  public static final List<ActivityTemplateDTO> ALL_ACTIVITIES = new ArrayList<>(
      Arrays.asList(GITHUB_LIST_REPO_TEMPLATE_DTO, GITHUB_GET_EXAMPLE_DTO, GITHUB_PROCESS_EXAMPLE_DTO));
}
