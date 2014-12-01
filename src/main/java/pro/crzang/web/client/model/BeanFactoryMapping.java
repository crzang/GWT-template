package pro.crzang.web.client.model;

import com.google.gwt.json.client.JSONValue;
import com.google.inject.Provider;

/**
 * Фабрика DataSource
 * Created by crzang.
 */
public interface BeanFactoryMapping {
  /**
   * Запос фабрики для класса
   * @param clazz класс
   * @returnфабрика класса
   */
  Provider<? extends Bean> getFactory(Class<?> clazz);

  /**
   * Регистрация фабрики
   * @param clazz класс
   * @param factory фабрика
   */
  void register(Class<?> clazz, Provider<? extends Bean> factory);

  /**
   * Создание DataSource
   * @param clazz класс
   * @param name
   * @param ds
   * @return
   */
  AbstractDataSource create(Class<?> clazz, String name, AbstractDataSource ds);

  /**
   * Создание DataSource из json
   * @param clazz
   * @param name
   * @param jsonValue
   * @return
   */
  AbstractDataSource create(Class<?> clazz, String name, JSONValue jsonValue);
}
