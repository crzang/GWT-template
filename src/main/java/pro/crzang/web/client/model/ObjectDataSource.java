package pro.crzang.web.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, описываюший объект.
 *
 * @author Alexey
 */
public class ObjectDataSource extends AbstractDataSource {

  public static final String PATH_SEPARATOR = "[\\.\\\\/]";
  @Inject
  private Provider<ListDataSource> listDataSourceProvider;

  /**
   * Конструктор.
   */
  public ObjectDataSource() {
    super(DataSourceType.OBJECT, "", new JSONObject());
  }


  private JSONValue getJsonField(String fieldName, JSONValue jsonValue) {
    if (jsonValue.isObject() != null) {
      JSONObject jsonObject = jsonValue.isObject();
      if (jsonObject.containsKey(fieldName)) {
        return jsonObject.get(fieldName);
      }
      else {
        return JSONNull.getInstance();
      }
    }
    else {
      throw new DataSourceStructureException("Неверное обращение: объект " + getDsName()
          + " не является объектом");
    }
  }

  /**
   * Получение значения поля объекта по пути.
   *
   * @param path путь до поля объекта
   * @return значения поля объекта
   */
  private JSONValue getFieldByPath(String path) {
    if (path == null || path.isEmpty()) {
      throw new DataSourceStructureException("Неверное обращение к полю объекта " + getDsName()
          + " по пути, невозможно получить свойство объекта по пустому пути.");
    }
    JSONValue jsonValue = value;
    String paths[] = path.split(PATH_SEPARATOR);
    if (paths.length == 1) {
      return getJsonField(path, jsonValue);
    }
    String currentPath = "";
    for (int i = 0; i < paths.length; i++) {
      String fieldName = paths[i];
      currentPath = currentPath + fieldName;
      jsonValue = getJsonField(fieldName, jsonValue);
      if (jsonValue.isNull() != null) {
        return jsonValue;
      }
      else if ((i < paths.length - 1) && jsonValue.isObject() == null) {
        throw new DataSourceStructureException("Невозможно получить поле: объекта " + getDsName()
            + " по пути:" + path + ". Поле:" + currentPath + "не  является объектом");
      }
      currentPath = currentPath + ".";
    }
    return jsonValue;

  }

  @Override
  public AbstractDataSource getField(String fieldName) {
    JSONValue json = getFieldByPath(fieldName);
    if (json == null || json.isNull() != null) {
      return new NullDataSource(fieldName);
    }
    Object value = getPrimiteveField(json);
    if (value != null) {
      if (value instanceof Double) {
        return new NumberDataSource(fieldName, (Double) value);
      }
      else if (value instanceof String) {
        return new StringDataSource(fieldName, (String) value);
      }
      else if (value instanceof Boolean) {
        return new BooleanDataSource(fieldName, (Boolean) value);
      }
      else {
        throw new DataSourceStructureException("Невозможно получить поле " + fieldName
            + " объекта " + getDsName() + " примитивный тип поля не поддерживается");
      }
    }
    if (json.isObject() != null) {
      return new ObjectDataSource().setName(fieldName).setValue(json.isObject());
    }
    if (json.isArray() != null) {
      return listDataSourceProvider.get().setName(fieldName).setValue(json.isArray());
    }
    throw new DataSourceStructureException("Невозможно получить поле " + fieldName + " объекта "
        + getDsName() + " тип поля не  поддерживается");
  }

  /**
   * Получение значения полей, значения которых являются примитивами.
   *
   * @param json json значение
   * @return значение поля
   */
  private Object getPrimiteveField(JSONValue json) {
    if (json.isNumber() != null) {
      return json.isNumber().doubleValue();
    }
    if (json.isBoolean() != null) {
      return Boolean.valueOf(json.isBoolean().booleanValue());
    }
    if (json.isString() != null) {
      return json.isString().stringValue();
    }
    return null;
  }

  /**
   * Получение поля в виде строки.
   *
   * @param fieldName имя поля
   * @return значение поля в виде строки
   */
  public String getStringField(String fieldName) {
    Object value = getPrimiteveField(getFieldByPath(fieldName));
    if (value instanceof String) {
      return (String) value;
    }
    return null;
  }

