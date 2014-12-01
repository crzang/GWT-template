package pro.crzang.web.client.model;

/**
 * Класс, описываюший исключения, возникшие по причине неверной структуры типов .
 *
 * @author Alexey
 */
public class DataSourceStructureException extends RuntimeException {

  /**
   * Контсруктор.
   */
  public DataSourceStructureException() {
    super();
  }

  /**
   * Конструктор.
   *
   * @param arg0 текст ошибки.
   * @param arg1 ошибка.
   */
  public DataSourceStructureException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  /**
   * Конструктор.
   *
   * @param exeption текст ошибки.
   */
  public DataSourceStructureException(String exeption) {
    super(exeption);
  }

}
