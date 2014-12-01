package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Класс, описываюший строку.
 *
 * @author Alexey
 */
public class StringDataSource extends AbstractDataSource {

  public StringDataSource(String name, String value) {
    super(DataSourceType.STRING, name, value);
  }

  public StringDataSource(String name, JSONString value) {
    super(DataSourceType.STRING, name, value);
  }

  @Override
  public String asString() {
    if (value.isNull() != null)
      return null;
    if (value.isString() != null) {
      return value.isString().stringValue();
    } else
      throw new DataSourceStructureException("Неверное обращение: объект" + getDsName()
          + " не является строкой");
  }

  @Override
  protected JSONValue init(Object obj) {
    return new JSONString((String) obj);
  }

  @Override
  public String toJson() {
    return value.isString().toString();
  }

  @Override
  /**
   * Клонирование объекта.
   * @return клонированный объект.
   */
  public StringDataSource cloneBean() {
    return new StringDataSource(getDsName(), asString());
  }

  /*public static class StringDataSourceFactory implements BeanFactory {

    @Override
    public AbstractDataSource create(String name, JSONValue value) {
      if (value == null || value.isNull() != null)
        return NullDataSource.getInstance();
      if (value.isString() == null)
        throw new DataSourceStructureException("Невозможно создать оболочку для строки. Объект:"
            + value.toString() + " не  является строкой");
      return new StringDataSource(name, value.isString());
    }
  }*/

}
