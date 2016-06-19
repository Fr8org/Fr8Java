package co.fr8.data.controls.impl;

import co.fr8.data.controls.ControlTypeEnum;
import co.fr8.data.controls.ListItem;
import co.fr8.data.interfaces.dto.ControlDefinitionDTO;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Object implementation of the DropDownList control which provides serialization
 * to JSON
 */
public class DropDownList extends ControlDefinitionDTO {

  private List<ListItem> listItems = new ArrayList<>();
  private String selectedKey;
  private boolean hasRefreshButton = false;
  private FieldDTO selectedItem;

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

  public ListItem findBySelected() {
    if (StringUtils.isNotBlank(selectedKey)) {
      Optional<ListItem> selected =
          listItems.stream().filter(i -> i.getKey().equalsIgnoreCase(selectedKey)).findFirst();

      if (selected.isPresent())
        return selected.get();
    }

    Logger.debug("ListItem not found for " + selectedKey);

    return null;
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

  public FieldDTO getSelectedItem() {
    return selectedItem;
  }

  public void setSelectedItem(FieldDTO selectedItem) {
    this.selectedItem = selectedItem;
  }
}
