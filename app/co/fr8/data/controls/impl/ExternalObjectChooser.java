package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ActivityTemplateSubplanDTO;

/**
 * The object representation of the ExternalObjectChooser control which provides
 * serialization to JSON.
 */
public class ExternalObjectChooser extends ActivityTemplateSubplanDTO {

  /**
   * Constructor for the SelectData class
   * Calls the ActivityTemplateSubplanDTO constructor passing the
   * ControlTypeEnum.EXTERNAL_OBJECT_CHOOSER enum value to set the type property
   */
  public ExternalObjectChooser() {
    super(ControlTypeEnum.EXTERNAL_OBJECT_CHOOSER);
  }
}
