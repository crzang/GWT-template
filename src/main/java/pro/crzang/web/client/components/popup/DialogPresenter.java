package pro.crzang.web.client.components.popup;

import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * Презентер диалога.
 * Created by crzang.
 */
public abstract class DialogPresenter extends PresenterWidget<DialogPresenter.DialogView>{

  static final Object DIALOG_CONTENT_SLOT = new Object();
  static final Object DIALOG_HEADER_SLOT = new Object();

  public DialogPresenter(EventBus eventBus,
      DialogView view) {
    super(eventBus, view);
  }

  /**
   * Показ диалога с сообщением.
   * @param header заголовок
   * @param message сообщение
   */
  public abstract void show(String header, String message);

  /**
   * Показ диалога с виджетом
   * @param header заголовок
   * @param widget виджет
   */
  public abstract void show(String header, Widget widget);

  interface DialogView extends PopupView {
  }
}
