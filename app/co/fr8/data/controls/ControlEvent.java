package co.fr8.data.controls;

/**
 * The ControlEvent class defines a name and a handler that is used by a Control.
 *
 * When the Control is rendered in the UI, the event name maps to a JavaScript
 * event and the handler property specifies the name of the JavaScript method
 * that will handle the event.
 */
public class ControlEvent {
  
  public final String name;
  public final String handler;

  public ControlEvent(String name, String handler) {
    this.name = name;
    this.handler = handler;
  }

  public String getName() {
    return name;
  }

  public String getHandler() {
    return handler;
  }

  public static ControlEvent getRequestConfigOnChange() {
    return new ControlEvent("onChange", "requestConfig");
  }

  public static ControlEvent getRequestConfigOnClick() {
    return new ControlEvent("onClick", "requestConfig");
  }
}
