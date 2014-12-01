package pro.crzang.web.client.components.tablayout;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import pro.crzang.web.client.system.SystemPresenter;

/**
 * Компонент с вкладками
 * Created by crzang.
 */
public class TabLayoutPresenterImpl extends TabLayoutPresenter
    implements TabLayoutUiHandlers {

  @Inject
  TabLayoutPresenterImpl(EventBus eventBus, ITabLayoutView view,
                         TabLayoutProxy proxy) {
    super(eventBus, view, proxy, SystemPresenter.SET_SYSTEM_CONTENT);
    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
  }
}
