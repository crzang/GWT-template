package pro.crzang.web.client.rest;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Моудль рест провайдеров.
 * Created by crzang.
 */
public class RestModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bind(RestProvider.class).to(RestProviderImpl.class).in(Singleton.class);
    bind(JsonFileProvider.class).to(JsonFileProviderImpl.class).in(Singleton.class);
  }
}
