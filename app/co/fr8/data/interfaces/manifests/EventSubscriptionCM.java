package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * TODO: IMPLEMENT
 */
public class EventSubscriptionCM extends Manifest {

  @JsonProperty("Subscriptions")
  public List<String> subscriptions;

  @JsonProperty("Manufacturer")
  public String manufacturer;

  public EventSubscriptionCM() {
    super(MT.StandardEventSubscription);
    this.manufacturer = manufacturer;
  }

  public EventSubscriptionCM(List<String> subscriptions, String manufacturer) {
    super(MT.StandardEventSubscription);
    this.subscriptions = subscriptions;
    this.manufacturer = manufacturer;
  }

  public List<String> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(List<String> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }
}
