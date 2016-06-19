package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.FieldDTO;
import co.fr8.data.interfaces.dto.PayloadObjectDTO;
import co.fr8.terminal.TerminalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement
 */
public class StandardPayloadDataCM extends Manifest {

  private String name;
  private List<PayloadObjectDTO> payloadObjects = new ArrayList<>();
  private String objectType;

  public StandardPayloadDataCM() {
    super(MT.StandardPayloadData);
    this.objectType = TerminalConstants.UNSPECIFIED_TYPE;
  }


  public StandardPayloadDataCM(List<FieldDTO> fields) {
    this();
    payloadObjects.add(new PayloadObjectDTO(fields));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<PayloadObjectDTO> getPayloadObjects() {
    return payloadObjects;
  }

  public void setPayloadObjects(List<PayloadObjectDTO> payloadObjects) {
    this.payloadObjects = payloadObjects;
  }

  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }
}
