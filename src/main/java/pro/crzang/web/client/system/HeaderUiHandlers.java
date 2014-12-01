package pro.crzang.web.client.system;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * События шапки корневого виджета.
 * Created by crzang.
 */
interface HeaderUiHandlers extends UiHandlers {

  /**
   * Выйти из системы.
   */
  void logout();

}
