package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.*;
import co.fr8.data.interfaces.manifests.StandardFr8TerminalCM;
import co.fr8.terminal.infrastructure.BaseTerminalEvent;
import co.fr8.util.DateUtils;
import co.fr8.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Implement
 * from TerminalBase.BaseClasses.BaseTerminalController
 */
abstract public class AbstractTerminalService {

  private static final String TEST_SUFFIX = "_TEST";
  private final BaseTerminalEvent baseTerminalEvent;
  private Map<String, TerminalActivityFactory> activityRegistrations = new HashMap<>();
  private boolean integrationTestMode;

  public boolean isIntegrationTestMode() {
    return integrationTestMode;
  }

  public AbstractTerminalService(BaseTerminalEvent event, boolean isTestMode) {
    this.baseTerminalEvent = event;
    this.integrationTestMode = isTestMode;
  }

  public AbstractTerminalService(BaseTerminalEvent event) {
    this(event, false);
  }

  abstract public StandardFr8TerminalCM discover();
/*
  /// <summary>
  /// Reports Terminal Error incident
  /// </summary>
//  [HttpGet]
  public IHttpActionResult ReportTerminalError(String terminalName, Exception terminalError,String userId = null)
  {
    if (integrationTestMode)
      return Ok();

    String exceptionMessage = terminalError.getMessage() + "  \n\r   " + terminalError;//String.Format("{0}\r\n{1}", terminalError.Message, terminalError.StackTrace);
    try {
      return Json(baseTerminalEvent.SendTerminalErrorIncident(terminalName, exceptionMessage, terminalError.getClass().getName(),userId));
    } catch (Exception ex) {
      String errorMessage = "An error has occurred in terminal [" + terminalName + "]. " +
          exceptionMessage + " | Fr8UserId = " + userId + " \\n Additionally, an error has occurred while trying to post error details to the Hub. " +
          ex.getMessage();

      Logger.error(errorMessage, ex);

      throw ex;
    }
  }

  /// <summary>
  /// Reports start up incident
  /// </summary>
  /// <param name="terminalName">Name of the terminal which is starting up</param>
//  [HttpGet]
  public IHttpActionResult AfterStartup(String terminalName) {
    return null;

    //TODO: Commented during development only. So that app loads fast.
    //return Json(ReportStartUp(terminalName));
  }

  /// <summary>
  /// Reports start up event by making a Post request
  /// </summary>
  /// <param name="terminalName"></param>

  private Task<String> reportStartup(String terminalName) {
    if (integrationTestMode)
      return Task.FromResult<String>(String.Empty);

    return baseTerminalEvent.SendEventOrIncidentReport(terminalName, "Terminal Incident");
  }



  private void bindTestHubCommunicator(Object curObject, String explicitData) {
    TerminalActivity baseTerminalAction = (TerminalActivity) curObject;

    if (baseTerminalAction == null) {
      return;
    }

    baseTerminalAction.hubCommunicator = new TestMonitoringHubCommunicator(explicitData);
  }

  private void bindExplicitDataHubCommunicator(Object curObject, String explicitData) {
    TerminalActivity baseTerminalAction = (TerminalActivity) curObject;

    if (baseTerminalAction == null) {
      return;
    }

    baseTerminalAction.hubCommunicator = new ExplicitDataHubCommunicator(explicitData);
  }
*/
  private void setCurrentUser(TerminalActivity baseTerminalAction, String userId, String userEmail) {

    if (baseTerminalAction != null) {
      baseTerminalAction.setCurrentUser(userId, userEmail);
    }

  }

  private void configureHubCommunicator(TerminalActivity baseTerminalActivity, String terminalName) {

    if (baseTerminalActivity != null) {
      baseTerminalActivity.getHubCommunicator().configure(terminalName);
    }

  }

  /// <summary>
  /// Reports event when process an action
  /// </summary>
  /// <param name="terminalName"></param>
  private String reportEvent(String terminalName) {

    return (integrationTestMode) ? StringUtils.EMPTY : "Terminal Event placeholder string for " + terminalName;
//    return baseTerminalEvent.sendEventOrIncidentReport(terminalName, "Terminal Event");
  }

