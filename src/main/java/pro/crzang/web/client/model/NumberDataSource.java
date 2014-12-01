package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;

/**
 * Класс, описывающий тип данных - целое число.
 *
 * @author Alexey
 */
public class NumberDataSource extends AbstractDataSource {

  /**
   * Конструктор (Integer).
   *
   * @param name  имя
   * @param value значение
   */
  public NumberDataSource(String name, Integer value) {
    super(DataSourceType.NUMBER, name, value);
  }

  /**
   * Конструктор (Long).
   *
   * @param name  имя
   * @param value значение
   */
  public NumberDataSource(String name, Long value) {
    super(DataSourceType.NUMBER, name, value);
  }

  /**
   * Конструктор (Double).
   *
   * @param name  имя
   * @param value значение
   */
  public NumberDataSource(String name, Double value) {
    super(DataSourceType.NUMBER, name, value);
  }

  @Override
  public Integer asInteger() {
    if (value.isNull() != null)
      return null;
    if (value.isNumber() != null) {
      Double doubleValue = value.isNumber().doubleValue();
      return doubleValue.intValue();
    }
    throw new DataSourceStructureException("Неверное обращение: объект" + getDsName()
        + " не является целым числом");
  }

  @Override
  public Long asLong() {
    if (value.isNull() != null)
      return null;
    if (value.isNumber() != null) {
      Double doubleValue = value.isNumber().doubleValue();
      return doubleValue.longValue();
    }
    throw new DataSourceStructureException("Неверное обращение: объект" + getDsName()
        + " не является целым числом");
  }

  @Override
  public Double asDouble() {
    if (value.isNull() != null)
      return null;
    if (value.isNumber() != null) {
      return value.isNumber().doubleValue();
    } else
      throw new DataSourceStructureException("Неверное обращение: объект" + getDsName()
          + " не является вещественным числом");
  }

  @Override
  public String asString() {
    if (value.isNull() != null)
      return null;
    if (value.isNumber() != null)
      return value.isNumber().toString();
    throw new DataSourceStructureException("Неверное обращение: объект" + getDsName()
        + " не является числом");
  }

  @Override
  protected JSONValue init(Object obj) {
    if (obj instanceof Integer)
      return new JSONNumber(((Integer) obj).doubleValue());
    if (obj instanceof Long)
      return new JSONNumber(((Long) obj).doubleValue());
    if (obj instanceof Double)
      return new JSONNumber((Double) obj);
    throw new DataSourceStructureException("Невозможно создать объект, тип объекта " + obj
        + " не поддерживается. Возможные типы: Long, Integer, Double.");
  }

  @Override
  public String toJson() {
    return value.isNumber().toString();
  }

  @Override
  /**
   * Клонирование объекта.
   * @return клонированный объект.
   */
  public NumberDataSource cloneBean() {
    return new NumberDataSource(getDsName(), asDouble());
  }

  /**
   * Фабрика для оболочки числового типа.
   *
   * @author Ivan Chernyshihin
   * @since 4.3
   */
  /*public static class NumberDataSourceFactory implements BeanFactory {

    @Override
    public AbstractDataSource create(String name, JSONValue value) {
      if (value == null || value.isNull() != null)
        return NullDataSource.getInstance();
      if (value.isNumber() == null)
        throw new DataSourceStructureException(
            "Невозможно созать оболочку числового типа, объект не  является числом");
      Double doubleValue = value.isNumber().doubleValue();
      if (doubleValue % 1 == 0)
        return new NumberDataSource(name, doubleValue);
      else if (doubleValue > Integer.MAX_VALUE || doubleValue < Integer.MIN_VALUE)
        return new NumberDataSource(name, doubleValue.longValue());
      else
        return new NumberDataSource(name, doubleValue.intValue());
    }
  }*/
}
