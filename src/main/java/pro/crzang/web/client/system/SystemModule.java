package pro.crzang.web.client.system;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import pro.crzang.web.client.security.CurrentUser;

/**
 * Системный модуль.
 * Классы для отображения основной страницы.
 * Created by crzang.
 */
public class SystemModule extends AbstractPresenterModule {
  @Override
  public void configure() {
    bindPresenter(SystemPresenter.class, SystemPresenterImpl.class,
        SystemPresenter.ISystemView.class,
        SystemView.class,
        SystemPresenter.SystemProxy.class);

    bindPresenter(ApplicationPresenter.class, ApplicationPresenterImpl.class,
        ApplicationPresenter.IApplicationView.class, ApplicationView.class,
        ApplicationPresenter.ApplicationProxy.class);

    bindPresenter(HeaderPresenter.class, HeaderPresenterImpl.class,
        HeaderPresenter.IHeaderView.class,
        HeaderView.class, HeaderPresenter.HeaderProxy.class);
    bind(CurrentUser.class).in(Singleton.class);
  }
}
