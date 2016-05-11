package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;

/**
 * TODO: Implement
 */
public class LoggingDataCM extends Manifest {
  private String objectId;
  private String fr8UserId;
  private String data;
  private String primaryCategory;
  private String secondaryCategory;
  private String activity;

  public LoggingDataCM() {
    super(MT.LoggingData);
  }

  public String getObjectId() {
    return objectId;
  }

  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }

  public String getFr8UserId() {
    return fr8UserId;
  }

  public void setFr8UserId(String fr8UserId) {
    this.fr8UserId = fr8UserId;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getPrimaryCategory() {
    return primaryCategory;
  }

  public void setPrimaryCategory(String primaryCategory) {
    this.primaryCategory = primaryCategory;
  }

  public String getSecondaryCategory() {
    return secondaryCategory;
  }

  public void setSecondaryCategory(String secondaryCategory) {
    this.secondaryCategory = secondaryCategory;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }
}
