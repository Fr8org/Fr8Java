package github;

import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.data.states.ActivityCategoryEnum;
import co.fr8.data.states.AuthenticationTypeEnum;
import co.fr8.terminal.base.AbstractTerminalService;
import play.Logger;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.UUID;

import static co.fr8.play.ApplicationConstants.*;

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

    TerminalDTO terminalDTO = new TerminalDTO(TERMINAL_NAME, "GitHub", "1",
        /* TODO: decouple this from play */TERMINAL_HOST, "GitHub Terminal which monitors commits to a repository",
        AuthenticationTypeEnum.EXTERNAL.getCode());

    WebServiceDTO webServiceDTO = new WebServiceDTO("GitHub",
        "https://assets-cdn.github.com/favicon.ico");

    ActivityTemplateDTO subscribeActivity = new ActivityTemplateDTO("GitHub Subscribe",
        "Subscribe to GitHub Repository", "1", webServiceDTO, terminalDTO, ActivityCategoryEnum.MONITORS,
        true, 380);

    ActivityTemplateDTO pullActivity = new ActivityTemplateDTO("GitHub Pull",
        "Pull from GitHub Repository", "1", webServiceDTO, terminalDTO, ActivityCategoryEnum.FORWARDERS,
        true, 380);

    StandardFr8TerminalCM ret = new StandardFr8TerminalCM(terminalDTO,
       Arrays.asList(subscribeActivity, pullActivity));

    Logger.debug("Created new StandardFr8TerminalCM " + ret);

    return ret;
  }

  @Override
  public ExternalAuthUrlDTO generateExternalAuthUrl() {
    String uuid = UUID.randomUUID().toString();
    return new ExternalAuthUrlDTO(MessageFormat.format(GITHUB_OAUTH_BASE_URL, uuid), uuid);
  }

  @Override
  public AuthorizationTokenDTO authenticateToken(ExternalAuthDTO externalAuthDTO) {

    return authentication.authenticate(externalAuthDTO);
  }
}
