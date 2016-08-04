package github;

import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.terminal.base.AbstractTerminalService;
import github.activities.GitForwardRepositoriesActivity;
import github.activities.GitGetRepositoriesActivity;
import github.activities.GitProcessRepositoriesActivity;
import github.activities.MonitorRepositoriesActivity;
import play.Logger;

import java.text.MessageFormat;
import java.util.UUID;

import static co.fr8.play.ApplicationConstants.*;
import static github.util.GitHubTerminalConstants.ALL_ACTIVITIES;
import static github.util.GitHubTerminalConstants.GITHUB_TERMINAL_DTO;

/**
 * TODO: Implement
 */
public class GitHubTerminal extends AbstractTerminalService {

  private Authentication authentication = new Authentication();

  public GitHubTerminal() {
    super(new GitHubTerminalEvent(), TERMINAL_NAME);
  }

  @Override
  public StandardFr8TerminalCM discover() {
    Logger.debug("Discover called host is: " + TERMINAL_HOST);
    StandardFr8TerminalCM ret = new StandardFr8TerminalCM(GITHUB_TERMINAL_DTO,
        ALL_ACTIVITIES);
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
    registerActivity(new MonitorRepositoriesActivity());
    registerActivity(new GitForwardRepositoriesActivity());
    registerActivity(new GitProcessRepositoriesActivity());
    registerActivity(new GitGetRepositoriesActivity());
  }
}
