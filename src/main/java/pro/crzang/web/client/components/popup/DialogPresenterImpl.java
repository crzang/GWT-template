package pro.crzang.web.client.components.popup;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Created by crzang.
 */
class DialogPresenterImpl extends DialogPresenter {

  @Inject
  public DialogPresenterImpl(EventBus eventBus,
      DialogView view) {
    super(eventBus, view);
  }

  @Override
  public void show(String header, String message) {
    show(header, new HTMLPanel(message));
  }

  @Override
  public void show(String header, Widget widget) {
    getView().setInSlot(DIALOG_HEADER_SLOT, new HTMLPanel(header));
    getView().setInSlot(DIALOG_CONTENT_SLOT, widget);
    getView().center();
  }

}
