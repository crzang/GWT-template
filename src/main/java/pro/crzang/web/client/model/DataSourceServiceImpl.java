package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Класс содержит общие методы для работы с моделью данных.
 *
 * @author Alexey
 */
public class DataSourceServiceImpl implements DataSourceService {

  @Inject
  private BeanFactoryMapping beanFactoryMapping;
  @Inject
  private Provider<ListDataSource> listDataSourceProvider;
  @Inject
  public DataSourceServiceImpl() {
  }

  /**
   * Создание обхекта данных соответствуюшего типа.
   *
   * @param fieldName имя поля
   * @param obj       данные
   * @return объект, содержаший данные
   */
  @Override
  public AbstractDataSource createDataSource(String fieldName, Object obj) {
    AbstractDataSource ds;
    if (obj == null)
      return new NullDataSource(fieldName);
    if (obj instanceof Double)
      return new NumberDataSource(fieldName, (Double) obj);
    if (obj instanceof Integer)
      return new NumberDataSource(fieldName, (Integer) obj);
    if (obj instanceof Long)
      return new NumberDataSource(fieldName, (Long) obj);
    if (obj instanceof Boolean)
      return new BooleanDataSource(fieldName, (Boolean) obj);
    if (obj instanceof String)
      return new StringDataSource(fieldName, (String) obj);
    if (obj instanceof JSONValue) {
      JSONValue json = (JSONValue) obj;
      if (json.isObject() != null)
        return new ObjectDataSource().setName(fieldName).setValue(json.isObject());
      else if (json.isArray() != null)
        return listDataSourceProvider.get().setObjectType(AbstractDataSource.class).setValue(json
            .isArray()).setName(fieldName);
      else if (json.isNull() != null)
        return new NullDataSource(fieldName);
      else if (json.isNumber() != null)
        return beanFactoryMapping
            .create(NumberDataSource.class, fieldName, json.isNumber());

      else if (json.isBoolean() != null)
        return new BooleanDataSource(fieldName, json.isBoolean().booleanValue());
      else if (json.isString() != null)
        return new StringDataSource(fieldName, json.isString().stringValue());
    }

    throw new DataSourceStructureException("Тип поля " + fieldName
        + " не соответсвует ни одному заданому в системе типу");
  }
}
