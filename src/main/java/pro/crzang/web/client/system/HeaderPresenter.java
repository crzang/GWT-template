package pro.crzang.web.client.system;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * Шапка корневого виджета.
 * Created by crzang.
 */
public abstract class HeaderPresenter extends Presenter<HeaderPresenter.IHeaderView,
    HeaderPresenter.HeaderProxy> {
  static Object SLOT_SEARCH_FIELD = new Object();

  public HeaderPresenter(EventBus eventBus, HeaderPresenter.IHeaderView view, HeaderPresenter.HeaderProxy proxy) {
    super(eventBus, view, proxy);
  }

  @ProxyStandard
  interface HeaderProxy extends Proxy<HeaderPresenter> {
  }

  interface IHeaderView extends View, HasUiHandlers<HeaderUiHandlers> {

  }
}
