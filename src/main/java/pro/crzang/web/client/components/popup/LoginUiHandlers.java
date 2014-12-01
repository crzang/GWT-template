package pro.crzang.web.client.components.popup;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * События окна авторизации.
 * Created by crzang.
 */
interface LoginUiHandlers extends UiHandlers{
  /**
   * ПРи нажатии войти.
   * @param login логин
   * @param password пароль
   */
  void onLogin(String login, String password);
}
