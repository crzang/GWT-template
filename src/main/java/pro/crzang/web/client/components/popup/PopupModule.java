package pro.crzang.web.client.components.popup;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Created by crzang.
 */
public class PopupModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(LoginPresenter.class, LoginPresenterImpl.class, LoginPresenter.ILoginView.class,
        LoginView.class, LoginPresenter.LoginProxy.class);
    bindPresenterWidget(MessagePresenter.class, MessagePresenterImpl.class,
        MessagePresenter.MessageView.class,
        MessageWidget.class);
    bindPresenterWidget(DialogPresenter.class, DialogPresenterImpl.class,
        DialogPresenter.DialogView.class, DialogWidget.class);
    bind(ErrorMessageDialog.class).to(ErrorMessageDialogImpl.class);
  }
}
