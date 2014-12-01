package pro.crzang.web.client.components.tablayout;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Created by crzang.
 */
public class TabLayoutModule extends AbstractPresenterModule {
  @Override
  protected void configure() {


    bindPresenter(TabLayoutPresenter.class,TabLayoutPresenterImpl.class,
        TabLayoutPresenter.ITabLayoutView.class, TabLayoutView.class,
        TabLayoutPresenter.TabLayoutProxy.class);
  }
}
