package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONValue;

/**
 * Класс, описывающий логическую переменную.
 *
 * @author Alexey
 */
public class BooleanDataSource extends AbstractDataSource {

  public BooleanDataSource(String name, Boolean value) {
    super(DataSourceType.BOOLEAN, name, value);
  }

  public BooleanDataSource(String name, JSONBoolean value) {
    super(DataSourceType.BOOLEAN, name, value);
  }

  @Override
  public Boolean asBoolean() {
    if (value.isNull() != null)
      return null;
    if (value.isBoolean() != null) {
      return value.isBoolean().booleanValue();
    }
    throw new DataSourceStructureException("Неверное обращение: объект" + getDsName()
        + " не является логической переменной");
  }

  @Override
  protected JSONValue init(Object obj) {
    return JSONBoolean.getInstance((Boolean) obj);
  }

  @Override
  public String toJson() {
    return value.isBoolean().toString();
  }

  @Override
  /**
   * Клонирование объекта.
   * @return клонированный объект.
   */
  public BooleanDataSource cloneBean() {
    return new BooleanDataSource(getDsName(), asBoolean());
  }

  /*public static class BooleanDataSourceFactory implements BeanFactory {
    @Override
    public AbstractDataSource create(String name, JSONValue value) {
      if (value == null || value.isNull() != null)
        return NullDataSource.getInstance();
      if (value.isBoolean() == null)
        throw new DataSourceStructureException(
            "Невозможно создать оболочку для логического типа. Объект:" + value.toString()
                + " не  является логической пемеменной");
      return new BooleanDataSource(name, value.isBoolean());
    }
  }*/
}