  protected void logWhenRequestReceived(String actionPath,String terminalName, String activityId) {
    Logger.info("[" + terminalName + "] received /" + actionPath + " call  at " +
        DateUtils.getCurrentShortDate() + " for ActivityID " + activityId + ", " + terminalName);
  }

  protected void logWhenRequestResponded(String actionPath,String terminalName, String activityId) {
    Logger.info("[" + terminalName + "] responded to /" + actionPath + " call  at " +
        DateUtils.getCurrentShortDate() + " for ActivityID " + activityId + ", " + terminalName);
  }

  // For /Configure and /Activate actions that accept ActionDTO
  public Object handleFr8Request(String curTerminal, String curActionPath, Fr8DataDTO curDataDTO) {
    if (curDataDTO.getActivityDTO() == null) {
      Logger.error("curDataDTO activity DTO is null " + curTerminal);
      throw new IllegalArgumentException("ActivityDTO for " + curDataDTO + " is null");
    }


    if (curDataDTO.getActivityDTO().getActivityTemplate() == null)
      throw new IllegalArgumentException("ActivityTemplate is null " + curDataDTO.getActivityDTO().getClass());

    integrationTestMode = false;

    ActivityDTO curActivityDTO = curDataDTO.getActivityDTO();
    String activityTemplateName = curActivityDTO.getActivityTemplate().getName();

    if (activityTemplateName.endsWith(TEST_SUFFIX)) {
      integrationTestMode = true;
      activityTemplateName = activityTemplateName
          .substring(0, activityTemplateName.length() - TEST_SUFFIX.length());
    }

    String curAssemblyName = curTerminal + ".Actions." + activityTemplateName + "_v" + curActivityDTO.getActivityTemplate().getVersion();

    /* Resolve the class by name*/
    Class calledType;
    TerminalActivityFactory factory = activityRegistrations.get(curTerminal);

    TerminalActivity terminalActivity = null;
    if (factory != null) {
      terminalActivity = factory.getTerminalActivity();
    }

    if (terminalActivity == null) {
      // TODO: log an error and return
    }


    /**
     * Mapper.Map<>pulls the puts the values of an object in to a map
     */
//    var curActivityDO = Mapper.Map<ActivityDO>(curActionDTO);
    //this is a comma separated String
    String curDocumentation = curActivityDTO.getDocumentation();

    AuthorizationTokenDTO curAuthTokenDTO = curActivityDTO.getAuthToken();

//    Task<ActivityDO> response;
    String currentUserId = curAuthTokenDTO.getUserId();
    String currentUserEmail = curAuthTokenDTO.getExternalAccountId();
    //Set Current user of action
    setCurrentUser(terminalActivity, currentUserId, currentUserEmail);
    configureHubCommunicator(terminalActivity, curTerminal);
    try {
      // null checking
//      curActionDTO = (curActionDTO == null) ? new ActivityDTO() : curActionDTO;
      // TODO: Determine whether it's correct to allow new objects to be created here
//      curActivityDTO.setActivityTemplate((curActivityDTO.getActivityTemplate() == null) ? new ActivityTemplateDTO() : curActivityDTO.getActivityTemplate());
//      curActivityDTO.getActivityTemplate().setTerminal(curActivityDTO.getActivityTemplate().getTerminal() == null ? new TerminalDTO() : curActivityDTO.getActivityTemplate().getTerminal());
//      curActivityDO = curActivityDO ?? new ActivityDO();

      //log when request start proceeding
      logWhenRequestReceived(curActionPath.toLowerCase(), curActivityDTO.getActivityTemplate().getTerminal().getName(), curActivityDTO.getId().toString());

      curActionPath = curActionPath.toLowerCase();
      switch (ActionPathEnum.getByLowerValue(curActionPath)) {
        case CONFIGURE: {

          ActivityDTO resultActionDTO = terminalActivity.configure(curActivityDTO, curAuthTokenDTO);

          logWhenRequestReceived(curActionPath, curActivityDTO.getActivityTemplate().getTerminal().getName(), curActivityDTO.getId().toString());

          return resultActionDTO;
        }
        case RUN:
        case EXECUTE_CHILD_ACTIVITIES: {
          onStartActivity(curTerminal, activityTemplateName, integrationTestMode);
          PayloadDTO resultPayloadDTO = terminalActivity.run(curActivityDTO, curDataDTO.getContainerId(), curAuthTokenDTO);
//          var resultPayloadDTO = await(Task < PayloadDTO >) curMethodInfo
//              .Invoke(curObject, new Object[]{curActivityDO, curDataDTO.ContainerId, curAuthTokenDO});
          onCompletedActivity(curTerminal, integrationTestMode);

          logWhenRequestResponded(curActionPath, curActivityDTO.getActivityTemplate().getTerminal().getName(),
              curActivityDTO.getId().toString());

          return resultPayloadDTO;
        }
        case INITIAL_CONFIGURATION_RESPONSE: {
//          Task<ActivityDO> resutlActionDO = (Task<ActivityDO>) curMethodInfo.Invoke(curObject, new Object[]{curActivityDO, curAuthTokenDO});
//
//          var resultICR = await
//          resutlActionDO.ContinueWith(x = > Mapper.Map < ActivityDTO > (x.Result))
//          ;
//
//          LogWhenRequestResponded(curActionPath.ToLower(), curActivityDTO.ActivityTemplate.Terminal.Name, curActivityDO.Id.ToString());
//
//          return resultICR;
        }
        case FOLLOWUP_CONFIGURATION_RESPONSE: {
//          Task<ActivityDO> resutlActionDO = (Task<ActivityDO>) curMethodInfo.Invoke(curObject, new Object[]{curActivityDO, curAuthTokenDO});
//
//          var resultFCR = await
//          resutlActionDO.ContinueWith(x = > Mapper.Map < ActivityDTO > (x.Result))
//          ;
//
//          LogWhenRequestResponded(curActionPath.ToLower(), curActivityDTO.ActivityTemplate.Terminal.Name, curActivityDO.Id.ToString());
//
//          return resultFCR;
        }
        case ACTIVATE: {
          //activate is an optional method so it may be missing
//          if (curMethodInfo == null)
//            return Mapper.Map < ActivityDTO > (curActivityDO);
//
//          Task<ActivityDO> resutlActionDO = (Task<ActivityDO>) curMethodInfo.Invoke(curObject, new Object[]{curActivityDO, curAuthTokenDO});
//
//          var resultA = await
//          resutlActionDO.ContinueWith(x = > Mapper.Map < ActivityDTO > (x.Result))
//          ;
//
//          LogWhenRequestResponded(curActionPath.ToLower(), curActivityDTO.ActivityTemplate.Terminal.Name, curActivityDO.Id.ToString());
//
//          return resultA;
        }
        case DEACTIVATE: {
          //deactivate is an optional method so it may be missing
//          if (curMethodInfo == null)
//            return Mapper.Map < ActivityDTO > (curActivityDO);
//
//          Task<ActivityDO> resutlActionDO;
//          var param = curMethodInfo.GetParameters();
//          if (param.Length == 2)
//            resutlActionDO = (Task<ActivityDO>) curMethodInfo.Invoke(curObject, new Object[]{curActivityDO, curAuthTokenDO});
//          else {
//            response = (Task<ActivityDO>) curMethodInfo.Invoke(curObject, new Object[]{curActivityDO});
//
//            var resultD = await
//            response.ContinueWith(x = > Mapper.Map < ActivityDTO > (x.Result));
//
//            LogWhenRequestResponded(curActionPath.ToLower(), curActivityDTO.ActivityTemplate.Terminal.Name, curActivityDO.Id.ToString());
//
//            return resultD;
//          }
//
//          return resutlActionDO.ContinueWith(x = > Mapper.Map < ActivityDTO > (x.Result))
//          ;
        }
        case DOCUMENTATION: {
//          if (curMethodInfo == null) {
//            return getDefaultDocumentation();
//          }
//
//          var resultDCN = await
//          HandleDocumentationRequest(curObject, curMethodInfo, curActivityDO, curDocumentation);
//
//          LogWhenRequestResponded(curActionPath.ToLower(), curActivityDTO.ActivityTemplate.Terminal.Name, curActivityDO.Id.ToString());
//
//          return resultDCN;
        }
        default:
//          response = (Task<ActivityDO>) curMethodInfo.Invoke(curObject, new Object[]{curActivityDO});
//
//          var result = await response.ContinueWith(x = > Mapper.Map < ActivityDTO > (x.Result));
//
//          return result;
      }


    } catch (Exception e) {
//      JsonSerializerSettings settings = new JsonSerializerSettings
//      {
//        PreserveReferencesHandling = PreserveReferencesHandling.Objects
//      };
//
//      //Logger.GetLogger().Error($"Exception caught while processing {curActionPath} for {this.GetType()}", e);
//      Logger.LogError($"Exception caught while processing {curActionPath} for {this.GetType()} with exception {e.Data} and stack trace {e.StackTrace} and message {e.GetFullExceptionMessage()}", curTerminal);
//      var endpoint = (curActivityDO.ActivityTemplate != null && curActivityDO.ActivityTemplate.Terminal != null && curActivityDO.ActivityTemplate.Terminal.Endpoint != null) ? curActivityDO.ActivityTemplate.Terminal.Endpoint : "<no terminal url>";
//      EventManager.TerminalInternalFailureOccurred(endpoint, JsonConvert.SerializeObject(curActivityDO, settings), e, curActivityDO.Id.ToString());
//
//      throw;
    }
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

//    return Task.Run(() =>
//        baseTerminalEvent.SendEventReport(
//            terminalName,
//            String.Format("{0} completed processing this Container at {1}.", terminalName, DateTime.Now.ToString("G"))));
  }
/*
  public HttpResponseMessage GetActivityDocumentation(String helpPath)
  {
    String htmlContent = FindDocumentation(helpPath);

    var response = new HttpResponseMessage();
    response.Content = new StringContent(htmlContent);
    response.Content.Headers.ContentType = new MediaTypeHeaderValue("text/html");

    return response;
  }

  private String FindDocumentation(String helppath)
  {
    String filename = System.Web.Hosting.HostingEnvironment.MapPath(String.Format("~\\Documentation\\{0}.html", helppath));
    String content = "";

    try
    {
      content = System.IO.File.ReadAllText(filename);
    }
    catch (Exception ex)
    {
      throw new Exception(ex.Message, ex.InnerException);
    }

    return content;
  }

  private async Task<dynamic> HandleDocumentationRequest(object classInstance,MethodInfo curMethodInfo, ActivityDO curActivityDO, String curDocumentation)
  {
    if (!curDocumentation.IsNullOrEmpty() && curDocumentation.Split(',').Contains("MainPage"))
    {
      Task<SolutionPageDTO> resultSolutionPageDTO = (Task<SolutionPageDTO>)curMethodInfo
          .Invoke(classInstance, new Object[] { curActivityDO, curDocumentation });
      return await resultSolutionPageDTO;
    }
    if (!curDocumentation.IsNullOrEmpty() && curDocumentation.Split(',').Contains("HelpMenu"))
    {
      Task<ActivityResponseDTO> resultActivityRepsonceDTO = (Task<ActivityResponseDTO>)curMethodInfo
          .Invoke(classInstance, new Object[] { curActivityDO, curDocumentation });
      return await resultActivityRepsonceDTO;
    }
    return Task.FromResult(new ActivityResponseDTO {Type = ActivityResponse.Error.ToString(), Body = "Unknown display method"});
  }

  private SolutionPageDTO getDefaultDocumentation()
  {
    var curSolutionPage = new SolutionPageDTO
    {
      Name = "No Documentation method found",
          Body = "Please add the Documentation method to the Solution class"
    };

    return curSolutionPage;
  }
  */
}
