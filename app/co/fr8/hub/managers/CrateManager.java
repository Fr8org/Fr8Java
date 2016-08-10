package co.fr8.hub.managers;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.AbstractCrateStorage;
import co.fr8.data.crates.Crate;
import co.fr8.data.crates.CrateStorage;
import co.fr8.data.entities.ContainerDO;
import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.*;
import co.fr8.data.states.AvailabilityTypeEnum;
import co.fr8.util.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * TODO: Implement
 */
public class CrateManager implements ICrateManager {

  @Override
  public AbstractCrateStorage updateStorage(AbstractCrateStorage storageAccessExpression) {
    return null;
  }

  @Override
  public CrateDTO toDto(Crate crate) {
    return null;
  }

  @Override
  public CrateStorageDTO toDto(AbstractCrateStorage storage) {
    return null;
  }

  @Override
  public AbstractCrateStorage fromDto(CrateStorageDTO storageDto) {
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
  public String crateStorageAsStr(AbstractCrateStorage storage) {
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

  public static Crate createStandardEventSubscriptionsCrate(String label, String manufacturer, String[] subscriptions) {
    EventSubscriptionCM eventSub = new EventSubscriptionCM(Arrays.asList(subscriptions), manufacturer);
    Crate<EventSubscriptionCM> ret =
        new Crate<>(MT.StandardEventSubscription, eventSub);
    ret.setLabel(label);
    ret.setAvailability(AvailabilityTypeEnum.NotSet);

    return ret;

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
  public <T extends Manifest> T getByManifest(ContainerExecutionContext containerExecutionContext) {
    return null;
  }

  @Override
  public OperationalStateCM getOperationalState(ContainerExecutionContext containerExecutionContext) {
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

  @Override
  public AbstractCrateStorage updateStorage(CrateStorageDTO storageAccessExpression) {
    AbstractCrateStorage storage =fromDto(storageAccessExpression);
    return storage;
  }

  @Override
  public AbstractCrateStorage updateStorage(String storageAccessExpression) {
    return null;
  }

  public AbstractCrateStorage getUpdatableStorage(ActivityDTO activity) {
    if (activity == null) throw new IllegalArgumentException("action");
    return updateStorage(activity.getCrateStorage());
  }

  public AbstractCrateStorage getUpdatableStorage(PayloadDTO payload) {
    if (payload == null) throw new IllegalArgumentException("payload");
    return updateStorage(payload.getCrateStorage());
  }

  public AbstractCrateStorage getStorage(String crateStorageRaw) {
    if (StringUtils.isBlank(crateStorageRaw)) {
      return new CrateStorage();
    }

    return fromDto(JsonUtils.writeStringToCrateStorageDTO(crateStorageRaw));
  }

  public AbstractCrateStorage getStorage(ActivityDTO activity) {
    return fromDto(activity.getCrateStorage());
  }

  public AbstractCrateStorage getStorage(PayloadDTO payload) {
    return fromDto(payload.getCrateStorage());
  }

  public boolean isStorageEmpty(ActivityDTO activity) {
    return isEmptyStorage(activity.getCrateStorage());
  }

  /// <summary>
  /// Lets you update activity UI control values without need to unpack and repack control crates
  /// </summary>
  public ActivityDTO updateControls(ActivityDTO activity, StandardConfigurationControlsCM action) {
    return updateControls(activity, action);
  }

  private Object updateControls(Object activity, StandardConfigurationControlsCM action) {
    if (activity == null) {
      throw new IllegalArgumentException("The activity object is null");
    }
    if (action == null) {
      throw new IllegalArgumentException("The action object is null");
    }
    ICrateManager crateManager = new CrateManager();

    ActivityDTO activityPayload = null;
    if (activity instanceof ActivityDTO) {
      activityPayload = (ActivityDTO) activity;
      IUpdatableCrateStorage storage = crateManager.getUpdatableStorage(activityPayload);

        Crate controlsCrate = storage.getFirstCrate();

        StandardConfigurationControlsCM activityUi = action.cloneProperties(controlsCrate.getContent());
//        action(activityUi);
        storage.replaceByLabel(Crate.fromContent(controlsCrate.getLabel(),
            new StandardConfigurationControlsCM(activityUi.getControls()), controlsCrate.getAvailability()));

    }
    return activityPayload;
  }

  /// <summary>
  /// Returns a copy of AbstractActivityUI for the given activity
  /// </summary>
/*
  public static StandardConfigurationControlsCM getReadonlyActivityUi(ActivityPayload activity) {
    return getReadonlyActivityUi(activity);
  }

  private static StandardConfigurationControlsCM getReadonlyActivityUi(Object activity) {
    if (activity == null) {
      throw new IllegalArgumentException("Object activity is null");
    }
    ICrateManager crateManager = new CrateManager();

    ActivityPayload activityDto = null;

    if (activity instanceof ActivityPayload) {
      activityDto = (ActivityPayload) activity;
      AbstractCrateStorage storage = crateManager.getStorage(activityDto);
      return new StandardConfigurationControlsCM().cloneProperties(storage.getFirstCrateOrDefault().getContent());
    }

    return null;
  }
*/

}
