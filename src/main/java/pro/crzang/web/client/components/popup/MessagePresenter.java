package pro.crzang.web.client.components.popup;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * Презентер всплывающего сообщения.
 * Created by crzang.
 */
public abstract class MessagePresenter extends PresenterWidget<MessagePresenter.MessageView>{

  public MessagePresenter(EventBus eventBus,
      MessageView view) {
    super(eventBus, view);
  }

  /**
   * Показать сообщение
   * @param message текст сообщения
   */
  abstract public void show(String message);

  interface MessageView extends PopupView {
    void setMessage(String message);
  }
}
