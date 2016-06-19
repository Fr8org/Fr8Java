package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.data.states.ActivityTypeEnum;
import co.fr8.hub.managers.CrateManager;
import co.fr8.hub.managers.ICrateManager;
import co.fr8.play.ApplicationConstants;
import co.fr8.terminal.TerminalConstants;
import co.fr8.terminal.base.exception.ActivityNotFoundException;
import co.fr8.terminal.base.ui.AbstractActivityUI;
import co.fr8.terminal.infrastructure.BaseTerminalEvent;
import co.fr8.terminal.infrastructure.DefaultHubCommunicator;
import co.fr8.terminal.infrastructure.IHubCommunicator;
import co.fr8.util.CollectionUtils;
import co.fr8.util.DateUtils;
import co.fr8.util.Fr8StringUtils;
import co.fr8.util.logging.Logger;
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
abstract public class AbstractTerminalService<T extends BaseTerminalEvent> {

  private final T baseTerminalEvent;
  private final String name;

  private Map<String, AbstractTerminalActivity<? extends AbstractActivityUI>> activityRegistrations = new HashMap<>();
  private IHubCommunicator hubCommunicator = new DefaultHubCommunicator();
  private ICrateManager crateManager = new CrateManager();

  /**
   * Two parameter constructor
   *
   * @param event Sets the event associated with the terminal
   */
  public AbstractTerminalService(T event, String name) {
    this.baseTerminalEvent = event;
    this.name = name;
    registerActivities();
  }

  /**
   * Configures the IHubCommunicator for the terminal and sets it to the activity
   *
   * @param terminalActivity the activity that will use the hubCommunicator
   */
  private void configureHubCommunicator(AbstractTerminalActivity terminalActivity) {

    if (terminalActivity != null) {
      hubCommunicator.configure(this.name);
      terminalActivity.getActivityContext().setHubCommunicator(hubCommunicator);
    }

    // TODO: Throw exception?
  }

  /**
   * Helper method which logs information about the request
   *
   * @param actionPath the type of request
   * @param terminalName the name of the terminal
   * @param activityId the ID of the activity
   * @param type the type action
   */
  private void logWhenRequest(String actionPath,String terminalName, String activityId, String type) {
    Logger.info("[" + terminalName + "] " + type + " /" + actionPath + " call at " +
        DateUtils.getCurrentShortDate() + " for ActivityID " + activityId + ", " + terminalName);
  }

  /**
   * Converts a Fr8DataDTO object to an ActivityContext object to use in when processing the activity
   *
   * @param curDataDTO the Fr8DataDTO object to convert
   * @param actionName the ActionNameEnum representing the type of request being made
   * @return
   */
  private ActivityContext extractActivityContextFromData(Fr8DataDTO curDataDTO, ActionNameEnum actionName) {
    if (curDataDTO.getActivityPayload() == null) {
      Logger.error("curDataDTO activity DTO is null for " + curDataDTO);
      throw new IllegalArgumentException("ActivityPayload for " + curDataDTO + " is null");
    }
    ActivityDTO curActivityPayload = curDataDTO.getActivityPayload();
    if (curDataDTO.getActivityPayload().getActivityTemplate() == null)
      throw new IllegalArgumentException("ActivityTemplate is null " + curDataDTO.getActivityPayload().getClass());

      logWhenRequest(actionName.getLowerValue(), curActivityPayload.getActivityTemplate().getTerminal().getName(),
          curActivityPayload.getId().toString(), "received");

      Logger.debug("Checking curActionPath " + actionName + " as enum: " +
          actionName);

      return createActivityContextFromFr8Data(curDataDTO);
  }

  private AbstractTerminalActivity<? extends AbstractActivityUI> extractActivity(Fr8DataDTO fr8DataDTO)
      throws ActivityNotFoundException {

    String activityTemplateName = fr8DataDTO.getActivityPayload().getActivityTemplate().getName();

    Logger.debug("Looking for activity template with name: " + activityTemplateName);

    AbstractTerminalActivity<? extends AbstractActivityUI> terminalActivity =
        activityRegistrations.get(activityTemplateName);


    if (terminalActivity == null) {
      Logger.error("No terminalActivity found for " + activityTemplateName);
      throw new ActivityNotFoundException("No terminalActivity found for " + activityTemplateName);
    }

    return terminalActivity;
  }

