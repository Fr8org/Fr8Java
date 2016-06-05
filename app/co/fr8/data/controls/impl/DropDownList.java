package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Object implementation of the DropDownList control which provides serialization
 * to JSON
 */
public class DropDownList extends ControlDefinitionDTO {

  private List<ListItem> listItems = new ArrayList<>();
  private String selectedKey;

  @JsonProperty("hasRefreshButton")
  private boolean hasRefreshButton = false;

  /**
   * Constructor for the DropDownList class
   * Calls the ActivityTemplateSubplanDTO constructor passing the
   * ControlTypeEnum.DROPDOWN_LIST enum value to set the type property
   */
  public DropDownList() {
    super(ControlTypeEnum.DROPDOWN_LIST);
  }

  public DropDownList(String label) {
    super(ControlTypeEnum.DROPDOWN_LIST);
    this.setLabel(label);
  }

  public DropDownList(ControlTypeEnum type) {
    super(type);
  }

  public void selectByKey(String key) {
    listItems.forEach(item -> {
      if (item.getKey().equalsIgnoreCase(key)) {
        setValue(item.getValue());
        selectedKey = item.getKey();
      }
    });
  }

  public void selectByValue(String value) {
    listItems.forEach(item -> {
      if (item.getValue().equalsIgnoreCase(value)) {
        setValue(item.getValue());
        selectedKey = item.getKey();
      }
    });
  }

  public List<ListItem> getListItems() {
    return listItems;
  }

  public void setListItems(List<ListItem> listItems) {
    this.listItems = listItems;
  }

  public String getSelectedKey() {
    return selectedKey;
  }

  public void setSelectedKey(String selectedKey) {
    this.selectedKey = selectedKey;
  }

  public boolean isHasRefreshButton() {
    return hasRefreshButton;
  }

  public void setHasRefreshButton(boolean hasRefreshButton) {
    this.hasRefreshButton = hasRefreshButton;
  }
}
