package co.fr8.data.controls;

/**
 * This class defines an object which represents an element in a dropdown list UI
 * control
 */
public class ListItem {

  private boolean selected = false;
  private String key;
  private String value;

  public ListItem() {
  }

  public ListItem(boolean selected, String key, String value) {
    this.selected = selected;
    this.key = key;
    this.value = value;
  }

  public ListItem(String key, String value) {
    this(false, key, value);
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ListItem{" +
        "selected=" + selected +
        ", key='" + key + '\'' +
        ", value='" + value + '\'' +
        '}';
  }
}
