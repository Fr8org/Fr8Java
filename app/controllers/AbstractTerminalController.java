package controllers;

import co.fr8.terminal.base.AbstractTerminalService;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;

/**
 * TODO: Document
 */
public class AbstractTerminalController<T extends AbstractTerminalService>
    extends Controller {

  protected T terminal;

  /**
   * TODO: DOCUMENT
   * @param form
   */
  protected void debugForm(DynamicForm form) {
    if (Logger.isDebugEnabled()) {
      form.data().forEach((k,v) -> {
        Logger.debug("form contains key " + k + " with value " + v);
      });
    }
  }

  /**
   * TODO: DOCUMENT
   * @param message
   * @return
   */
  protected ObjectNode createErrorResponse(String message) {
    return Json.newObject().put("ErrorMessage", message);

  }
}
