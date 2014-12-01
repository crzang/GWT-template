package pro.crzang.web.client.model.bean;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Created by crzang.
 */
public class BeanModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    //bind interface to impl
    bind(ErrorInfoBean.class).to(ErrorInfoJ.class);

  }
}
