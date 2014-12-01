package pro.crzang.web.client.components.popup;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

/**
 * Виджет диалога.
 * Created by crzang.
 */
class DialogWidget extends PopupViewImpl implements DialogPresenter.DialogView {
  final DialogBox popupPanel = new DialogBox();
  private HTMLPanel header = new HTMLPanel("span", "");
  private HTMLPanel content = new HTMLPanel("pre", "");
  private VerticalPanel buttons = new VerticalPanel();//("div", "");

  @Inject
  protected DialogWidget(EventBus eventBus) {
    super(eventBus);
    initWidget(popupPanel);
    FlowPanel contentPanel = new FlowPanel();
    popupPanel.setWidget(contentPanel);
    contentPanel.setHeight("300px");
    contentPanel.setWidth("500px");
    contentPanel.getElement().getStyle().setMargin(15, Style.Unit.PX);
    contentPanel.add(header);
    header.add(new HTMLPanel("Заголовок диалога"));
    contentPanel.add(content);

    contentPanel.add(buttons);
    Button buttonOk = new Button("Ok");
    buttonOk.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    buttons.add(buttonOk);
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == DialogPresenter.DIALOG_CONTENT_SLOT) {
      this.content.clear();
      this.content.add(content);
    }
    else if (slot == DialogPresenter.DIALOG_HEADER_SLOT) {
      this.header.clear();
      this.header.add(content);
    }
    else {
      super.setInSlot(slot, content);
    }
  }

  @Override
  public void center() {
    popupPanel.center();
  }
}
