package pro.crzang.web.client.system;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import pro.crzang.web.client.components.tablayout.TabLayoutPresenter;
import pro.crzang.web.client.event.ContentResizeEvent;

/**
 * Презентер #HOME. Со слотом под TabLayout.
 * Created by crzang.
 */
class SystemPresenterImpl extends SystemPresenter
    implements SystemUiHandlesrs {
  private final Provider<TabLayoutPresenter> tabLayoutPresenterProvider;

  @Inject
  SystemPresenterImpl(EventBus eventBus, ISystemView view, SystemProxy proxy,
      Provider<TabLayoutPresenter> tabLayoutPresenterProvider) {
    super(eventBus, view, proxy, ApplicationPresenter.SET_MAIN_CONTENT);
    this.tabLayoutPresenterProvider = tabLayoutPresenterProvider;
    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    setInSlot(SET_SYSTEM_CONTENT, tabLayoutPresenterProvider.get());
  }


  @Override
  public void onResize(int height) {
    getEventBus().fireEvent(new ContentResizeEvent(height));
  }

}
