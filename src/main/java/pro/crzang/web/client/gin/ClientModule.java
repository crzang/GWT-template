package pro.crzang.web.client.gin;

import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

/**
 * Корневой модуль.
 * Created by crzang.
 */
public class ClientModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    install(new DefaultModule());
    install(new ApplicationModule());
    install(new DispatchAsyncModule());
    bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
    bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
    bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.login);

  }
}
