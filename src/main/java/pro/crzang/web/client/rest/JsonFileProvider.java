package pro.crzang.web.client.rest;

import com.google.gwt.user.client.Command;
import pro.crzang.web.client.model.bean.JsonFile;

/**
 * Провайдер регистрирующий точку входа для rest.
 * @author by crzang.
 */
public interface JsonFileProvider extends AbstractRestProvider<JsonFile>{
  /**
   * Регистрация rest точки входа
   * @param json
   * @param command возврат управления
   */
  void registerEntryPoint(String json, Command command);
}
