package pro.crzang.web.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.*;
import com.google.inject.Inject;

import java.util.Map;

/**
 * Класс, описываюший медиатип.
 *
 * @author Alexey
 */
public abstract class AbstractDataSource implements Bean {

  @Inject
  protected DataSourceService dataSourceService;

  @Inject
  protected BeanFactoryMapping beanFactoryMapping;
  protected transient JSONValue value;
  private transient DataSourceType dsType;
  private transient String dsName;

  /**
   * Конструктор.
   *
   * @param type   тип объекта.
   * @param dsName имя объекта.
   */
  protected AbstractDataSource(DataSourceType type, String dsName, Object value) {
    this.dsType = type;
    this.dsName = dsName;
    setValue(value);
  }

  /**
   * Получение типа данных.
   *
   * @return тип данных
   */
  public DataSourceType getDsType() {
    return this.dsType;
  }

  /**
   * Получение имени.
   *
   * @return имя
   */
  public String getDsName() {
    return this.dsName;
  }

  /**
   * Представление данных в виде целого числа.
   *
   * @return число
   */
  public Integer asInteger() {
    DataSourceStructureException ex = new DataSourceStructureException("Неверное обращение: объект"
        + getDsName() + " не является целым числом");
    throwStructuireException(ex);
    return null;
  }

  /**
   * Представление данных в виде целого числа.
   *
   * @return число
   */
  public Long asLong() {
    DataSourceStructureException ex = new DataSourceStructureException("Неверное обращение: объект"
        + getDsName() + " не является целым числом");
    throwStructuireException(ex);
    return null;
  }

  /**
   * Представление данных в виде строки.
   *
   * @return строка
   */
  public String asString() {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Неверное обращение: объект " + getDsName() + " не является строкой");
    throwStructuireException(ex);
    return null;
  }

  /**
   * Представление данных в виде логической переменной.
   *
   * @return логическая перменная
   */
  public Boolean asBoolean() {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Неверное обращение: объект " + getDsName() + " не является логической пеоременной");
    throwStructuireException(ex);
    return null;
  }

  ;

  /**
   * Представление данных в виде вещественного числа.
   *
   * @return вещественное число
   */
  public Double asDouble() {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Неверное обращение: объект " + getDsName() + "   не является вещественным числом");
    throwStructuireException(ex);
    return null;
  }

  /**
   * Представление данных в виде списка.
   *
   * @return список.
   */
  public ListDataSource asList() {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Неверное обращение: объект " + getDsName() + "  не является массивом");
    throwStructuireException(ex);
    return null;
  }

  ;

  /**
   * Представление данных в виде объекта.
   *
   * @return список.
   */
  public ObjectDataSource asObject() {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Неверное обращение: объект " + getDsName() + "  не является объектом");
    throwStructuireException(ex);
    return null;
  }

  ;

  /**
   * Является ли текуший тип массивом.
   *
   * @return да/нет
   */
  public boolean isArray() {
    return false;
  }

  ;

  /**
   * Является ли текуший тип null значением.
   *
   * @return да/нет
   */
  public boolean isNull() {
    return false;
  }

  /**
   * Является ли текуший тип undefined значением.
   *
   * @return да/нет
   */
  public boolean isUndefined() {
    return false;
  }

  /**
   * Является ли текуший тип объектом.
   *
   * @return да/нет
   */
  public boolean isObject() {
    return false;
  }

  /**
   * Добавление элмента в массив.
   *
   * @param dataSource добавляемый элмент
   */
  public void addItem(AbstractDataSource dataSource) {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Невозможно добавить элмент, объект " + getDsName() + "  не является объектом");
    throwStructuireException(ex);
  }

  /**
   * Получение списка полей.
   *
   * @return поля
   */
  public Map<String, AbstractDataSource> getFields() {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Невозможно получить список полей, объект " + getDsName() + "  не является объектом");
    throwStructuireException(ex);
    return null;
  }

  /**
   * Установка значения поля.
   *
   * @param fieldName  имя поля
   * @param fieldValue значение
   */
  public void setField(String fieldName, AbstractDataSource fieldValue) {
    DataSourceStructureException ex = new DataSourceStructureException(
        "Невозможно установить значнние для поля :" + fieldName + " объект " + getDsName()
            + "  не является объектом");
    throwStructuireException(ex);
  }

  /**
   * Получение значения поля по имени поля.
   *
   * @param fieldName имя поля
   * @return значение
   */
  public AbstractDataSource getField(String fieldName) {
    DataSourceStructureException ex = new DataSourceStructureException("Невозможно получить поле :"
        + fieldName + " объект " + getDsName() + "  не является объектом");
    throwStructuireException(ex);
    return null;
  }

  public AbstractDataSource setValue(JSONValue value) {
    this.value = value;
    return this;
  }

  protected abstract JSONValue init(Object obj);

  protected AbstractDataSource setValue(Object obj) {
    if (obj == null)
      this.value = JSONNull.getInstance();
    else
      this.value = init(obj);
    return this;
  }

  protected native String toJson(JavaScriptObject jsObject)
  /*-{
    return JSON.stringify(jsObject);
  }-*/;

  /**
   * Преобразование к строке JSON.
   *
   * @return JSON
   */
  public abstract String toJson();

  private void throwStructuireException(DataSourceStructureException ex) {
    throw ex;
  }

  /**
   * Клонирование объекта.
   *
   * @return клонированный объект.
   */
  public abstract AbstractDataSource cloneBean();

  /**
   * Клонирование json объекта.
   *
   * @param jsonValue кланироемое значение
   * @return клонированный объект
   */
  protected JSONValue cloneJSON(JSONValue jsonValue) {
    if (jsonValue.isObject() != null)
      return JSONParser.parseStrict(toJson(jsonValue.isObject().getJavaScriptObject()));
    else if (jsonValue.isArray() != null)
      return JSONParser.parseStrict(toJson(jsonValue.isArray().getJavaScriptObject()));
    if (jsonValue.isString() != null)
      return new JSONString(jsonValue.isString().stringValue());
    else if (jsonValue.isBoolean() != null)
      return JSONBoolean.getInstance(jsonValue.isBoolean().booleanValue());
    else if (jsonValue.isNumber() != null)
      return new JSONNumber(jsonValue.isNumber().doubleValue());
    else if (jsonValue.isNull() != null)
      return JSONNull.getInstance();
    throw new DataSourceStructureException("Невозможно клонировать объект " + jsonValue
        + " данный тип объектов не поддерживается");
  }

  public JSONValue getValue() {
    return value;
  }

  public AbstractDataSource setName(String dsName) {
    this.dsName=dsName;
    return this;
  }
}
