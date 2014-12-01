package pro.crzang.web.client.system;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import pro.crzang.web.client.gin.NameTokens;
import pro.crzang.web.client.security.LoggedInGatekeeper;

/**
 * Created by crzang.
 */
public abstract class SystemPresenter extends
    Presenter<SystemPresenter.ISystemView, SystemPresenter.SystemProxy> {
  /**
   * Слот для TabLayout
   */
  @ContentSlot
  public static final GwtEvent.Type<RevealContentHandler<?>> SET_SYSTEM_CONTENT = new GwtEvent.Type<RevealContentHandler<?>>();

  public SystemPresenter(EventBus eventBus, ISystemView view, SystemProxy proxy,
      GwtEvent.Type<RevealContentHandler<?>> slot) {
    super(eventBus, view, proxy, slot);
  }

  public interface ISystemView extends View, HasUiHandlers<SystemUiHandlesrs> {
    /**
     * Открытие каталога пользователя.
     */
    void openUserFolders();
  }

  @ProxyStandard @NameToken(NameTokens.HOME) @UseGatekeeper(
      LoggedInGatekeeper.class)
  public interface SystemProxy extends ProxyPlace<SystemPresenter> {
  }
}
