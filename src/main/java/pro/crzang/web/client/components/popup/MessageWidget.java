package pro.crzang.web.client.components.popup;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

/**
 * Виджет всплывающего сообщения.
 * Created by crzang.
 */
class MessageWidget extends PopupViewImpl implements MessagePresenter.MessageView {
  final PopupPanel imagePopup = new PopupPanel(true);

  /**
   * The {@link com.gwtplatform.mvp.client.PopupViewImpl} class uses the {@link com.google.web.bindery.event.shared.EventBus} to listen to
   * {@link NavigationEvent} in order to automatically close when this event is
   * fired, if desired. See
   * {@link #setAutoHideOnNavigationEventEnabled(boolean)} for details.
   *
   * @param eventBus The {@link com.google.web.bindery.event.shared.EventBus}.
   */
  @Inject
  protected MessageWidget(EventBus eventBus) {
    super(eventBus);
    imagePopup.setHeight("30px");
    imagePopup.getElement().getStyle().setBackgroundColor("#3B4248");
    imagePopup.setAutoHideOnHistoryEventsEnabled(true);
    imagePopup.setAutoHideEnabled(true);
    imagePopup.setAnimationEnabled(true);
    initWidget(imagePopup);
  }

  @Override
  public void center() {
    asPopupPanel().center();
    Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand() {
      @Override
      public boolean execute() {
        hide();
        return false;
      }
    }, 2000);
  }

  @Override
  public void setMessage(String str) {
    HTML message = new HTML(str);
    message.addStyleName("headerTitle");
    message.getElement().getStyle().setPadding(15, Style.Unit.PX);
    imagePopup.setWidget(message);
  }
}
