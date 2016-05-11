package controllers;

import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.util.json.JsonUtils;
import github.GitHubTerminal;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Charles Pretzer on 5/9/2016.
 */
public class GItHubTerminalController extends Controller {

  private GitHubTerminal gitHubTerminal = new GitHubTerminal();

  public Result discover() {
    Logger.debug("Call to terminals/discover");

    StandardFr8TerminalCM discoverCrateManifest = gitHubTerminal.discover();

    Logger.debug("Returning terminal info: " + discoverCrateManifest);

    return ok(JsonUtils.writeManifestJsonString(discoverCrateManifest));
  }
}
