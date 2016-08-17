package github.util;

import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.CategoriesDTO;
import co.fr8.data.interfaces.dto.TerminalDTO;
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

  private static CategoriesDTO githubCategory = new CategoriesDTO("00000000-0000-0000-0000-000000000000", githubName, "https://assets-cdn.github.com/favicon.ico");
  private static CategoriesDTO triggersCategory = new CategoriesDTO("417dd061-27a1-4dec-aecd-4f468013fd24", ActivityCategoryEnum.TRIGGERS.getFriendlyName(), "/Content/icons/monitor-icon-64x64.png");
  private static CategoriesDTO processCategory = new CategoriesDTO("69fb6d2c-2083-4696-9457-b7b152d358c2", ActivityCategoryEnum.PROCESSORS.getFriendlyName(), "/Content/icons/process-icon-64x64.png");

  public static final ActivityTemplateDTO TRIGGER_GITHUB_PULL_REQUEST_DTO =
      new ActivityTemplateDTO(TRIGGER_GITHUB_PULL_REQUEST_ACTIVITY_ID, TRIGGER_GITHUB_PULL_REQUEST_ACTIVITY_NAME, TRIGGER_GITHUB_PULL_REQUEST_ACTIVITY_NAME, version,
          "", getCategories(triggersCategory, githubCategory), true, minWidth);

  public static final ActivityTemplateDTO TRIGGER_GITHUB_REPOSITORY_DTO =
      new ActivityTemplateDTO(TRIGGER_GITHUB_REPOSITORY_ACTIVITY_ID, TRIGGER_GITHUB_REPOSITORY_ACTIVITY_NAME, TRIGGER_GITHUB_REPOSITORY_ACTIVITY_NAME, version,
          "", getCategories(triggersCategory, githubCategory), true, minWidth);

  public static final ActivityTemplateDTO UPDATE_GITHUB_ISSUE_DTO =
      new ActivityTemplateDTO(UPDATE_GITHUB_ISSUE_ACTIVITY_ID, UPDATE_GITHUB_ISSUE_ACTIVITY_NAME, UPDATE_GITHUB_ISSUE_ACTIVITY_NAME, version,
          "", getCategories(githubCategory, processCategory), true, minWidth);

  public static final ActivityTemplateDTO CREATE_GITHUB_ISSUE_DTO =
      new ActivityTemplateDTO(CREATE_GITHUB_ISSUE_ACTIVITY_ID, CREATE_GITHUB_ISSUE_ACTIVITY_NAME, CREATE_GITHUB_ISSUE_ACTIVITY_NAME, version,
          "", getCategories(githubCategory, processCategory), true, minWidth);

  public static final List<ActivityTemplateDTO> ALL_ACTIVITIES = new ArrayList<>(
      Arrays.asList(UPDATE_GITHUB_ISSUE_DTO, CREATE_GITHUB_ISSUE_DTO));

  // Category must be under github so that all activities are organized under Github in the Hub's Plan Builder.
  private static List<CategoriesDTO> getCategories(CategoriesDTO category, CategoriesDTO mainCategory) {
    List<CategoriesDTO> categories = new ArrayList<>();
    categories.add(category);
    categories.add(mainCategory);
    return categories;
  }

}
