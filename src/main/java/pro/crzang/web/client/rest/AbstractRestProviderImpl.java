package pro.crzang.web.client.rest;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import pro.crzang.web.client.model.Bean;
import pro.crzang.web.client.model.ObjectDataSource;
import pro.crzang.web.client.components.popup.ErrorMessageDialog;
import pro.crzang.web.client.components.popup.MessagePresenter;
import pro.crzang.web.client.model.bean.ErrorInfoBean;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Базовая реализация абстрактного rest провайдера.
 * Created by crzang.
 */
abstract class AbstractRestProviderImpl<T extends Bean> implements
    AbstractRestProvider<T> {
  @Inject
  protected Provider<RestProvider> restProviderProvider;
  @Inject
  private Provider<MessagePresenter> messagePresenterProvider;
  @Inject
  private Provider<ErrorMessageDialog> errorMessageDialogProvider;
  @Inject
  private Provider<ErrorInfoBean> errorInfoBeanProvider;

  private Map<String, T> cache;
  private Logger logger = Logger.getLogger(AbstractRestProviderImpl.class.getName());

  /**
   * Конструктор.
   *
   * @param useCache кешировать запросы.
   */
  AbstractRestProviderImpl(boolean useCache) {
    if (useCache) {
      cache = new HashMap<String, T>();
    }
  }

  @Override
  public void getItem(final String id, AsyncCallback<T> callback) {
    throw new RuntimeException("Для провайдера" + getClass() + " не реализовано получение.");
  }

  /**
   * Получение объекта.
   *
   * @param url
   * @param callback
   */
  protected void getData(String url, final AsyncCallback<T> callback) {
    getData(url,null,callback);
  }

  /**
   * Получение объекта.
   *
   * @param url
   * @param callback
   */
  protected void getData(String url,String json,final AsyncCallback<T> callback) {
    if (cache != null && cache.containsKey(url)) {
      callback.onSuccess(cache.get(url));
    }
    else {
      restProviderProvider.get().getRawData(url,json, new JsonCallback(url, callback));
    }
  }
  @Override
  public T create(String name, JSONValue jsonValue) {
    throw new RuntimeException("Для провайдера" + getClass() + " не реализовано создание объекта.");
  }

  /**
   * Преобразование json в объект
   */
  private class JsonCallback implements AsyncCallback<String> {
    private final String url;
    private final AsyncCallback<T> callback;

    public JsonCallback(String url, AsyncCallback<T> callback) {
      this.url = url;
      this.callback = callback;
    }

    @Override
    public void onFailure(Throwable caught) {
      logger.log(Level.SEVERE, "", caught);
      ErrorInfoBean errorInfo = errorInfoBeanProvider.get();
      errorInfo.setCaught(caught);
      if (callback instanceof AsyncCallbackWithHandler) {
        ((AsyncCallbackWithHandler) callback).onError(errorInfo);
      }
      else {
        showErrorWithStackTrace(errorInfo);
      }
      callback.onFailure(caught);
    }

    @Override
    public void onSuccess(String json) {
      try {
        JSONValue jsonValue = JSONParser.parseStrict(json);
        ObjectDataSource objectDataSource = (ObjectDataSource) new ObjectDataSource().setValue(jsonValue);
        if (objectDataSource.getBooleanField("error") != null && objectDataSource.getBooleanField("error")) {
          ErrorInfoBean errorInfoBean = (ErrorInfoBean) errorInfoBeanProvider.get().setValue(jsonValue);
          if (callback instanceof AsyncCallbackWithHandler) {
            ((AsyncCallbackWithHandler) callback).onError(errorInfoBean);
          }
          else {
            if (errorInfoBean.getStackTrace() == null || errorInfoBean.getStackTrace().isEmpty()) {
              messagePresenterProvider.get().show(errorInfoBean.getMessage());
            }
            else {
              showErrorWithStackTrace(errorInfoBean);
            }
            throw new Exception(errorInfoBean.getMessage());
          }
        }
        else {
          T documentJ = create("", jsonValue);
          if (cache != null) {
            cache.put(url, documentJ);
          }
          if(callback!=null) {
            callback.onSuccess(documentJ);
          }
        }
      }
      catch (Exception e) {
        logger.log(Level.SEVERE, "", e);
        if(callback!=null) {
          callback.onFailure(e);
        }
      }
    }
  }

  private void showErrorWithStackTrace(ErrorInfoBean errorInfoBean) {
    errorMessageDialogProvider.get().show(errorInfoBean);
  }
}
