package co.fr8.data.interfaces.dto;

/**
 * Data Transfer Object which represents Fr8HubSecurityDTO
 * Terminal will secure any requests for making further requests to the Hub
 */
public class Fr8HubSecurityDTO {

  private String fr8UserId;
  private String fr8HubCallBackUrl;
  private String fr8HubCallBackSecret;

  public Fr8HubSecurityDTO(String fr8UserId, String fr8HubCallBackUrl, String fr8HubCallBackSecret) {
    this.fr8UserId = fr8UserId;
    this.fr8HubCallBackUrl = fr8HubCallBackUrl;
    this.fr8HubCallBackSecret = fr8HubCallBackSecret;
  }

  public String getFr8HubCallBackSecret() {
    return fr8HubCallBackSecret;
  }

  public void setFr8HubCallBackSecret(String fr8HubCallBackSecret) {
    this.fr8HubCallBackSecret = fr8HubCallBackSecret;
  }

  public String getFr8HubCallBackUrl() {
    return fr8HubCallBackUrl;
  }

  public void setFr8HubCallBackUrl(String fr8HubCallBackUrl) {
    this.fr8HubCallBackUrl = fr8HubCallBackUrl;
  }

  public String getFr8UserId() {
    return fr8UserId;
  }

  public void setFr8UserId(String fr8UserId) {
    this.fr8UserId = fr8UserId;
  }

  @Override
  public String toString() {
    return "Fr8HubSecurityDTO{" +
        "fr8UserId='" + fr8UserId + '\'' +
        ", fr8HubCallBackUrl='" + fr8HubCallBackUrl + '\'' +
        ", fr8HubCallBackSecret='" + fr8HubCallBackSecret + '\'' +
        '}';
  }
}

