package base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;

public class AbstractFakeApplication extends WithApplication {

  public static FakeApplication app;

  @BeforeClass
  public static void startApp() {
    app = Helpers.fakeApplication();
    Helpers.start(app);
  }

  @Override
  protected Application provideApplication() {
    return new GuiceApplicationBuilder()
        .configure("play.http.router", "router.Routes")
        .build();
  }

  @AfterClass
  public static void stopApp() {
    Helpers.stop(app);
  }

}
