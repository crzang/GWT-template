package pro.crzang.web.client.model;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import pro.crzang.web.client.model.bean.BeanModule;

/**
 * Created by crzang.
 */
public class ModelModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bind(BeanFactoryMapping.class).to(BeanFactoryMappingImpl.class).in(Singleton.class);
    bind(BeanFactoryRegistration.class).to(BeanFactoryRegistrationImpl.class).in(Singleton.class);
    bind(DataSourceService.class).to(DataSourceServiceImpl.class).in(Singleton.class);
    bind(ListDataSource.class);
    install(new BeanModule());
  }
}
