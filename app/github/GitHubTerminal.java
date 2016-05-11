package github;

import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.TerminalDTO;
import co.fr8.data.interfaces.dto.WebServiceDTO;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.data.states.ActivityCategoryEnum;
import co.fr8.data.states.AuthenticationTypeEnum;
import co.fr8.terminal.base.AbstractTerminalService;
import play.Logger;

import java.util.Arrays;

/**
 * TODO: Implement
 */
public class GitHubTerminal extends AbstractTerminalService {

  public GitHubTerminal() {
    super(new GitHubTerminalEvent());
  }

  @Override
  public StandardFr8TerminalCM discover() {

    Logger.debug("Discover called");

    TerminalDTO terminalDTO = new TerminalDTO("GitHub Java Terminal", "GitHub", "1",
        "http://fr8java.herokuapp.com", "GitHub Terminal which monitors commits to a repository",
        AuthenticationTypeEnum.INTERNAL.getCode());

    WebServiceDTO webServiceDTO = new WebServiceDTO("GitHub",
        "https://assets-cdn.github.com/favicon.ico");

    ActivityTemplateDTO subscribeActivity = new ActivityTemplateDTO("GitHub Subscribe",
        "Subscribe to GitHub Repository", "1", webServiceDTO, terminalDTO, ActivityCategoryEnum.RECEIVERS,
        true, 380);

    ActivityTemplateDTO pullActivity = new ActivityTemplateDTO("GitHub Pull",
        "Pull from GitHub Repository", "1", webServiceDTO, terminalDTO, ActivityCategoryEnum.FORWARDERS,
        true, 380);

    StandardFr8TerminalCM ret = new StandardFr8TerminalCM(terminalDTO,
       Arrays.asList(subscribeActivity, pullActivity));

    Logger.debug("Created new StandardFr8TerminalCM " + ret);

    return ret;
  }
}
