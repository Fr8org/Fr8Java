package co.fr8.terminal.base;

import co.fr8.data.interfaces.dto.ContainerExecutionContext;
import co.fr8.data.interfaces.dto.SolutionPageDTO;

/**
 * TODO: DOCUMENT
 */
public interface IActivity {

  void run(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext);
  void runChildActivities(ActivityContext activityContext, ContainerExecutionContext containerExecutionContext);
  void configure(ActivityContext activityContext);
  void activate(ActivityContext activityContext);
  void deactivate(ActivityContext activityContext);
  SolutionPageDTO getDocumentation(ActivityContext activityContext, String documentationType);
  
}
