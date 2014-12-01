package pro.crzang.web.client.components.popup;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import pro.crzang.web.client.gin.NameTokens;
import pro.crzang.web.client.security.CurrentUser;
import pro.crzang.web.client.system.Base64Coder;
import pro.crzang.web.shared.LoginAction;
import pro.crzang.web.shared.LoginResult;

/**
 * Created by crzang.
 */
class LoginPresenterImpl extends LoginPresenter
    implements LoginUiHandlers {
  @Inject
  private CurrentUser currentUser;
  @Inject
  private PlaceManager placeManager;
  @Inject
  private DispatchAsync dispatchAsync;

  @Inject
  LoginPresenterImpl(EventBus eventBus,
      ILoginView view,LoginProxy proxy) {
    super(eventBus, view, proxy, RevealType.Root);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    getView().show();
  }

  @Override
  public void onLogin(final String login,final  String password) {
    dispatchAsync.execute(new LoginAction(login, password), new AsyncCallback<LoginResult>() {
      @Override
      public void onFailure(Throwable caught) {
        getView().displayError();
      }

      @Override
      public void onSuccess(LoginResult result) {
        if(result.isAutorize()){
          getView().hide();
          String loginPassPair = login + ":" + password;
          String b64 = Base64Coder.encodeString(loginPassPair);
          Cookies.setCookie(NameTokens.AUTH_CL_COOKIE, b64);
          currentUser.setLoggetIn(true);
          placeManager.revealCurrentPlace();
        }
        else{
          getView().displayError();
        }
      }
    });

  }

}
