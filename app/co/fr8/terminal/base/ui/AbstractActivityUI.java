package co.fr8.terminal.base.ui;

import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import co.fr8.data.interfaces.manifests.StandardConfigurationControlsCM;

import java.util.List;


/**
 * TODO: Implement
 */
abstract public class AbstractActivityUI extends StandardConfigurationControlsCM {

  @Override
  public List<ControlDefinitionDTO> getControls() {
    consolidateControls();
    return controls;
  }

  abstract protected void consolidateControls();
}
