package pro.crzang.web.client.rest;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pro.crzang.web.client.model.bean.ErrorInfoBean;

/**
 * Асинхронный калбак, с собсветнным обработчиком ошибок.
 * Created by crzang.
 */
public interface AsyncCallbackWithHandler<T> extends AsyncCallback<T> {
  /**
   * Стектрейс и сообщение ошибки.
   *
   * @param errorInfo
   */
  void onError(ErrorInfoBean errorInfo);
}
