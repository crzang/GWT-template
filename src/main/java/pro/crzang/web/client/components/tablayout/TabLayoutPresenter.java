package pro.crzang.web.client.components.tablayout;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * Created by crzang.
 */
public abstract class TabLayoutPresenter extends Presenter<TabLayoutPresenter
    .ITabLayoutView, TabLayoutPresenter.TabLayoutProxy> {
  /**
   * Слот для вкладки поиска
   */
/*  @ContentSlot
  public static final GwtEvent.Type<RevealContentHandler<?>>
      SET_SEARCH_CONTENT        = new GwtEvent.Type<RevealContentHandler<?>>();
*/

  public TabLayoutPresenter(EventBus eventBus,
      ITabLayoutView view, TabLayoutProxy proxy,
      GwtEvent.Type<RevealContentHandler<?>> slot) {
    super(eventBus, view, proxy, slot);
  }

  interface ITabLayoutView
      extends View, HasUiHandlers<TabLayoutUiHandlers> {
    /**
     * Установка высоты
     * @param s
     */
    void setHeight(String s);

  }

  @ProxyStandard
  interface TabLayoutProxy extends Proxy<TabLayoutPresenter> {
  }
}
