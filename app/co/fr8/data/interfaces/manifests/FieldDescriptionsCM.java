package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.interfaces.dto.FieldDTO;

import java.util.List;

/**
 * TODO: Implement
 */
public class FieldDescriptionsCM extends Manifest {

  private List<FieldDTO> fields;

  public FieldDescriptionsCM() {
    super(MT.FieldDescription);
  }
/**
  public FieldDescriptionsCM(List<FieldDTO> fields) {
    this.fields = fields;
  }

  public FieldDescriptionsCM(params FieldDTO[] fields) : this()
  {
    Fields.AddRange(fields);
  }

  public string this[string key]
  {
    get { return Fields?.FirstOrDefault(x => x.Key == key)?.Value; }
    set
    {
      var field = Fields.FirstOrDefault();
      if (field != null)
      {
        field.Value = value;
      }
    }
  }
*/
}
