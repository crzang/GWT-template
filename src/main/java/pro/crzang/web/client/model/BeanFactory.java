package pro.crzang.web.client.model;

/**
 * Интерфейс фабрики медиатипа.
 *
 * @author Alexey
 */
public interface BeanFactory {

  /**
   * Создание медиатипа.
   *
   * @param name      имя
   * @param jsonValue данные
   * @return объект медиатипа
   */
  public AbstractDataSource create();

}
