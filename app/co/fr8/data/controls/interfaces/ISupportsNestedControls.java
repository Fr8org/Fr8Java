package co.fr8.data.controls.interfaces;

import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement
 */
public interface ISupportsNestedControls {

  List<ControlDefinitionDTO> controls = new ArrayList<>();

  List<ControlDefinitionDTO> getControls();
}
