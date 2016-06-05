package github;

import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.data.states.ActivityCategoryEnum;
import co.fr8.data.states.AuthenticationTypeEnum;
import co.fr8.terminal.base.AbstractTerminalService;
import github.activities.ListRepositoriesActivity;
import play.Logger;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.UUID;

import static co.fr8.play.ApplicationConstants.*;
import static github.util.GithubTerminalConstants.GITHUB_LIST_REPOS_TEMPLATE_DTO;
import static github.util.GithubTerminalConstants.GITHUB_TERMINAL_DTO;

/**
 * TODO: Implement
 */
public class GitHubTerminal extends AbstractTerminalService {

  private Authentication authentication = new Authentication();

  public GitHubTerminal() {
    super(new GitHubTerminalEvent());
  }

  @Override
  public StandardFr8TerminalCM discover() {

    Logger.debug("Discover called host is: " + TERMINAL_HOST);

    StandardFr8TerminalCM ret = new StandardFr8TerminalCM(GITHUB_TERMINAL_DTO,
       Collections.singletonList(GITHUB_LIST_REPOS_TEMPLATE_DTO));

    Logger.debug("Created new StandardFr8TerminalCM " + ret);

    return ret;
  }

  @Override
  public ExternalAuthUrlDTO generateExternalAuthUrl() {
    String uuid = UUID.randomUUID().toString();
    return new ExternalAuthUrlDTO(MessageFormat.format(GITHUB_OAUTH_BASE_URL, uuid), uuid);
  }

  @Override
  public AuthorizationToken authenticateToken(ExternalAuthDTO externalAuthDTO) {

    return authentication.authenticate(externalAuthDTO);
  }

  @Override
  public void registerActivities() {
    registerActivity(new ListRepositoriesActivity());
  }
}
