package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Маппинг фабрик бинов.
 *
 * @author Alexey
 */
final class BeanFactoryMappingImpl implements BeanFactoryMapping {

  private Map<Class<?>, Provider<? extends Bean>> factoryMap = new HashMap<Class<?>, Provider<? extends Bean>>();

  /**
   * Конструтор.
   */
  @Inject
  private BeanFactoryMappingImpl() {

  }

  /**
   * Получение фабрики по классу.
   *
   * @param clazz класс, для которого необходимо получит фабрику
   * @return фабрика
   */
  @Override
  public Provider<? extends Bean> getFactory(Class<?> clazz) {
    return factoryMap.get(clazz);
  }

  /**
   * Регистрация фабрики.
   *
   * @param clazz   класс
   * @param factory фабрика
   */
  @Override
  public void register(Class<?> clazz, Provider<? extends Bean> factory) {
    factoryMap.put(clazz, factory);
  }

  /**
   * Создание экземпляра медиатипа.
   *
   * @param clazz класс
   * @param name  имя поля в вышележащем объекте
   * @param ds    данные
   * @return медиатипа
   */
  @Override
  public AbstractDataSource create(Class<?> clazz, String name,
      AbstractDataSource ds) {
    return create(clazz,name,ds.value);
  }

  /**
   * Создание экземпляра медиатипа.
   *
   * @param clazz     класс
   * @param name      имя поля в вышележащем объекте
   * @param jsonValue данные
   * @return медиатипа
   */
  @Override
  public AbstractDataSource create(Class<?> clazz, String name,
      JSONValue jsonValue) {
    return getFactory(clazz).get().setName(name).setValue(jsonValue);
  }
}
