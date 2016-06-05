package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.hub.managers.ICrateManager;
import co.fr8.play.ApplicationConstants;
import co.fr8.terminal.TerminalConstants;
import co.fr8.terminal.infrastructure.BaseTerminalEvent;
import co.fr8.terminal.infrastructure.DefaultHubCommunicator;
import co.fr8.terminal.infrastructure.IHubCommunicator;
import co.fr8.util.DateUtils;
import co.fr8.util.Fr8StringUtils;
import co.fr8.util.logging.Logger;
import github.Authentication;
import org.apache.commons.lang3.StringUtils;
import org.apache.xerces.impl.dv.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO: Implement
 * from TerminalBase.BaseClasses.BaseTerminalController
 */
abstract public class AbstractTerminalService {

  private static final String TEST_SUFFIX = "_TEST";
  private final BaseTerminalEvent baseTerminalEvent;
  private Map<String, AbstractTerminalActivity> activityRegistrations = new HashMap<>();
  private boolean integrationTestMode;
  private Authentication authentication = new Authentication();
  private IHubCommunicator hubCommunicator = new DefaultHubCommunicator();
  private ICrateManager crateManager;
  public boolean isIntegrationTestMode() {
    return integrationTestMode;
  }

  public AbstractTerminalService(BaseTerminalEvent event, boolean isTestMode) {
    this.baseTerminalEvent = event;
    this.integrationTestMode = isTestMode;
  }

  public AbstractTerminalService(BaseTerminalEvent event) {
    this(event, false);
    registerActivities();
  }

  public abstract StandardFr8TerminalCM discover();
  public abstract ExternalAuthUrlDTO generateExternalAuthUrl();
  public abstract AuthorizationToken authenticateToken(ExternalAuthDTO externalAuthDTO);
  public abstract void registerActivities();

  private void configureHubCommunicator(AbstractTerminalActivity terminalActivity, String terminalName) {

    if (terminalActivity != null) {
      hubCommunicator.configure(terminalName);
      terminalActivity.getActivityContext().setHubCommunicator(hubCommunicator);
    }

    // TODO: Throw exception?
  }

  private String reportEvent(String terminalName) {

    return (integrationTestMode) ? StringUtils.EMPTY : "Terminal Event placeholder string for " + terminalName;
//    return baseTerminalEvent.sendEventOrIncidentReport(terminalName, "Terminal Event");
  }

  protected void logWhenRequestReceived(String actionPath,String terminalName, String activityId) {
    Logger.info("[" + terminalName + "] received /" + actionPath + " call at " +
        DateUtils.getCurrentShortDate() + " for ActivityID " + activityId + ", " + terminalName);
  }

  protected void logWhenRequestResponded(String actionPath,String terminalName, String activityId) {
    Logger.info("[" + terminalName + "] responded to /" + actionPath + " call  at " +
        DateUtils.getCurrentShortDate() + " for ActivityID " + activityId + ", " + terminalName);
  }

  // For /Configure and /Activate actions that accept ActionDTO
  public Object handleFr8Request(String curTerminal, String curActionPath, Fr8DataDTO curDataDTO) {

    if (curDataDTO.getActivityPayload() == null) {
      Logger.error("curDataDTO activity DTO is null " + curTerminal);
      throw new IllegalArgumentException("ActivityPayload for " + curDataDTO + " is null");
    }

    if (curDataDTO.getActivityPayload().getActivityTemplate() == null)
      throw new IllegalArgumentException("ActivityTemplate is null " + curDataDTO.getActivityPayload().getClass());

    integrationTestMode = false;

    ActivityDTO curActivityPayload = curDataDTO.getActivityPayload();
    String activityTemplateName = curActivityPayload.getActivityTemplate().getName();

    if (activityTemplateName.endsWith(TEST_SUFFIX)) {
      integrationTestMode = true;
      activityTemplateName = activityTemplateName
          .substring(0, activityTemplateName.length() - TEST_SUFFIX.length());
    }

//    TerminalActivityFactory factory = activityRegistrations.get(activityTemplateName);
    Logger.debug("Looking for activity template with name: " + activityTemplateName);
    AbstractTerminalActivity terminalActivity = activityRegistrations.get(activityTemplateName);
//    if (factory != null) {
//      terminalActivity = factory.getTerminalActivity();
//    } else {
//      // TODO: Fix this
//      activityRegistrations.put(activityTemplateName, null);
//    }

    if (terminalActivity == null) {
      Logger.error("No terminalActivity found for " + activityTemplateName);
      return "Invalid request: no terminal activity found for " + activityTemplateName;
    }


    //Set Current user of action
    configureHubCommunicator(terminalActivity, curTerminal);
    try {

      logWhenRequestReceived(curActionPath.toLowerCase(),
          curActivityPayload.getActivityTemplate().getTerminal().getName(), curActivityPayload.getId().toString());

      curActionPath = curActionPath.toLowerCase();
      Logger.debug("Checking curActionPath " + curActionPath + " as enum: " +
          ActionPathEnum.getByLowerValue(curActionPath));

      ActivityContext activityContext =
          createActivityContextFromFr8Data(curDataDTO);

      switch (ActionPathEnum.getByLowerValue(curActionPath)) {
        case CONFIGURE: {
          Logger.debug("In configure statement");
          terminalActivity.configure(activityContext);

          ActivityPayload resultActivityPayload = terminalActivity.getActivityPayload();

          Logger.debug("Returning ActivityPayload from configure: " + resultActivityPayload);

          return createActivityDTOFromPayload(resultActivityPayload);
        }
        case RUN: {

        }
        case EXECUTE_CHILD_ACTIVITIES: {
          onStartActivity(curTerminal, activityTemplateName, integrationTestMode);
          ContainerExecutionContext resultContainerExecutionContext =
              createContainerExecutionContext(curDataDTO);
          terminalActivity.run(activityContext, resultContainerExecutionContext);

          onCompletedActivity(curTerminal, integrationTestMode);

          logWhenRequestResponded(curActionPath, curActivityPayload.getActivityTemplate().getTerminal().getName(),
              curActivityPayload.getId().toString());

          return resultContainerExecutionContext;
        }
        case INITIAL_CONFIGURATION_RESPONSE: {

        }
        case FOLLOWUP_CONFIGURATION_RESPONSE: {

        }
        case ACTIVATE: {

        }
        case DEACTIVATE: {

        }
        case DOCUMENTATION: {

        }
        default:
          Logger.debug("Switch statement fell through to default");
      }


    } catch (Exception e) {
      Logger.error("There was an exception processing the request", e);
    }

    Logger.debug("HandleFr8Request returning end value");

    return "placeholder for handlefr8Request method";
  }

