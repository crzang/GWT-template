package pro.crzang.web.client.components.tablayout;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.gwtplatform.mvp.client.PresenterWidget;

/**
 * Created by Alexey on 14.08.2014.
 */
public class TabHeader extends Composite {
  private final Integer docId;
  FlowPanel mainPanel;
  final Image closeImg;
  TabLayoutPanel tabPanel;
  ClickHandler closeHandler;

  private PopupPanel popupPanel;
  private PresenterWidget<?> presenter;
  private final Label headerLabel;

  public TabHeader(String name, TabLayoutPanel tabPanel, Integer docId) {
    this.docId = docId;
    this.tabPanel = tabPanel;
    mainPanel = new FlowPanel();
    mainPanel.setStyleName("gwt-TabLayoutPanelTabInner");
    initWidget(mainPanel);
    //        Label l = new Label(name.substring(0, 13));
    headerLabel = new Label(name);
    headerLabel.getElement().getStyle().setFloat(Style.Float.LEFT);
    mainPanel.add(headerLabel);

    closeImg = new Image();
    //    closeImg.setUrl("images/close.png");
    closeImg.setUrl("images/closered.png");
    closeImg.setSize("15px", "15px");
    closeImg.getElement().getStyle().setPaddingLeft(5, Style.Unit.PX);
    //    closeImg.getElement().getStyle().setOpacity(0.2);
    closeImg.getElement().getStyle().setOpacity(0.5);
    closeImg.getElement().getStyle().setCursor(Style.Cursor.POINTER);

    closeImg.addMouseOverHandler(new MouseOverHandler() {
      @Override
      public void onMouseOver(MouseOverEvent event) {
        closeImg.getElement().getStyle().setOpacity(1);
      }
    });
    closeImg.addMouseOutHandler(new MouseOutHandler() {
      @Override
      public void onMouseOut(MouseOutEvent event) {
        closeImg.getElement().getStyle().setOpacity(0.5);
      }
    });

    mainPanel.add(closeImg);

    sinkEvents(Event.ONCONTEXTMENU);

    initContextMenu();
  }

  private void initContextMenu() {
    popupPanel = new PopupPanel(true);

    Command closeCommand = new Command() {
      public void execute() {

        if (closeHandler != null) {
          closeHandler.onClick(null);
        }
        popupPanel.hide();
      }
    };

    Command closeAllCommand = new Command() {
      public void execute() {


        //  int tabCount = tabPanel.getWidgetCount();
        if (tabPanel.getWidgetCount() > 2) {
          do {
            tabPanel.remove(tabPanel.getWidgetCount() - 1);
          } while (tabPanel.getWidgetCount() > 2);

        }
        popupPanel.hide();
      }
    };

    Command closeAllExceptCommand = new Command() {
      public void execute() {

        int selected;
        int delIndex = 1;
        if (tabPanel.getWidgetCount() > 3) {
          do {
            selected = tabPanel.getSelectedIndex();
            if (selected != (tabPanel.getWidgetCount() - delIndex)) {
              tabPanel.remove(tabPanel.getWidgetCount() - delIndex);
            }
            else {
              delIndex++;
            }

          } while (tabPanel.getWidgetCount() > 3);

        }
        popupPanel.hide();
      }
    };


    MenuBar popupMenuBar = new MenuBar(true);
    MenuItem closeItem = new MenuItem("Закрыть", true, closeCommand);
    MenuItem closeAllItem = new MenuItem("Закрыть все", true, closeAllCommand);
    MenuItem closeAllExceptItem = new MenuItem("Закрыть все, кроме выделенного", true, closeAllExceptCommand);

    popupMenuBar.addItem(closeItem);
    popupMenuBar.addItem(closeAllItem);
    popupMenuBar.addItem(closeAllExceptItem);

    popupMenuBar.setVisible(true);
    popupPanel.add(popupMenuBar);
  }

  public void onBrowserEvent(Event event) {

    event.cancelBubble(true);//This will stop the event from being propagated
    event.preventDefault();
    switch (DOM.eventGetType(event)) {


      case Event.ONCONTEXTMENU:
        int x = event.getClientX();
        int y = event.getClientY();

        popupPanel.setPopupPosition(x, y);
        popupPanel.show();

        break;

      default:
        break; // Do nothing
    }//end switch
  }


  public void addClickHandler(ClickHandler handler) {
    closeHandler = handler;
    closeImg.addClickHandler(handler);


  }

  public int getDocId() {
    return docId;
  }

  public void setPresenter(PresenterWidget<?> presenter) {
    this.presenter = presenter;
  }

  public PresenterWidget<?> getPresenter() {
    return presenter;
  }

  public void setTabText(String tabName) {
    headerLabel.setText(tabName);
  }
}
