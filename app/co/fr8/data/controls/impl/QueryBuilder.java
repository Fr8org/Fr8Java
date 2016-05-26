package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;

/**
 * DTO definition for a QueryBuilder control
 */
public class QueryBuilder extends ControlDefinitionDTO {

  public QueryBuilder() {
    super(ControlTypeEnum.QUERY_BUILDER);
  }
}
