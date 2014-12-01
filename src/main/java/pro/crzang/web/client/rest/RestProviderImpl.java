package pro.crzang.web.client.rest;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import pro.crzang.web.client.model.Bean;
import pro.crzang.web.client.model.BeanFactoryMapping;
import pro.crzang.web.client.model.BeanFactoryRegistration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by crzang.
 */
class RestProviderImpl implements RestProvider {
  private Logger logger = Logger.getLogger(RestProviderImpl.class.getName());
  private final Provider<BeanFactoryMapping> beanFactoryMapping;
  private String entryPoint;

  @Inject
  RestProviderImpl(Provider<BeanFactoryMapping> beanFactoryMapping,
                   Provider<BeanFactoryRegistration> beanFactoryRegistration) {
    beanFactoryRegistration.get().registrationFactories();
    this.beanFactoryMapping = beanFactoryMapping;
  }

  @Override
  public <T> void getData(Class<T> clazz, String url,
                          final AsyncCallback<T> callback) {
    getRawData(url, null, new JsonParseCallback<T>(callback, clazz));
  }

  @Override
  public void getRawData(String url, String json, final AsyncCallback<String> jsonCallback) {
    try {
      RequestBuilder requestBuilder = new RequestBuilder(json==null?RequestBuilder.GET:RequestBuilder.POST,
          entryPoint + url);
      if(json!=null) {
        requestBuilder.setHeader("Content-Type","application/json");
        requestBuilder.setRequestData(json);
      }
      requestBuilder.setCallback(new RequestResultCallback(jsonCallback));
      requestBuilder.send();
    }
    catch (RequestException ex) {
      logger.log(Level.SEVERE, "", ex);
      jsonCallback.onFailure(ex);
    }
  }

  @Override
  public void setEntryPoint(String entryPoint) {
    this.entryPoint = entryPoint;
  }

  @Override
  public String getEntryPoint() {
    return entryPoint;
  }

  /**
   * Получение ответа от сервера.
   */
  private static class RequestResultCallback implements RequestCallback {
    private final AsyncCallback<String> jsonCallback;

    public RequestResultCallback(AsyncCallback<String> jsonCallback) {
      this.jsonCallback = jsonCallback;
    }

    @Override
    public void onResponseReceived(Request request, Response response) {
      if(jsonCallback!=null) {
        int code = response.getStatusCode();
        if (code == 0) {
          jsonCallback.onFailure(new IOException("Не получен ответ от сервера"));
        }
        else if (code == 200 || code == 304) {
          jsonCallback.onSuccess(response.getText());
        }
        else {
          jsonCallback.onSuccess(response.getText());
        }
      }
    }

    @Override
    public void onError(Request request, Throwable exception) {
      jsonCallback.onFailure(exception);
    }
  }

  /**
   * Парсинг json строки.
   *
   * @param <T>
   */
  private class JsonParseCallback<T> implements AsyncCallback<String> {
    private final AsyncCallback<T> callback;
    private final Class<T> clazz;

    public JsonParseCallback(AsyncCallback<T> callback, Class<T> clazz) {
      this.callback = callback;
      this.clazz = clazz;
    }

    @Override
    public void onFailure(Throwable caught) {
      logger.log(Level.SEVERE, "", caught);
      Window.alert(caught.getMessage());
      callback.onFailure(caught);
    }

    @Override
    public void onSuccess(String respText) {
      Provider<? extends Bean> factory = beanFactoryMapping.get().getFactory(clazz);
      if (factory != null) {
        try {
          Bean bean = factory.get().setValue(JSONParser.parseStrict(respText));
          callback.onSuccess((T) bean);
        }
        catch (Exception ex) {
          logger.log(Level.SEVERE, "", ex);
          callback.onFailure(new Throwable("Error on response."));
        }
      }
      else {
        callback.onFailure(new Throwable("Bean factory not found."));
      }
    }
  }
}
