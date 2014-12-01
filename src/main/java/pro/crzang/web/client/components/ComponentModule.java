package pro.crzang.web.client.components;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import pro.crzang.web.client.components.popup.PopupModule;
import pro.crzang.web.client.components.tablayout.TabLayoutModule;

/**
 * Created by crzang.
 */
public class ComponentModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    install(new PopupModule());
    install(new TabLayoutModule());
  }
}