  private void onStartActivity(String terminalName, String actionName, boolean isTestActivityTemplate) {
    if (isTestActivityTemplate)
      return;

    baseTerminalEvent.sendEventReport(
        terminalName,
        terminalName + " began processing this Container at " + DateUtils.getCurrentShortDate() +
            ". Sending to Action " +  actionName);
  }

  // TODO: find the correct return type here
  private void onCompletedActivity(String terminalName, boolean isTestActivityTemplate) {
    if (isTestActivityTemplate)
      return;

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        baseTerminalEvent.sendEventReport(terminalName, terminalName + "completed processing this container at " +
            DateUtils.getCurrentShortDate());
      }};

    new Thread(runnable).start();

  }

  /**
   * Register an activity to the terminal
   * @param activity the activity to register
   */
  protected void registerActivity(AbstractTerminalActivity activity) {
    // TODO: Handle the case where an activity already exists
    Logger.debug("Registering activity " + activity.getActivityPayload().getName());
    activityRegistrations.put(activity.getActivityPayload().getName(), activity);
  }

  /**
   * Helper method which creates a ContainerExecutionContext object from an
   * Fr8DataDTO object
   * @param dataDTO the Fr8DataDTO object to use to instantiate the
   *                ContainerExecutionContext object
   * @return a ContainerExecutionContext object
   */
  private ContainerExecutionContext createContainerExecutionContext(Fr8DataDTO dataDTO) {
    PayloadDTO payload =
        hubCommunicator.getPayload(
            (dataDTO.getContainerId() == null) ? UUID.randomUUID() : dataDTO.getContainerId());

    if (payload != null) {
      return new ContainerExecutionContext(payload.getContainerId(), crateManager.getUpdatableStorage(payload));
    }

    return null;
  }

  /**
   * Helper method that creates an ActivityContext given a Fr8DataDTO object
   * @param fr8Data the Fr8DataDTO object to use to instantiate the
   *                ActivityContext object
   * @return an ActivityContext object
   */
  private ActivityContext createActivityContextFromFr8Data(Fr8DataDTO fr8Data) {
    ActivityContext ret = new ActivityContext();

    if (fr8Data != null) {
      ret.setActivityPayload(new ActivityPayload(fr8Data.getActivityPayload()));
      ret.setUserId(fr8Data.getActivityPayload().getAuthToken().getUserId());
      ret.setAuthorizationToken(fr8Data.getActivityPayload().getAuthToken());
    }

    Logger.debug("Created activity context for " + fr8Data);

    return ret;
  }

  private ActivityDTO createActivityDTOFromPayload(ActivityPayload payload) {
    return new ActivityDTO(payload);
  }


  /**
   * Generates an HMAC header that the Hub can use to verify the identity
   * of the terminal
   * @param currentUserId the ID of the user account on the Hub for which the request
   *                      is being made
   * @return a base64 encoded String
   */
  public String generateHMACHeader(String currentUserId) {
    String headerString = StringUtils.EMPTY;
    String endpoint = ApplicationConstants.TERMINAL_HOST;
    String terminalSecret = ApplicationConstants.TERMINAL_SECRET;
    String terminalId = ApplicationConstants.TERMINAL_ID;
    String now = String.valueOf(Calendar.getInstance().getTimeInMillis());
    String nonce = UUID.randomUUID().toString();

    try {

      String url = URLEncoder.encode(endpoint.toLowerCase(), StandardCharsets.UTF_8.name());
      //Formulate the keys used in plain format as a concatenated string.
      String authenticationKeyString =
          terminalId + url + now + nonce + Fr8StringUtils.base64MD5String(StringUtils.EMPTY) + currentUserId;


      byte[] secretKeyBase64ByteArray =
          terminalSecret.getBytes(StandardCharsets.US_ASCII);//Convert.FromBase64String(terminalSecret);


      Mac sha512Hmac = Mac.getInstance(TerminalConstants.HMAC_SHA_512);

      SecretKeySpec secretKey =
          new SecretKeySpec(secretKeyBase64ByteArray, TerminalConstants.HMAC_SHA_512 );

      sha512Hmac.init(secretKey);

      byte[] mac_data = sha512Hmac.doFinal(authenticationKeyString.getBytes(StandardCharsets.UTF_8));

      headerString = Base64.encode(mac_data);

    }catch(NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e){
      Logger.error("Exception generating sha512HMAC", e);
    }

    Logger.debug("Returning headerString: " + headerString);

    return "hmac " + headerString;
  }

}
