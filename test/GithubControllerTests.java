import controllers.GitHubTerminalController;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

public class GithubControllerTests extends WithApplication {

  @Override
  protected Application provideApplication() {
    return new GuiceApplicationBuilder()
        .configure("play.http.router", "router.Routes")
        .build();
  }

  @Test
  public void testDiscover() {
    Result result = new GitHubTerminalController().discover();
    assertEquals(OK, result.status());
    System.out.println(contentAsString(result));
  }

}
