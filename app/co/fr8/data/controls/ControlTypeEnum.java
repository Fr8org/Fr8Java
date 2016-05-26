package co.fr8.data.controls;

/**
 * Enum class which defines all the control types available to activities
 */
public enum ControlTypeEnum {

  TEXTBOX("TextBox"),
  TEXTBOXBIG("TextBoxBig"),
  CHECKBOX("CheckBox"),
  DROPDOWN_LIST("DropDownList"),
  RADIO_BUTTON_GROUP("RadioButtonGroup"),
  FILTER_PANE("FilterPane"),
  MAPPING_PANE("MappingPane"),
  TEXT_BLOCK("TextBlock"),
  FILE_PICKER("FilePicker"),
  ROUTING("Routing"),
  FIELD_LIST("FieldList"),
  BUTTON("Button"),
  TEXT_SOURCE("TextSource"),
  TEXT_AREA("TextArea"),
  QUERY_BUILDER("QueryBuilder"),
  MANAGE_PLAN("ManagePlan"),
  DURATION("Duration"),
  RUN_PLAN_BUTTON("RunPlanButton"),
  UPSTREAM_DATA_CHOOSER("UpstreamDataChooser"),
  UPSTREAM_FIELD_CHOOSER("UpstreamFieldChooser"),
  UPSTREAM_CRATE_CHOOSER("UpstreamCrateChooser"),
  DATE_PICKER("DatePicker"),
  CRATE_CHOOSER("CrateChooser"),
  CONTAINER_TRANSITION("ContainerTransition"),
  META_CONTROL_CONTAINER("MetaControlContainer"),
  CONTROL_LIST("ControlList"),
  SELECT_DATA("SelectData"),
  EXTERNAL_OBJECT_CHOOSER("ExternalObjectChooser"),
  BUILD_MESSAGE_APPENDER("BuildMessageAppender");

  private final String hubName;

  ControlTypeEnum(String hubName) {
    this.hubName = hubName;
  }

  public String getHubName() {
    return hubName;
  }
}
