package co.fr8.data.controls;

import co.fr8.data.controls.interfaces.IContainerControl;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for ListTemplate object
 */
public class ListTemplate extends AbstractControlDefinition implements IContainerControl<ControlDefinitionDTO> {

  private List<ControlDefinitionDTO> template = new ArrayList<>();

  public ListTemplate() {
  }

  @Override
  public Iterable<ControlDefinitionDTO> enumerateChildren() {
    return template;
  }

  public List<ControlDefinitionDTO> getTemplate() {
    return template;
  }
}
