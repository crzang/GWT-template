package pro.crzang.web.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.UmbrellaException;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import pro.crzang.web.client.components.popup.ErrorMessageDialog;
import pro.crzang.web.client.rest.JsonFileProvider;
import pro.crzang.web.client.security.CurrentUser;
import pro.crzang.web.client.system.Base64Coder;
import pro.crzang.web.shared.JsonFileAction;
import pro.crzang.web.shared.JsonFileResult;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Загрузчик
 * Created by crzang.
 */
public class BootstrapperImpl implements Bootstrapper {
  @Inject
  private PlaceManager                 placeManager;
  @Inject
  private CurrentUser                  currentUser;
  @Inject
  private Provider<ErrorMessageDialog> errorMessageDialogProvider;
  @Inject
  private DispatchAsync dispatchAsync;
  private Logger logger = Logger.getLogger("");
  @Inject
  private Provider<JsonFileProvider> jsonFileProviderProvider;

  @Inject
  public BootstrapperImpl() {

  }

  @Override
  public void onBootstrap() {
    dispatchAsync.execute(new JsonFileAction("rest-config"), new AsyncCallback<JsonFileResult>() {
      @Override
      public void onFailure(Throwable caught) {

      }

      @Override
      public void onSuccess(JsonFileResult result) {
        System.out.println(result.getJsonValue());
        jsonFileProviderProvider.get().
            registerEntryPoint(result.getJsonValue(), new Command() {
              @Override
              public void execute() {
                checkCookies();
                placeManager.revealCurrentPlace();
                setExceptionHandler();
              }
            });
      }
    });


  }

  /**
   * Установка обработчика не пойманых Exception.
   */
  private void setExceptionHandler() {
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      public void onUncaughtException(final Throwable e) {
        processException(e);
      }
    });
  }

  /**
   * Обработка исключений.
   * Из стектрейса убираются лишние элементы и деобфусцируются.
   * Стектрейс выводится в консоль.
   *
   * @param e
   */
  private void processException(final Throwable e) {
    logger.log(Level.SEVERE, "Ex caught!", e);
  }

  /**
   * Очистка стектрейса, от лишних сообщений.
   *
   * @param e
   * @return
   */
  private Throwable unwrap(Throwable e) {
    if (e instanceof UmbrellaException) {
      UmbrellaException ue = (UmbrellaException) e;
      if (ue.getCauses().size() == 1) {
        return unwrap(ue.getCauses().iterator().next());
      }
    }
    return e;
  }

  /**
   * Проверка cookies.
   */
  private void checkCookies() {
    String userCookies = Cookies.getCookie(NameTokens.AUTH_CL_COOKIE);
    if (userCookies != null) {
      String loginPassPair = Base64Coder.decodeString(userCookies);
      String[] values = loginPassPair.split(":");
      if (values.length > 1) {
        if (values[0].equals("demo") && values[0].equals("demo")) {
          currentUser.setLoggetIn(true);
        }
      }
    }
  }

}