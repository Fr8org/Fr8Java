package controller;

import base.AbstractFakeApplication;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.ActivitiesController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerConfigureTest extends AbstractFakeApplication {

  @Mock
  private Http.Request request;

  @Before
  public void setUp() throws Exception {
    Http.RequestBuilder rb = new Http.RequestBuilder();
    rb.bodyJson(json);
    Http.Context context = new Http.Context(rb);
    Http.Context.current.set(context);
  }

  @Test
  public void testConfigure() {
    Result result = new ActivitiesController().configureActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  @Test
  public void testActivate() {
    Result result = new ActivitiesController().activateActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  @Test
  public void testDeactivate() {
    Result result = new ActivitiesController().deactivateActivities();
    assertEquals(play.mvc.Http.Status.OK, result.status());
  }

  /**
   * You have to get the example requests from the Heroku logs.
   */
  private static JsonNode json= Json.parse("{\"ActivityDTO\":{\"label\":null,\"name\":\"Get all repositories\",\"activityTemplate\":{\"id\":\"41d3adac-1509-4639-be11-9684e3459c5b\",\"name\":\"Git GET Example\",\"version\":\"5\",\"label\":\"Get all repositories\",\"terminal\":{\"name\":\"terminalGithub\",\"label\":\"GitHub\",\"version\":\"5\",\"endpoint\":\"http://fr8java.herokuapp.com\"},\"tags\":\"\",\"category\":\"Receivers\",\"type\":\"Standard\",\"minPaneWidth\":330,\"needsAuthentication\":true,\"webService\":{\"id\":848,\"name\":\"GitHub\",\"iconPath\":\"https://assets-cdn.github.com/favicon.ico\"}},\"rootPlanNodeId\":\"f7664a1e-442b-482a-8b14-8bdedab8be80\",\"parentPlanNodeId\":\"3745494f-04b0-491f-ae17-817ba419f813\",\"currenView\":null,\"ordering\":1,\"id\":\"c84a3bf0-c6b4-4531-b405-dc273c779361\",\"crateStorage\":{\"crates\":[]},\"childrenActivities\":[],\"authTokenId\":null,\"authToken\":{\"id\":\"2f657f20-cfed-4de9-95c0-c907c1df60df\",\"token\":\"4718a5ab3c970688e2479ad0549dd791d284fa6b\",\"externalAccountId\":\"1116432\",\"externalAccountName\":\"1116432\",\"externalDomainId\":null,\"externalDomainName\":null,\"userId\":\"284cbafb-b0c3-43b3-be83-257ec5c98d4c\",\"externalStateToken\":null,\"additionalAttributes\":null,\"error\":null,\"expiresAt\":null,\"authCompletedNotificationRequired\":false,\"terminalId\":0},\"documentation\":null},\"ContainerId\":\"00000000-0000-0000-0000-000000000000\",\"ExplicitData\":null}");

}
