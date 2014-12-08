package pro.crzang.web.client.components.tablayout;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.proxy.NotifyingAsyncCallback;
import com.gwtplatform.mvp.client.proxy.TabContentProxy;
import pro.crzang.web.client.system.SystemPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Компонент с вкладками
 * Created by crzang.
 */
public class TabLayoutPresenterImpl extends TabLayoutPresenter
    implements TabLayoutUiHandlers {

  private Map<Tab, TabContentProxy<?>> tabProxys = new HashMap<>();
  private Tab firstTab;
  private Map<Tab, Presenter<?, ?>> tabs=new HashMap<>();

  @Inject
  TabLayoutPresenterImpl(EventBus eventBus, ITabLayoutView view,
      TabLayoutProxy proxy) {
    super(eventBus, view, proxy, TYPE_RequestTabs, SystemPresenter.SET_SYSTEM_CONTENT);
    getView().setUiHandlers(this);
  }

  @Override
  public Tab addTab(TabContentProxy<?> tabProxy) {
    Tab tab = super.addTab(tabProxy);
    if (firstTab == null) {
      firstTab = tab;
    }
    tabProxys.put(tab, tabProxy);
    return tab;
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    onSelectTab(firstTab);
  }

  @Override
  public void setInSlot(Object slot, PresenterWidget<?> content) {
    super.setInSlot(slot, content);
  }

  @Override
  public void onSelectTab(final Tab tab) {
    if (tabs.containsKey(tab)) {
      setInSlot(TYPE_SetTabContent, tabs.get(tab));
    }
    else {
      tabProxys.get(tab).getRawPresenter(new NotifyingAsyncCallback<Presenter<?, ?>>(getEventBus()) {
        @Override
        protected void success(Presenter<?, ?> result) {
          tabs.put(tab,result);
          onSelectTab(tab);
        }
      });
    }
  }
}
