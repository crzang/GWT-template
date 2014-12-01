package pro.crzang.web.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import pro.crzang.web.client.components.ComponentModule;
import pro.crzang.web.client.rest.RestModule;
import pro.crzang.web.client.system.SystemModule;
import pro.crzang.web.client.model.ModelModule;

/**
 * модуль приложения.
 * Created by crzang.
 */
public class ApplicationModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    install(new SystemModule());
    install(new ComponentModule());
    install(new RestModule());
    install(new ModelModule());
  }
}
