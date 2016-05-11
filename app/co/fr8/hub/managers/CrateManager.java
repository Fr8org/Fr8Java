package co.fr8.hub.managers;

import co.fr8.data.crates.Crate;
import co.fr8.data.crates.ICrateStorage;
import co.fr8.data.entities.ContainerDO;
import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.*;
import co.fr8.data.states.AvailabilityTypeEnum;

import java.util.List;

/**
 * TODO: Implement
 */
public class CrateManager implements ICrateManager {

  @Override
  public CrateDTO toDto(Crate crate) {
    return null;
  }

  @Override
  public CrateStorageDTO toDto(ICrateStorage storage) {
    return null;
  }

  @Override
  public ICrateStorage fromDto(CrateStorageDTO storageDto) {
    return null;
  }

  @Override
  public Crate fromDto(CrateDTO crateDto) {
    return null;
  }

  @Override
  public boolean isEmptyStorage(CrateStorageDTO storageDto) {
    return false;
  }

  @Override
  public String emptyStorageAsStr() {
    return null;
  }

  @Override
  public String crateStorageAsStr(ICrateStorage storage) {
    return null;
  }

  @Override
  public void addLogMessage(String label, List<LogItemDTO> logItemList, ContainerDO containerDO) {

  }

  @Override
  public Crate createAuthenticationCrate(String label, AuthenticationMode mode, boolean revocation) {
    return null;
  }

  @Override
  public Crate<ManifestDescriptionCM> createManifestDescriptionCrate(String label, String name, String id, AvailabilityTypeEnum availability) {
    return null;
  }

  @Override
  public Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, FieldDTO... fields) {
    return null;
  }

  @Override
  public Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, List<FieldDTO> fields) {
    return null;
  }

  @Override
  public Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, List<FieldDTO> fields, AvailabilityTypeEnum availability) {
    return null;
  }

  @Override
  public Crate<FieldDescriptionsCM> createDesignTimeFieldsCrate(String label, AvailabilityTypeEnum availability, FieldDTO... fields) {
    return null;
  }

  @Override
  public Crate<StandardConfigurationControlsCM> createStandardConfigurationControlsCrate(String label, ControlDefinitionDTO... controls) {
    return null;
  }

  @Override
  public Crate createStandardEventReportCrate(String label, EventReportCM eventReport) {
    return null;
  }

  @Override
  public Crate createStandardEventSubscriptionsCrate(String label, String manufacturer, String... subscriptions) {
    return null;
  }

  @Override
  public Crate createStandardTableDataCrate(String label, boolean firstRowHeaders, TableRowDTO... table) {
    return null;
  }

  @Override
  public Crate createPayloadDataCrate(String payloadDataObjectType, String crateLabel, StandardTableDataCM tableDataMS) {
    return null;
  }

  @Override
  public Crate createOperationalStatusCrate(String label, OperationalStateCM eventReport) {
    return null;
  }

  @Override
  public StandardPayloadDataCM transformStandardTableDataToStandardPayloadData(String curObjectType, StandardTableDataCM tableDataMS) {
    return null;
  }

  @Override
  public String getFieldByKey(CrateStorageDTO curCrateStorage, String findKey) {
    return null;
  }

  @Override
  public <T extends Manifest> T getByManifest(PayloadDTO payloadDTO) {
    return null;
  }

  @Override
  public OperationalStateCM getOperationalState(PayloadDTO payloadDTO) {
    return null;
  }

  @Override
  public Iterable<FieldDTO> getFields(Iterable<Crate> crates) {
    return null;
  }

  @Override
  public Iterable<String> getLabelsByManifestType(Iterable<Crate> crates, String manifestType) {
    return null;
  }

  @Override
  public FieldDescriptionsCM mergeContentFields(List<Crate<FieldDescriptionsCM>> curCrates) {
    return null;
  }

  @Override
  public <T extends Class> T getContentType(String crate) {
    return null;
  }
}