  /**
   * Main work method for a terminal service. This method is called from a controller
   * and passes the request payload as a Fr8DataDTO object
   *
   * @param actionName ActionNameEnum equivalent which matches the operation request
   *                   made from the Hub
   * @param curDataDTO The Fr8DataDTO object sent in the request payload from
   *                   the Hub
   * @return an ActivityDTO with crates that contain the result of the operation
   */
  public ActivityDTO handleFr8Request(ActionNameEnum actionName, Fr8DataDTO curDataDTO,
                                      Map<String, String[]> params) {
    ActivityDTO curActivityPayload = curDataDTO.getActivityPayload();
    ActivityContext activityContext = extractActivityContextFromData(curDataDTO, actionName);

    try {
      AbstractTerminalActivity terminalActivity = extractActivity(curDataDTO);
      //Set Current user of action
      configureHubCommunicator(terminalActivity);
      switch (actionName) {
        case CONFIGURE: {
          Logger.debug("In configure statement");
          terminalActivity.configure(activityContext);

          ActivityPayload resultActivityPayload = terminalActivity.getActivityPayload();

          Logger.debug("Returning ActivityPayload from configure: " + resultActivityPayload);

          return new ActivityDTO(resultActivityPayload);
        }
        case RUN: {
          Logger.debug("In run statement");
          ContainerExecutionContext resultContainerExecutionContext =
              createContainerExecutionContext(curDataDTO);

          String[] scopeParams = params.get("scope");
          boolean isChildActivitiesScope = false;

          if (CollectionUtils.isNotEmpty(scopeParams)) {
            for(String scopeParam : scopeParams) {
              if ("childActivities".equalsIgnoreCase(scopeParam)) {
                isChildActivitiesScope = true;
                break;
              }
            }
          }

          if (isChildActivitiesScope) {
            Logger.debug("Executing child activity scope");
            terminalActivity.runChildActivities(activityContext,
                resultContainerExecutionContext);
          } else {
            terminalActivity.run(activityContext,
                resultContainerExecutionContext);
          }

          break;

        }
        case ACTIVATE: {

          Logger.debug("activating activity");
          terminalActivity.activate(activityContext);

          //Plan.cs run method line 518 & activate method 258

          break;

        }
        case DEACTIVATE: {
          Logger.debug("deactivating activity");
          terminalActivity.deactivate(activityContext);

          break;

        }
        case DOCUMENTATION: {
          Logger.debug("documentation request");
          terminalActivity.getDocumentation(activityContext, "placeholder");
          break;
        }
        default:
          Logger.debug("Switch statement fell through to default");

          // TODO: Add error message to terminalActivity.getActivityPayload();
      }
      return new ActivityDTO(terminalActivity.getActivityPayload());

    } catch (Exception e) {
      Logger.error("There was an exception processing the request", e);
      // TODO: Add error message to terminalActivity.getActivityPayload();
    }

    Logger.debug("HandleFr8Request returning end value");

    logWhenRequest(actionName.getLowerValue(), curActivityPayload.getActivityTemplate().getTerminal().getName(),
        curActivityPayload.getId().toString(), "responded");


    // TODO: do something with this
    return null;
  }

  /**
   * Register an activity to the terminal. This method adds an activity to
   * the activityRegistrations map using the payload name as the key
   *
   * The logic does not attempt to handle duplicate keys. If a key exists, its
   * value will be overwritten
   *
   * @param activity the activity to register
   */
  protected void registerActivity(AbstractTerminalActivity<? extends AbstractActivityUI> activity) {

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

  /** Getters & Setters **/
  public String getName() {
    return name;
  }

  public T getBaseTerminalEvent() {
    return baseTerminalEvent;
  }

  /** Abstract Methods **/

  /**
   * Abstract method definition which is meant to respond to the /terminals/discover
   * request
   *
   * @return a StandardFr8TerminalCM object to be added to a crate
   */
  public abstract StandardFr8TerminalCM discover();

  /**
   * Abstract method definition which is executed upon request to /authorization/request_url
   *
   * @return an ExternalAuthURLDTO object to be packaged in to a crate
   */
  public abstract ExternalAuthUrlDTO generateExternalAuthUrl();

  /**
   * Abstract method definition which is executed when the /authentication/token
   * route is requested
   * @param externalAuthDTO an ExternalAuthDTO object extracted from the request
   *                        JSON body
   * @return an AuthorizationToken object
   */
  public abstract AuthorizationToken authenticateToken(ExternalAuthDTO externalAuthDTO);

  /**
   * Method which requires subclasses to register objects of type
   * AbstractTerminalActivity with the terminal service
   */
  public abstract void registerActivities();

}
