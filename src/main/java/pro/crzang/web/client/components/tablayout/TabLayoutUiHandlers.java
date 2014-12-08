package pro.crzang.web.client.components.tablayout;

import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.UiHandlers;

import java.util.Map;

/**
 * Created by crzang.
 */
public interface TabLayoutUiHandlers extends UiHandlers {

  void onSelectTab(Tab newTab);
}
