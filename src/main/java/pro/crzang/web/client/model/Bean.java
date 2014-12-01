package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONValue;

/**
 * Вершина иерархии всех бинов.
 *
 * @author Alexey
 */
public interface Bean {

  AbstractDataSource setName(String name);

  AbstractDataSource setValue(JSONValue jsonValue);

  String toJson();
  // Marker
}
