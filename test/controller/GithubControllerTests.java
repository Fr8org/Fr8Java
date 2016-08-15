package controller;

import base.AbstractFakeApplication;
import controllers.GitHubTerminalController;
import org.junit.Test;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

public class GithubControllerTests extends AbstractFakeApplication {

  @Test
  public void testDiscover() {
    Result result = new GitHubTerminalController().discover();
    assertEquals(OK, result.status());
    System.out.println(contentAsString(result));
  }

}
