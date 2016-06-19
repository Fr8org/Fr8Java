package co.fr8.data.interfaces.dto;

import co.fr8.terminal.base.exception.KeyNotFoundException;
import co.fr8.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 TODO: Document
 */
public class PayloadObjectDTO {

  public List<FieldDTO> fieldList;


  public PayloadObjectDTO() {
    fieldList = new ArrayList<>();
  }

  public PayloadObjectDTO(List<FieldDTO> fieldList)  {
    this.fieldList = fieldList;
  }

  public PayloadObjectDTO(FieldDTO[] fieldData) {
    this(Arrays.asList(fieldData));
  }

  /**
   * Gets matching field value for the specified key
   * @param key
   * @param skipNull
   * @param ignoreCase
   * @return
   */
  public String getFirstFieldValueForKey(String key, boolean skipNull, boolean ignoreCase) {

    if (CollectionUtils.isNotEmpty(fieldList)) {
      for (FieldDTO field : fieldList) {
        if ((ignoreCase && StringUtils.equalsIgnoreCase(field.getKey(), key)) ||
            (!ignoreCase && StringUtils.equals(field.getKey(), key))) {
          if (StringUtils.isBlank(field.getValue()) && skipNull) {
            continue;
          }

          return field.getValue();
        }
      }
    }

    return StringUtils.EMPTY;
  }

  public String getValueForKey(String key, boolean ignoreCase)
      throws KeyNotFoundException {

     Optional<FieldDTO> fieldDTO =
      fieldList.stream().filter(field -> StringUtils.equalsIgnoreCase(field.getKey(), key)).findFirst();

    if (fieldDTO.isPresent())
      return fieldDTO.get().getValue();

    throw new KeyNotFoundException("Unable to find key " + key);
  }

  public List<String> geAllValuesForKey(String key, boolean ignoreCase) {

    List<String> ret = new ArrayList<>();

    if (CollectionUtils.isNotEmpty(fieldList)) {
//      ret = fieldList.stream().filter(field -> StringUtils.equalsIgnoreCase(field.getKey(), key)).collect(Collectors.toList());
      for (FieldDTO field : fieldList) {
        if (StringUtils.equalsIgnoreCase(field.getKey(), key)) {
          ret.add(field.getValue());
        }
      }

    }

    return ret;
  }
  
}
