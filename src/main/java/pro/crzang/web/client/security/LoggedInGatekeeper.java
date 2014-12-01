package pro.crzang.web.client.security;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

/**
 * Created by crzang.
 */
public class LoggedInGatekeeper implements Gatekeeper {
  private final CurrentUser currentUser;

  @Inject
  public LoggedInGatekeeper(CurrentUser currentUser) {
    this.currentUser = currentUser;
  }

  @Override
  public boolean canReveal() {
    return currentUser.isLoggedIn();
  }
}
