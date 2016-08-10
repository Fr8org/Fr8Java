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

import static co.fr8.play.ApplicationConstants.*;

/**
 * TODO: DOCUMENT
 * Very important part because all the initialization takes place here.
 */
public class GitHubTerminalConstants {

  private static String version = "5";
  private static int terminalStatus = 1;
  private static int minWidth = 330;
  private static String githubName = "GitHub";

  public static final TerminalDTO GITHUB_TERMINAL_DTO =
      new TerminalDTO(TERMINAL_NAME, "GitHub", version, terminalStatus, TERMINAL_HOST,
          "GitHub Terminal", AuthenticationTypeEnum.EXTERNAL.getCode());

  public static final WebServiceDTO GITHUB_WEBSERVICE_DTO  =
      new WebServiceDTO("GitHub", "https://assets-cdn.github.com/favicon.ico");

  private static CategoriesDTO githubCategory = new CategoriesDTO(githubName, "https://assets-cdn.github.com/favicon.ico");
  private static CategoriesDTO triggersCategory = new CategoriesDTO(ActivityCategoryEnum.TRIGGERS.getFriendlyName(), "/Content/icons/monitor-icon-64x64.png");
  private static CategoriesDTO processCategory = new CategoriesDTO(ActivityCategoryEnum.PROCESSORS.getFriendlyName(), "/Content/icons/process-icon-64x64.png");
//  private static CategoriesDTO receiverCategory = new CategoriesDTO(ActivityCategoryEnum.RECEIVERS.getFriendlyName(), "/Content/icons/get-icon-64x64.png");
//  private static CategoriesDTO sendersCategory = new CategoriesDTO(ActivityCategoryEnum.SENDERS.getFriendlyName(), "/Content/icons/forward-icon-64x64.png");

//  private static List<CategoriesDTO> githubGetCategories = new ArrayList<>();
//  private static List<CategoriesDTO> githubProcessCategories = new ArrayList<>();
//  private static List<CategoriesDTO> githubSendersCategories = new ArrayList<>();

  public static final ActivityTemplateDTO TRIGGER_GITHUB_PULL_REQUEST_DTO =
      new ActivityTemplateDTO(MONITOR_PULL_REQUEST_ACTIVITY_ID, TRIGGER_GITHUB_PULL_REQUEST_ACTIVITY_NAME, TRIGGER_GITHUB_PULL_REQUEST_ACTIVITY_NAME, version,
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getCategories(triggersCategory, githubCategory), ActivityCategoryEnum.TRIGGERS, true, minWidth);

  public static final ActivityTemplateDTO TRIGGER_GITHUB_REPOSITORY_DTO =
      new ActivityTemplateDTO(MONITOR_GITHUB_REPOSITORY_ACTIVITY_ID, TRIGGER_GITHUB_REPOSITORY_ACTIVITY_NAME, TRIGGER_GITHUB_REPOSITORY_ACTIVITY_NAME, version,
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getCategories(triggersCategory, githubCategory), ActivityCategoryEnum.TRIGGERS, true, minWidth);

  public static final ActivityTemplateDTO UPDATE_GITHUB_ISSUE_DTO =
      new ActivityTemplateDTO(UPDATE_GITHUB_ISSUE_ACTIVITY_ID, UPDATE_GITHUB_ISSUE_ACTIVITY_NAME, UPDATE_GITHUB_ISSUE_ACTIVITY_NAME, version,
          GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getCategories(githubCategory, processCategory), ActivityCategoryEnum.PROCESSORS, true, minWidth);
//  public static final ActivityTemplateDTO GITHUB_PROCESS_EXAMPLE_DTO = new ActivityTemplateDTO("B2804A47-67E9-4305-B012-D5A154AA4DA2", "Git PROCESS Example", "Process git info", version, GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getCategories(githubProcessCategories, processCategory), ActivityCategoryEnum.PROCESSORS, true, minWidth);
  //public static final ActivityTemplateDTO GITHUB_FORWARD_EXAMPLE_DTO = new ActivityTemplateDTO("Git FORWARD Example", "Forward git info", version, GITHUB_WEBSERVICE_DTO, GITHUB_TERMINAL_DTO, "", getGithubSendersCategories(), ActivityCategoryEnum.SENDERS, true, minWidth);


  public static final List<ActivityTemplateDTO> ALL_ACTIVITIES = new ArrayList<>(
      Arrays.asList(TRIGGER_GITHUB_PULL_REQUEST_DTO, TRIGGER_GITHUB_REPOSITORY_DTO, UPDATE_GITHUB_ISSUE_DTO));


  // Category must be under github so that all activities are organized under Github in the Hub's Plan Builder.
  private static List<CategoriesDTO> getCategories(CategoriesDTO category, CategoriesDTO mainCategory) {
    List<CategoriesDTO> categories = new ArrayList<>();
    categories.add(category);
    categories.add(mainCategory);
    return categories;
  }

}
