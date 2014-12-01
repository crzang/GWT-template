package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONValue;

/**
 * Класс, описывающий NULL значение.
 *
 * @author Alexey
 */
public class NullDataSource extends AbstractDataSource {

  private static final String NULL_STRING_VALUE = "null";

  protected NullDataSource(String dsName) {
    super(null, dsName, null);
  }

  /**
   * Получение экземпляра.
   *
   * @return NullDataSource
   */
  public static NullDataSource getInstance() {
    return new NullDataSource("");
  }

  @Override
  public Integer asInteger() {
    return 0;
  }

  @Override
  public String asString() {
    return null;
  }

  ;

  @Override
  public Boolean asBoolean() {
    return false;
  }

  @Override
  public Double asDouble() {
    return new Double(0);
  }

  ;

  @Override
  public ListDataSource asList() {
    return null;
  }

  @Override
  public ObjectDataSource asObject() {
    return null;
  }

  ;

  @Override
  protected JSONValue init(Object obj) {
    return null;
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public String toJson() {
    return NULL_STRING_VALUE;
  }

  @Override
  /**
   * Клонирование объекта.
   * @return клонированный объект.
   */
  public NullDataSource cloneBean() {
    return NullDataSource.getInstance();
  }

 /* public static class NullDataSourceFactory implements BeanFactory {

    @Override
    public AbstractDataSource create(String name, JSONValue value) {
      if (value.isNull() == null)
        throw new DataSourceStructureException("Невозможно создать NULL оболочку . Объект:"
            + value.toString() + " не  является пустым");
      return new NullDataSource(name);
    }
  }*/
}
