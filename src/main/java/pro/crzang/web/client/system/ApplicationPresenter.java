package pro.crzang.web.client.system;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * Created by crzang.
 */
public class ApplicationPresenter
    extends Presenter<ApplicationPresenter.IApplicationView, ApplicationPresenter.ApplicationProxy> {
  /**
   * Слот для SystemView
    */
  @ContentSlot
  static final GwtEvent.Type<RevealContentHandler<?>>
                      SET_MAIN_CONTENT      = new GwtEvent.Type<RevealContentHandler<?>>();
  /**
   * Слот для HeaderView
   */
  static final Object SLOT_HEADER_PRESENTER = new Object();

  public ApplicationPresenter(EventBus eventBus,
      IApplicationView view, ApplicationProxy proxy, RevealType revealType) {
    super(eventBus, view, proxy, revealType);
  }

  interface IApplicationView extends View {
  }

  @ProxyStandard
  interface ApplicationProxy extends Proxy<ApplicationPresenter> {
  }
}
