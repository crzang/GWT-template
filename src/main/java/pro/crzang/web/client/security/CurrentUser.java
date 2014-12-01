package pro.crzang.web.client.security;

/**
 * Текущий пользователь.
 * Created by crzang.
 */
public class CurrentUser {
  private Boolean isLoggedIn;
  private String nickname;

  public CurrentUser() {
    isLoggedIn=false;
    nickname="";
  }

  public CurrentUser(Boolean isLoggedIn, String nickname) {
    this.isLoggedIn = isLoggedIn;
    this.nickname = nickname;
  }

  /**
   * Проверка что авторизован.
   * @return
   */
  public Boolean isLoggedIn() {
    return isLoggedIn;
  }

  public String getNickname() {
    return nickname;
  }

  /**
   * Установка флага авторизации.
   * @param isLogin
   */
  public void setLoggetIn(boolean isLogin) {
    isLoggedIn=isLogin;
  }
}
