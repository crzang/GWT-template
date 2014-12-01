package pro.crzang.web.client.rest;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import pro.crzang.web.client.model.Bean;

/**
 * Абстрактный rest провайдер.
 * Created by crzang.
 */
public interface AbstractRestProvider<T extends Bean> {
  /**
   * Получение объекта по id
   * @param id объекта
   * @param callback результат
   */
  void getItem(String id,AsyncCallback<T> callback);

  /**
   * Созадние объекта из json
   * @param name имя
   * @param jsonValue json
   * @return ObjectDataSource
   */
  T create(String name,JSONValue jsonValue);
}
