package co.fr8.data.controls.impl;

import co.fr8.data.constants.MT;
import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.interfaces.dto.FieldSourceDTO;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO implementation for the TextSource UI control
 */
public class TextSource extends DropDownList {

  private String initialLabel;
  private String upstreamSourceLabel;
  private String textValue;
  private String valueSource;
  private String groupLabelText;
  @JsonProperty("HasValue")
  private boolean hasValue;
  @JsonProperty("HasUpstreamValue")
  private boolean hasUpstreamValue;
  @JsonProperty("HasSpecificValue")
  private boolean hasSpecificValue;
  @JsonProperty("ValueSourceIsNotSet")
  private boolean valueSourceIsNotSet;


  public TextSource() {
    super(ControlTypeEnum.TEXT_SOURCE);
  }

  public TextSource(String initialLabel, String upstreamSourceLabel, String name) {
    this();
    this.initialLabel = initialLabel;
    this.upstreamSourceLabel = upstreamSourceLabel;
    this.setName(name);
    // TODO: Make sure that the CrateManifestType.getFriendlyName() method is correct here
    this.setSource(new FieldSourceDTO(upstreamSourceLabel, MT.FieldDescription.getFriendlyName()));
  }

  public String getValue(AbstractCrateStorage payloadCrateStorage) {
    switch(valueSource.toLowerCase()) {
      case "specific":
        return textValue;
      case "upstream":
        if (payloadCrateStorage == null) {
          Logger.error("Can't resolve upstream value without payload crate storage provided");
        }
        return payloadCrateStorage.findField(getSelectedKey());
      default:
        return null;
    }
  }

//  public boolean canGetValue(AbstractCrateStorage payloadCrateStorage) {
////    if (hasSpecifiedValue())
////      return true;
//
//    return !("upstream".equalsIgnoreCase(this.valueSource) && payloadCrateStorage == null && !hasSpecificValue());
//  }

//  public boolean hasValue() {
//    return StringUtils.isNotBlank(this.valueSource) && (hasUpstreamValue() || hasSpecificValue());
//  }
//
//  public boolean hasUpstreamValue() {
//    return ("upstream".equalsIgnoreCase(this.valueSource) && StringUtils.isNotBlank(getValue()));
//  }
//
//  public boolean hasSpecificValue() {
//    return ("specific".equalsIgnoreCase(this.valueSource) && StringUtils.isNotBlank(this.textValue));
//  }

  public String getInitialLabel() {
    return initialLabel;
  }

  public void setInitialLabel(String initialLabel) {
    this.initialLabel = initialLabel;
  }

  public String getUpstreamSourceLabel() {
    return upstreamSourceLabel;
  }

  public void setUpstreamSourceLabel(String upstreamSourceLabel) {
    this.upstreamSourceLabel = upstreamSourceLabel;
  }

  public String getTextValue() {
    return textValue;
  }

  public void setTextValue(String textValue) {
    this.textValue = textValue;
  }

  public String getValueSource() {
    return valueSource;
  }

  public void setValueSource(String valueSource) {
    this.valueSource = valueSource;
  }

  public boolean isHasValue() {
    return hasValue;
  }

  public void setHasValue(boolean hasValue) {
    this.hasValue = hasValue;
  }

  public boolean isHasUpstreamValue() {
    return hasUpstreamValue;
  }

  public void setHasUpstreamValue(boolean hasUpstreamValue) {
    this.hasUpstreamValue = hasUpstreamValue;
  }

  public boolean isHasSpecificValue() {
    return hasSpecificValue;
  }

  public void setHasSpecificValue(boolean hasSpecificValue) {
    this.hasSpecificValue = hasSpecificValue;
  }

  public boolean isValueSourceIsNotSet() {
    return valueSourceIsNotSet;
  }

  public void setValueSourceIsNotSet(boolean valueSourceIsNotSet) {
    this.valueSourceIsNotSet = valueSourceIsNotSet;
  }

  public String getGroupLabelText() {
    return groupLabelText;
  }

  public void setGroupLabelText(String groupLabelText) {
    this.groupLabelText = groupLabelText;
  }
}
