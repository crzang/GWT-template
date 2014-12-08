package pro.crzang.web.client.components.tablayout;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.NonLeafTabContentProxy;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import pro.crzang.web.client.gin.NameTokens;
import pro.crzang.web.client.system.ApplicationPresenter;

/**
 * Created by crzang.
 */
public abstract class TabLayoutPresenter extends TabContainerPresenter<TabLayoutPresenter
    .ITabLayoutView, TabLayoutPresenter.TabLayoutProxy> {

  @RequestTabs
  public static final GwtEvent.Type<RequestTabsHandler> TYPE_RequestTabs = new GwtEvent.Type<RequestTabsHandler>();

  @ContentSlot
  public static final GwtEvent.Type<RevealContentHandler<?>> TYPE_SetTabContent = new GwtEvent.Type<RevealContentHandler<?>>();

  public TabLayoutPresenter(EventBus eventBus,
      ITabLayoutView view, TabLayoutProxy proxy,
      GwtEvent.Type<RequestTabsHandler> requestTabsEventType,
      GwtEvent.Type<RevealContentHandler<?>> slot) {
    super(eventBus, view, proxy,TYPE_SetTabContent,requestTabsEventType, slot);
  }

  interface ITabLayoutView
      extends TabView, HasUiHandlers<TabLayoutUiHandlers> {
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
