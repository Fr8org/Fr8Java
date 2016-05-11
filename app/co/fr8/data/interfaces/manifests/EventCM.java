package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.CrateManifestSerializerAnnotation;
import co.fr8.data.crates.CrateStorage;
import co.fr8.data.crates.ICrateStorage;

/**
 * TODO: Implement
 */
//@CrateManifestSerializerAnnotation(serializer=EventSerializer)
public class EventCM extends Manifest {

  private String eventName;
  private String palletId;
  private ICrateStorage crateStorage;

  public EventCM() {
    super(MT.EventOrIncidentReport);
    this.crateStorage = new CrateStorage();
  }

  public EventCM(String eventName, String palletId) {
    super(MT.EventOrIncidentReport);
    this.eventName = eventName;
    this.palletId = palletId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getPalletId() {
    return palletId;
  }

  public void setPalletId(String palletId) {
    this.palletId = palletId;
  }

  public ICrateStorage getCrateStorage() {
    return crateStorage;
  }

  public void setCrateStorage(ICrateStorage crateStorage) {
    this.crateStorage = crateStorage;
  }
}
