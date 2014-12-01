package pro.crzang.web.client.system;

import com.google.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import pro.crzang.web.client.gin.NameTokens;
import pro.crzang.web.client.security.CurrentUser;

/**
 * Шапка.
 * Позволяет выполнить новый поиск или открыть каталог пользователя.
 * Created by crzang.
 */
class HeaderPresenterImpl extends HeaderPresenter implements HeaderUiHandlers {
  private final PlaceManager placeManager;
  private final CurrentUser  currentUser;

  @Inject
  HeaderPresenterImpl(EventBus eventBus,
      IHeaderView view,
      HeaderProxy proxy, PlaceManager placeManager, CurrentUser currentUser) {
    super(eventBus, view, proxy);
    getView().setUiHandlers(this);
    this.currentUser = currentUser;
    this.placeManager = placeManager;
  }

  @Override
  protected void onReveal() {
    super.onReveal();
  }

  @Override
  public void logout() {
    currentUser.setLoggetIn(false);
    placeManager.revealUnauthorizedPlace(NameTokens.login);
  }

}
