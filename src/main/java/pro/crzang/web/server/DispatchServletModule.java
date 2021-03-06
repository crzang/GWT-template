package pro.crzang.web.server;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;

/**
 * Created by crzang.
 */
public class DispatchServletModule extends ServletModule {
  @Override
  public void configureServlets() {
    serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);
  }
}
