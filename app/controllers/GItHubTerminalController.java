package controllers;

import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import github.GitHubTerminal;
import play.mvc.Result;


/**
 * TODO: Implement
 */
public class GitHubTerminalController extends AbstractTerminalController<GitHubTerminal> {

  public GitHubTerminalController() {
    terminal = new GitHubTerminal();
  }

  /**
   *
   * @return
   */
  public Result discover() {
    Logger.debug("Call to terminals/discover");

    StandardFr8TerminalCM discoverCrateManifest = terminal.discover();

    Logger.debug("Returning terminal info: " + discoverCrateManifest);

    return ok(JsonUtils.writeManifestJsonString(discoverCrateManifest));
  }
}
