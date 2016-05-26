package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import co.fr8.util.CollectionUtils;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;

import java.util.*;

/**
 * DTO for the FIeldList UI control
 */
public class FieldList extends ControlDefinitionDTO {

  public FieldList() {
    super(ControlTypeEnum.FIELD_LIST);
  }

  @Override
  public void reset(List<String> fieldNames) {

    Logger.debug("Resetting " + fieldNames + " in " + getValue());

    if (CollectionUtils.isNotEmpty(fieldNames)) {

      ObjectNode newValue = Json.newObject();
      JsonNode jsonValue = JsonUtils.writeStringToObject(getValue());

      if (jsonValue != null) {
        Iterator<String> fieldNameIterator = jsonValue.fieldNames();

        String currentFieldName;
        while (fieldNameIterator.hasNext()) {
          currentFieldName = fieldNameIterator.next();
          if (fieldNames.contains(currentFieldName)) {

            Logger.debug("Resetting field " + currentFieldName);
            newValue.put(currentFieldName, StringUtils.EMPTY);
          } else {
            Logger.debug("Copying " + currentFieldName + " to new map");
            newValue.put(currentFieldName, jsonValue.get(currentFieldName));
          }
        }
        setValue(newValue.asText());

        Logger.debug("Set value to " + newValue.asText());

        return;
      }
    }

    Logger.debug("Reset value property to empty constant " + EMPTY_BRACKETS);

    resetToEmpty();
  }
}
