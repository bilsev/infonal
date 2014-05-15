package org.kodmanyagha.infonal.data.driver;

import java.util.HashMap;
import java.util.Map;

public class DBDataObject {
  private Map<String, Object> values;

  public DBDataObject() {
    this(new HashMap<String, Object>());
  }

  public DBDataObject(Map<String, Object> map) {
    this.values = map;
  }

  public Map<String, Object> getValues() {
    return values;
  }

  public void setValues(Map<String, Object> values) {
    this.values = values;
  }

}
