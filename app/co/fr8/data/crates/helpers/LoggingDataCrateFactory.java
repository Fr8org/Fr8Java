package co.fr8.data.crates.helpers;

import co.fr8.data.crates.Crate;
import co.fr8.data.interfaces.manifests.LoggingDataCM;

/**
 * TODO: Implement
 */
public class LoggingDataCrateFactory {
  public Crate create(LoggingDataCM loggingDataCm) {
    return Crate.fromContent("Dockyard Terminal Event or Incident Report", loggingDataCm);

    // var serializer = new JsonSerializer();
    // var contents = serializer.Serialize(loggingData);

//            var contents = JsonConvert.SerializeObject(loggingData);
//
//            return new CrateDTO()
//            {
//                Id = Guid.NewGuid().ToString(),
//                Label = "Dockyard Plugin Event or Incident Report",
//                Contents = contents,
//                ManifestType = "Dockyard Plugin Event or Incident Report",
//                ManifestId = 3
//            };
  }

}
