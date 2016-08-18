package github.activities.request;

import java.util.Arrays;

public class WebhookRequest {
  public String name;
  public boolean active;
  public String[] events;
  public Config config;

  public WebhookRequest(String name, boolean active, String[] events, Config config) {
    this.name = name;
    this.active = active;
    this.events = events;
    this.config = config;
  }

  @Override
  public String toString() {
    return "{" +
        "name=" + name +
        ", active='" + active + '\'' +
        ", events='" + Arrays.toString(events) + '\'' +
        ", config='" + config.toString() + '\'' +
        '}';
  }

  public static class Config {
    public String url;
    public String contentType;

    public Config(String url, String contentType) {
      this.url = url;
      this.contentType = contentType;
    }

    @Override
    public String toString() {
      return "{" +
          "url=" + url +
          ", content_type='" + contentType + '\'' +
          '}';
    }
  }
}
