package pro.crzang.web.client.rest;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Провайдер запросов на сервер.
 * Created by crzang.
 */
interface RestProvider {

  /**
   * Получение данных GET запросом.
   *
   * @param clazz    класс результирующего объекта
   * @param url      адрес
   * @param callback возврат
   * @param <T>      клсаа объекта
   */
  <T> void getData(Class<T> clazz, String url, AsyncCallback<T> callback);

  /**
   * Получение json строки
   * @param url адрес
   * @param json
   * @param jsonCallback возврат json.
   */
  void getRawData(String url, String json, AsyncCallback<String> jsonCallback);

  /**
   * Установка точки входа.
   * @param entryPoint
   */
  void setEntryPoint(String entryPoint);

  /**
   * Получение точки входа.
   * @return
   */
  String getEntryPoint();
}
