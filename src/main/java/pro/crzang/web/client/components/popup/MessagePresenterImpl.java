package pro.crzang.web.client.components.popup;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Created by crzang.
 */
class MessagePresenterImpl extends MessagePresenter{

  @Inject
  MessagePresenterImpl(EventBus eventBus,
      MessageView view) {
    super(eventBus, view);
  }

  @Override
  public void show(String message) {
    getView().setMessage(message);
    getView().center();
  }
}
