package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.ActivityTemplateDTO;
import co.fr8.data.interfaces.dto.TerminalDTO;
import co.fr8.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Crate Manifest for the Standard Fr8 Terminal definition
 */
public class StandardFr8TerminalCM extends Manifest {

  private TerminalDTO definition;
  private List<ActivityTemplateDTO> activities;

  /**
   * Constructor
   * Calls the super method with the MT.StandardFr8Terminal enum type
   */
  public StandardFr8TerminalCM(TerminalDTO definition, List<ActivityTemplateDTO> activities) {
    super(MT.StandardFr8Terminal);
    this.definition = definition;
    this.activities = activities;
  }

  /**
   * Getter method for the definition property
   * @return a TerminalDTO object representing the terminal definition
   */
  public TerminalDTO getDefinition() {
    return definition;
  }

  /**
   * Setter method for the definition property
   * @param definition TerminalDTO object which represents the definition
   */
  public void setDefinition(TerminalDTO definition) {
    this.definition = definition;
  }

  /**
   * Getter method for the activities property
   * @return a list of ActivityTemplateDTO objects
   */
  public List<ActivityTemplateDTO> getActivities() {
    if (CollectionUtils.isEmpty(activities))
      activities = new ArrayList<>();

    return activities;
  }

  /**
   * Setter method for the activities property
   * @param activities a list of ActivityTemplateDTO objects which will be
   *                   assigned to the activites property
   */
  public void setActivities(List<ActivityTemplateDTO> activities) {
    this.activities = activities;
  }
}