  /**
   * Получение поля в виде логического значения.
   *
   * @param fieldName имя поля
   * @return значение поля в виде логического значения
   */
  public Boolean getBooleanField(String fieldName) {
    Object value = getPrimiteveField(getFieldByPath(fieldName));
    if (value instanceof Boolean) {
      return (Boolean) value;
    }
    return false;
  }

  /**
   * Получение поля в вешественного числа.
   *
   * @param fieldName имя поля
   * @return значение поля в вешественного числа
   */
  public Double getDoubleField(String fieldName) {
    Object value = getPrimiteveField(getFieldByPath(fieldName));
    if (value instanceof Double) {
      return (Double) value;
    }
    return 0.0;
  }

  /**
   * Получение поля в виде целого числа(4 байта).
   *
   * @param fieldName имя поля
   * @return значение поля в виде целого числа
   */
  public Integer getIntegerField(String fieldName) {
    Double doubleValue = getDoubleField(fieldName);
    return doubleValue == null ? 0 : doubleValue.intValue();
  }

  /**
   * Получение поля в виде целого числа (8 байт).
   *
   * @param fieldName имя поля
   * @return значение поля в виде целого числа
   */
  public Long getLongField(String fieldName) {
    Double doubleValue = getDoubleField(fieldName);
    return doubleValue == null ? 0 : doubleValue.longValue();
  }

  @Override
  public void setField(String fieldName, AbstractDataSource fieldValue) {
    JSONValue jsonFieldValue = fieldValue == null ? JSONNull.getInstance() : fieldValue.value;
    setFieldByPath(fieldName, jsonFieldValue);
  }

  private void setFieldToObject(JSONValue jsonObject, String fieldName, JSONValue jsonFieldValue) {
    if (jsonObject.isObject() != null) {
      jsonObject.isObject().put(fieldName, jsonFieldValue);
    }
    else {
      throw new DataSourceStructureException("Невозможно установить новое значение поля:"
          + fieldName + " для объекта " + jsonObject
          + ", рассматриваемая сушность не  является объектом");
    }
  }

  private void setFieldByPath(String path, JSONValue fieldValue) {
    if (path == null || path.isEmpty()) {
      throw new DataSourceStructureException("Неверное обращение к полю объекта " + getDsName()
          + " по пути, невозможно получить свойство объекта по пустому пути.");
    }
    String paths[] = path.split(PATH_SEPARATOR);
    JSONValue jsonValue = this.value;
    String fieldPath = paths[paths.length - 1];
    if (paths.length != 1) {
      int endIdx = path.indexOf(fieldPath) - 1;
      String jsonObjectPath = path.substring(0, endIdx);
      jsonValue = getFieldByPath(jsonObjectPath);
      // CMJ-15224 создаем новые датасурсы если они не найдены
      if (jsonValue.isObject() == null) {
        setFieldByPath(jsonObjectPath, new ObjectDataSource().setName("ods").value);
        jsonValue = getFieldByPath(jsonObjectPath);
      }
    }
    setFieldToObject(jsonValue, fieldPath, fieldValue);
  }

  /**
   * Установка поля, значение которго является строкой.
   *
   * @param fieldName имя поля
   * @param value     значение, которое необходимо установить
   */
  public void setStringField(String fieldName, String value) {
    JSONValue jsonValue = JSONNull.getInstance();
    if (value != null) {
      jsonValue = new JSONString(value);
    }
    setFieldByPath(fieldName, jsonValue);
  }

  /**
   * Установка поля, значение которго является логическим значением.
   *
   * @param fieldName имя поля
   * @param value     значение, которое необходимо установить
   */
  public void setBooleanField(String fieldName, Boolean value) {
    JSONValue jsonValue = JSONNull.getInstance();
    if (value != null) {
      jsonValue = JSONBoolean.getInstance(value);
    }
    setFieldByPath(fieldName, jsonValue);
  }

  /**
   * Установка поля, значение которго является вещественным числом.
   *
   * @param fieldName имя поля
   * @param value     значение, которое необходимо установить
   */
  public void setDoubleField(String fieldName, Double value) {
    JSONValue jsonValue = JSONNull.getInstance();
    if (value != null) {
      jsonValue = new JSONNumber(value);
    }
    setFieldByPath(fieldName, jsonValue);
  }

