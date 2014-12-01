package pro.crzang.web.client.model;

/**
 * Класс, храняший соответсвиие типов полей медиатипа.
 *
 * @author Alexey
 */
public enum DataSourceType {
  NUMBER("number"),
  BOOLEAN("boolean"),
  STRING("string"),
  OBJECT("object"),
  ARRAY("array");

  private String name;

  /**
   * Конструктор.
   *
   * @param name
   */
  DataSourceType(String name) {
    this.name = name;
  }

  public static DataSourceType getTypeByName(String name) {
    for (DataSourceType dsType : DataSourceType.values())
      if (dsType.getName().equals(name))
        return dsType;
    return null;
  }

  /**
   * Получение имени типа.
   *
   * @return имя типа.
   */
  public String getName() {
    return this.name;
  }

}
