package pro.crzang.web.client.components.popup;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import pro.crzang.web.client.gin.NameTokens;

/**
 * Презентер диалога входа в систему.
 * Created by crzang.
 */
public class LoginPresenter extends Presenter<LoginPresenter.ILoginView,
    LoginPresenter.LoginProxy> {
  public LoginPresenter(EventBus eventBus,
      ILoginView view, LoginProxy proxy, RevealType revealType) {
    super(eventBus, view, proxy, revealType);
  }

  interface ILoginView extends PopupView, HasUiHandlers<LoginUiHandlers> {
    /**
     * Показать сообщения ошибки входа.
     */
    void displayError();
  }

  @ProxyStandard
  @NameToken(NameTokens.login)
  public interface LoginProxy extends ProxyPlace<LoginPresenter> {
  }
}
