package co.fr8.terminal.infrastructure;

import co.fr8.data.crates.Crate;
import co.fr8.data.crates.helpers.EventReportCrateFactory;
import co.fr8.data.crates.helpers.LoggingDataCrateFactory;
import co.fr8.data.interfaces.dto.CrateDTO;
import co.fr8.data.interfaces.manifests.LoggingDataCM;
import co.fr8.hub.managers.ICrateManager;
import co.fr8.networking.IRestfulServiceClient;
import co.fr8.util.net.HttpUtils;
import co.fr8.util.net.IHttpUtils;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * TODO: Implement
 */
abstract public class BaseTerminalEvent {
  private final EventReportCrateFactory eventReportCrateFactory;
  private final LoggingDataCrateFactory loggingDataCrateFactory;
  private ICrateManager crateManager;

  public abstract Crate eventParser(String externalEventPayload);

  private String eventWebServerUrl = StringUtils.EMPTY;
  private boolean eventsDisabled = false;
  private static final IHttpUtils httpUtils = new HttpUtils();

  public BaseTerminalEvent() {
    //Missing CoreWebServerUrl most likely means that terminal is running in an integration test environment.
    //Disable event distribution in such case since we don't need it (nor do we have a Hub instance
    //to send events too).
    // TODO: Understand configuration manager... probably need a properties file
//    eventWebServerUrl = CloudConfigurationManager.GetSetting("CoreWebServerUrl");
    if (StringUtils.isBlank(eventWebServerUrl)) {
      eventsDisabled = true;
    } else {
      eventWebServerUrl += "api/v1/event/gen1_event";
    }

    eventReportCrateFactory = new EventReportCrateFactory();
    loggingDataCrateFactory = new LoggingDataCrateFactory();

    //TODO: ObjectFactory is dependency injection
//    crateManager = ObjectFactory.GetInstance<CrateManager>();
  }


  public String sendEventOrIncidentReport(String terminalName, String eventType)
  {
    if (eventsDisabled) return StringUtils.EMPTY;
    //SF DEBUG -- Skip this event call for local testing
    //return;
    //make Post call
    LoggingDataCM loggingDataCM = new LoggingDataCM();
    loggingDataCM.setObjectId(terminalName);
    loggingDataCM.setData("service_start_up");
    loggingDataCM.setPrimaryCategory("Operations");
    loggingDataCM.setSecondaryCategory("System Startup");
    loggingDataCM.setActivity("system startup");

//    Crate loggingDataCrate = loggingDataCrateFactory.crate(loggingDataCM);
    //TODO inpect this
    //I am not sure what to supply for parameters eventName and palletId, so i passed terminalName and eventType
    return httpUtils.post();
//        new Uri(eventWebServerUrl, UriKind.Absolute),
//        crateManager.toDto(eventReportCrateFactory.Create(eventType, terminalName, loggingDataCrate)));
  }

  public String sendEventReport(String terminalName, String message)
  {
    //SF DEBUG -- Skip this event call for local testing
    //return;
    if (eventsDisabled) return StringUtils.EMPTY;
    //make Post call
//    IRestfulServiceClient restClient = prepareRestClient();
    LoggingDataCM loggingDataCM = new LoggingDataCM();
    loggingDataCM.setObjectId(terminalName);
    loggingDataCM.setData(message);
    loggingDataCM.setPrimaryCategory("Operations");
    loggingDataCM.setSecondaryCategory("System Startup");
    loggingDataCM.setActivity("system startup");

    Crate loggingDataCrate = loggingDataCrateFactory.create(loggingDataCM);
    //TODO inpect this
    //I am not sure what to supply for parameters eventName and palletId, so i passed terminalName and eventType
    return httpUtils.post();
//    new Uri(eventWebServerUrl, UriKind.Absolute),
//        crateManager.toDto(eventReportCrateFactory.Create("Terminal Event", terminalName, loggingDataCrate)));
  }

  /// <summary>
  /// Sends "Terminal Incident" to report terminal Error
  /// </summary>
  /// <param name="terminalName">Name of the terminal where the exception occured</param>
  /// <param name="exceptionMessage">Exception Message</param>
  /// <param name="exceptionName">Name of the occured exception</param>
  /// <param name="fr8UserId">Id of the current user. It should be obtained from AuthorizationToken</param>
  /// <returns>Response from the fr8 Event Controller</returns>
  public String sendTerminalErrorIncident(String terminalName, String exceptionMessage, String exceptionName, String fr8UserId)
  {
    if (eventsDisabled) return StringUtils.EMPTY;

    //prepare the REST client to make the POST to fr8's Event Controller
//    IRestfulServiceClient restClient = prepareRestClient();


    //create event logging data with required information

    LoggingDataCM loggingDataCM = new LoggingDataCM();
    loggingDataCM.setFr8UserId(fr8UserId);
    loggingDataCM.setObjectId(terminalName);
    loggingDataCM.setData(exceptionMessage);
    loggingDataCM.setPrimaryCategory("TerminalError");
    loggingDataCM.setSecondaryCategory(exceptionMessage);
    loggingDataCM.setActivity("Occurred");

    Crate loggingDataCrate = loggingDataCrateFactory.create(loggingDataCM);

    //return the response from the fr8's Event Controller
    return httpUtils.post();
//    new Uri(eventWebServerUrl, UriKind.Absolute),
//        crateManager.toDto(eventReportCrateFactory.Create("Terminal Incident", terminalName, loggingDataCrate)));
  }

  /// <summary>
  /// Initializes a new rest call
  /// </summary>
  /// <returns>The protected access specifier is only for Unit Test purpose.
  /// In all other scenarios it should be teated as private</returns>
  protected IHttpUtils prepareRestClient() {
    // TODO: ObjectFactory is dependency injection
//    return ObjectFactory.GetInstance<IRestfulServiceClient>();
    return httpUtils;
  }

  /// <summary>
  /// Processing the external event pay load received
  /// </summary>
  /// <param name="curExternalEventPayload">event pay load received</param>
  /// <param name="parser">delegate method</param>
  public void process(String curExternalEventPayload) {
//    IRestfulServiceClient client = ObjectFactory.GetInstance<IRestfulServiceClient>();

    Logger.debug("placeholder code: does nothing");
    String fr8EventUrl = StringUtils.EMPTY; //CloudConfigurationManager.GetSetting("CoreWebServerUrl") + "api/v1/event/processevents";
    CrateDTO eventReportCrateDTO = crateManager.toDto(eventParser(curExternalEventPayload));

    if (eventReportCrateDTO != null) {
      try {
        URI url = new URI(fr8EventUrl);

        //TODO are we expecting a timeout??
        httpUtils.post(/*url, eventReportCrateDTO*/);
      } catch (URISyntaxException e) {
        Logger.error("Unable to send request to " + fr8EventUrl, e);
        //Timeout
//        throw new TimeoutException(
//            String.Format("Timeout while making HTTP request.  \r\nURL: {0},   \r\nMethod: {1}",
//                url.ToString(),
//                HttpMethod.Post.Method));
      }
    }
}

}
