package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class Application extends Controller {

  public Result configure() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("configure");

    logRequest(request());

    return ok(json);
  }

  public Result activate() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("activate");

    logRequest(request());

    return ok(json);
  }

  public Result execute() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("execute");

    logRequest(request());

    return ok(json);
  }

  public Result discover() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("discover");

    logRequest(request());

    return ok(json);
  }

  public Result configurePost() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("configurePost");

    logRequest(request());

    return ok(json);
  }

  public Result activatePost() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("activatePost");

    logRequest(request());

    return ok(json);
  }

  public Result executePost() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("executePost");

    logRequest(request());

    return ok(json);
  }

  public Result discoverPost() {
    ObjectNode json = Json.newObject();

    if (Logger.isInfoEnabled())
      Logger.info("discoverPost");

    logRequest(request());

    return ok(json);
  }

  private void logRequest(Http.Request request) {
    if(Logger.isInfoEnabled()) {
      StringBuilder requestString = new StringBuilder("Request:")
          .append("\tbody [")
          .append(request().body())
          .append("]")
          .append("\tparameters [");
      DynamicForm form = Form.form().bindFromRequest();

      if(form != null) {
        form.data().forEach((key, value) -> {
          requestString.append("\tkey: ")
              .append(key)
              .append(", value: ")
              .append(value);
        });
      }

      requestString.append("\t]");

      Logger.info(requestString.toString());
    }
  }

}
