package co.fr8.hub.managers;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.crates.Crate;
import co.fr8.data.entities.ContainerDO;
import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.*;
import co.fr8.data.states.AvailabilityTypeEnum;

import java.util.List;

/**
 * Interface which can be used to manage Crates within the context of a CrateStorage
 * object
 */
public interface ICrateManager {
  CrateDTO toDto(Crate crate);
  CrateStorageDTO toDto(AbstractCrateStorage storage);

  AbstractCrateStorage fromDto(CrateStorageDTO storageDto);
  Crate fromDto(CrateDTO crateDto);

  /**
   * TODO: Research how to port these method calls
   * ORIGINAL:
        IUpdatableCrateStorage UpdateStorage(Expression<Func<CrateStorageDTO>> storageAccessExpression);
        IUpdatableCrateStorage UpdateStorage(Expression<Func<string>> storageAccessExpression);
   */
  IUpdatableCrateStorage updateStorage(AbstractCrateStorage storageAccessExpression);
  IUpdatableCrateStorage updateStorage(CrateStorageDTO storageAccessExpression);
  IUpdatableCrateStorage updateStorage(String storageAccessExpression);

  boolean isEmptyStorage(CrateStorageDTO storageDto);
  String emptyStorageAsStr();
  String crateStorageAsStr(AbstractCrateStorage storage);
  void addLogMessage(String label, List<LogItemDTO> logItemList, ContainerDO containerDO);
  Crate createAuthenticationCrate(String label, AuthenticationMode mode, boolean revocation);

  AbstractCrateStorage getStorage(ActivityDTO activityPayload);
  AbstractCrateStorage getUpdatableStorage(ActivityDTO activity);
  AbstractCrateStorage getUpdatableStorage(PayloadDTO payload);
  Crate<ManifestDescriptionCM> createManifestDescriptionCrate(String label, String name, String id, AvailabilityTypeEnum availability);
  Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, FieldDTO... fields);
  Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, List<FieldDTO> fields);
  Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, List<FieldDTO> fields, AvailabilityTypeEnum availability);
  Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, AvailabilityTypeEnum availability, FieldDTO... fields);
  Crate<StandardConfigurationControlsCM> createStandardConfigurationControlsCrate(String label, ControlDefinitionDTO... controls);
  Crate createStandardEventReportCrate(String label, EventReportCM eventReport);

  Crate createStandardTableDataCrate(String label, boolean firstRowHeaders, TableRowDTO... table);
  Crate createPayloadDataCrate(String payloadDataObjectType, String crateLabel, StandardTableDataCM tableDataMS);
  Crate createOperationalStatusCrate(String label, OperationalStateCM eventReport);
  StandardPayloadDataCM transformStandardTableDataToStandardPayloadData(String curObjectType, StandardTableDataCM tableDataMS);
  String getFieldByKey(CrateStorageDTO curCrateStorage, String findKey);
  <T extends Manifest> T getByManifest(ContainerExecutionContext containerExecutionContext);
  OperationalStateCM getOperationalState(ContainerExecutionContext containerExecutionContext);
  Iterable<FieldDTO> getFields(Iterable<Crate> crates);
  Iterable<String> getLabelsByManifestType(Iterable<Crate> crates, String manifestType);
  FieldDescriptionsCM mergeContentFields(List<Crate<FieldDescriptionsCM>> curCrates);
  <T extends Class> T getContentType(String crate);
}
