package co.fr8.terminal.base;

import co.fr8.data.crates.ICrateStorage;
import co.fr8.data.interfaces.dto.ActivityDTO;
import co.fr8.data.interfaces.dto.AuthorizationTokenDTO;
import co.fr8.data.interfaces.manifests.StandardConfigurationControlsCM;

/**
 * TODO: Implement
 */
abstract public class EnhancedTerminalActivity<T extends StandardConfigurationControlsCM>
     {


  protected ICrateStorage currentActivityStorage;
  protected ActivityDTO currentActivity;
  protected AuthorizationTokenDTO authorizationToken;
  protected T configurationControls;
  protected UpstreamQueryManager upstreamQueryManager;
  protected UIBuilder uiBuilder;
  protected int loopIndex;
  protected boolean disableValidationOnFollowup;

}
