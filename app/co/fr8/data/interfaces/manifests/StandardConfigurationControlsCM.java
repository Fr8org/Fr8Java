package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement
 */
public class StandardConfigurationControlsCM extends Manifest {

  List<ControlDefinitionDTO> controls;

  public StandardConfigurationControlsCM() {
    super(MT.StandardConfigurationControls);
    controls = new ArrayList<>();
  }

  public StandardConfigurationControlsCM(List<ControlDefinitionDTO> controls) {
    super(MT.StandardConfigurationControls);
    this.controls = controls;
  }

  public StandardConfigurationControlsCM cloneProperties(Manifest manifest) {
    return null;
  }

  public List<ControlDefinitionDTO> getControls() {
    return controls;
  }
}