  /**
   * Установка поля, значение которго является целым числом(4 байта).
   *
   * @param fieldName имя поля
   * @param value     значение, которое необходимо установить
   */
  public void setIntegerField(String fieldName, Integer value) {
    setDoubleField(fieldName, value.doubleValue());
  }

  /**
   * Установка поля, значение которго является целым числом(8 байт).
   *
   * @param fieldName имя поля
   * @param value     значение, которое необходимо установить
   */
  public void setLongField(String fieldName, Long value) {
    setDoubleField(fieldName, value.doubleValue());
  }

  @Override
  public Map<String, AbstractDataSource> getFields() {
    if (this.value.isObject() != null) {
      Map<String, AbstractDataSource> fields = new HashMap<String, AbstractDataSource>();
      for (String key : this.value.isObject().keySet()) {
        fields.put(key, getField(key));
      }
      return fields;
    }
    throw new DataSourceStructureException("Невозможно получить cписок полей  объекта "
        + getDsName() + ", рассматриваемая сушность не  является объектом");

  }

  public JavaScriptObject getJsObject() {
    return value.isObject().getJavaScriptObject();
  }

  @Override
  public ObjectDataSource asObject() {
    return this;
  }

  @Override
  protected JSONValue init(Object obj) {
    return (JSONValue) obj;
  }

  @Override
  public boolean isObject() {
    return true;
  }

  @Override
  public String toJson() {
    return toJson(getJsObject());
  }

  /**
   * Получение поля в виде списка значений(элментами списка являются объекты).
   *
   * @param fieldName  имя поля
   * @param objectType тип элментов списка
   * @return список
   */
  public <E> List<E> getListField(String fieldName, Class<E> objectType) {
    AbstractDataSource field = getField(fieldName);
    ListDataSource<E> listDataSource = listDataSourceProvider.get().setObjectType(objectType);
    return field.isNull() ? null :
        (List<E>) listDataSource.setName(fieldName).setValue(field.value.isArray());
  }

  /**
   * Установка поля, значнием котрого является список(элементы списка - объекты).
   *
   * @param fieldName  имя поля
   * @param objectType тип элментов списка
   * @param value      значение
   */
  public <E> void setListField(String fieldName, List<E> value, Class<E> objectType) {
    ListDataSource listDataSource = listDataSourceProvider.get().setObjectType(objectType);
    setField(fieldName, value == null ? NullDataSource.getInstance() : listDataSource
        .setName(fieldName).setValue(new JSONArray()));
    for (E item : value) {
      listDataSource.add(item);
    }
  }

  /**
   * Получение поля-объекта.
   *
   * @param fieldName  имя поля
   * @param objectType тип объекта.
   * @return поле-объект.
   */
  /*@SuppressWarnings("unchecked")
  public <T> T getObjectField(String fieldName, Class<T> objectType) {
    return (T) BeanFactoryMappingImpl
        .create(objectType, fieldName, getField(fieldName));
  }*/

  /**
   * Получение поля-объекта.
   *
   * @param fieldName  имя поля
   * @param objectType тип объекта.
   * @return поле-объект.
   */
  @SuppressWarnings("unchecked")
  public <T> T getObjectField(String fieldName, Class<T> objectType) {
    return (T) beanFactoryMapping.getFactory(objectType).get().setValue(getField(fieldName)
        .value);
  }

  @Override
  /**
   * Клонирование объекта.
   * @return клонированный объект.
   */
  public ObjectDataSource cloneBean() {
    return (ObjectDataSource) new ObjectDataSource().setValue(cloneJSON(this.value)).setName(
        getDsName());
  }

  /**
   * Фабрика для оболочки объектных типов.
   *
   * @author Ivan Chernyshihin
   * @since 4.3
   */
  /*public static class ObjectDataSourceFactory implements BeanFactory {

    @Override
    public AbstractDataSource create(String name, JSONValue jsonValue) {
      if (jsonValue == null || jsonValue.isNull() != null)
        return NullDataSource.getInstance();
      if (jsonValue.isObject() == null)
        throw new DataSourceStructureException("Невозможно создать оболочку для объекта. Объект:"
            + jsonValue.toString() + " не  является объектом");
      return new ObjectDataSource(name, jsonValue.isObject());
    }
  }*/
}
