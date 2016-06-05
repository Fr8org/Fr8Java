package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.ActivityResponseDTO;
import co.fr8.terminal.base.ActivityErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement
 */
public class OperationalStateCM extends Manifest {

  private ActivityCallStack callStack = new ActivityCallStack();
  private List<HistoryElement> history = new ArrayList<>();
  private ActivityErrorCode currentActivityErrorCode;
  private String currentActivityErrorMessage;
  private String currentClientActivityName;
  private StackLocalData bypassData;
  private ActivityResponseDTO currentActivityResponse;

  public OperationalStateCM() {
    super(MT.OperationalStatus);
  }

  public ActivityCallStack getCallStack() {
    return callStack;
  }

  public void setCallStack(ActivityCallStack callStack) {
    this.callStack = callStack;
  }

  public List<HistoryElement> getHistory() {
    return history;
  }

  public void setHistory(List<HistoryElement> history) {
    this.history = history;
  }

  public ActivityErrorCode getCurrentActivityErrorCode() {
    return currentActivityErrorCode;
  }

  public void setCurrentActivityErrorCode(ActivityErrorCode currentActivityErrorCode) {
    this.currentActivityErrorCode = currentActivityErrorCode;
  }

  public String getCurrentActivityErrorMessage() {
    return currentActivityErrorMessage;
  }

  public void setCurrentActivityErrorMessage(String currentActivityErrorMessage) {
    this.currentActivityErrorMessage = currentActivityErrorMessage;
  }

  public String getCurrentClientActivityName() {
    return currentClientActivityName;
  }

  public void setCurrentClientActivityName(String currentClientActivityName) {
    this.currentClientActivityName = currentClientActivityName;
  }

  public StackLocalData getBypassData() {
    return bypassData;
  }

  public void setBypassData(StackLocalData bypassData) {
    this.bypassData = bypassData;
  }

  public ActivityResponseDTO getCurrentActivityResponse() {
    return currentActivityResponse;
  }

  public void setCurrentActivityResponse(ActivityResponseDTO currentActivityResponse) {
    this.currentActivityResponse = currentActivityResponse;
  }
}
