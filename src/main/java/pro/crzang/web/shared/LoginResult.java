package pro.crzang.web.shared;

import com.gwtplatform.dispatch.shared.Result;

// Результат запроса. Json из файла.
public class LoginResult implements Result {

  private boolean autorize;
  private int permission;

  public LoginResult(boolean autorize, int permission) {
    this.autorize = autorize;
    this.permission = permission;
  }

  @SuppressWarnings("unused")
  public LoginResult() {
  }

  public boolean isAutorize() {
    return autorize;
  }

  public int getPermission() {
    return permission;
  }
}
