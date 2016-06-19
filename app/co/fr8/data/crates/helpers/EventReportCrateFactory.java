package co.fr8.data.crates.helpers;

import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.manifests.EventCM;

import java.util.Arrays;
import java.util.List;

/**
 * TODO: Implement
 */
public class EventReportCrateFactory {
  public Crate create(String eventName, String palletId, Crate[] crates) {
    return create(eventName, palletId, Arrays.asList(crates));
  }

  public Crate create(String eventName, String palletId, List<Crate> crates) {
    EventCM eventCM = new EventCM(eventName, palletId);

    // TODO: fix this
    eventCM.getCrateStorage().add(crates.toArray(new Crate[crates.size()]));

    return Crate.fromContent("Fr8 Terminal Event or Incident Report", eventCM);
  }

  public Crate create(EventCM eventCm) {
    return Crate.fromContent("Fr8 Terminal Event or Incident Report", eventCm);
//
//            var eventDTOContent = JsonConvert.SerializeObject(eventDTO);
//            return new CrateDTO()
//            {
//                Id = Guid.NewGuid().ToString(),
//                Label = "Dockyard Plugin Event or Incident Report",
//                Contents = eventDTOContent,
//                ManifestType = "Dockyard Plugin Event or Incident Report",
//                ManifestId = 2
//            };
  }
}
