package pro.crzang.web.shared;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * Запрос json файла.
 * Created by crzang.
 */
public class LoginAction extends UnsecuredActionImpl<LoginResult> {

  private String login;
  private String pass;

  public LoginAction(String login, String pass) {
    this.login = login;
    this.pass = pass;
  }

  @SuppressWarnings("unused")
  public LoginAction() {
  }

  public String getLogin() {
    return login;
  }

  public String getPass() {
    return pass;
  }
}
