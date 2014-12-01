package pro.crzang.web.client.system;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Шапка клиента с логотипом и основными кнопками.
 * Created by Alexey on 06.08.2014.
 */
class HeaderView extends ViewWithUiHandlers<HeaderUiHandlers> implements
    HeaderPresenterImpl.IHeaderView {

  private String lineHeight = "25px";
  private double marginLeft = 5;

  private HorizontalPanel mainPanel;

  @Inject
  HeaderView() {
    mainPanel = new HorizontalPanel();
    initWidget(mainPanel);
    mainPanel.setWidth("100%");
    mainPanel.setHeight("60px");
    mainPanel.getElement().getStyle().setBackgroundColor("#3B4248");
    compose();
  }

  /**
   * Компановка формы.
   */
  private void compose() {
    //Логотип
    Image logoImg = new Image();
    logoImg.setUrl("images/logo.png");
    logoImg.setWidth("53px");
    logoImg.setHeight("53px");
    logoImg.getElement().getStyle().setPadding(3, Style.Unit.PX);
    mainPanel.add(logoImg);
    mainPanel.setCellWidth(logoImg, "65px");

    VerticalPanel info = new VerticalPanel();
    info.getElement().getStyle().setColor("white");
    mainPanel.add(info);
    Label headerTitle = new Label("TITLE");
    info.add(headerTitle);
    headerTitle.addStyleName("headerTitle");


    //Панель кнопок
    HorizontalPanel actionPanel = new HorizontalPanel();
    actionPanel.setSpacing(5);
    mainPanel.add(actionPanel);
    mainPanel.setCellHorizontalAlignment(actionPanel,
        HasHorizontalAlignment.ALIGN_RIGHT);
    mainPanel.setCellVerticalAlignment(actionPanel,
        HasVerticalAlignment.ALIGN_BOTTOM);
    actionPanel.getElement().getStyle().setMarginRight(5, Style.Unit.PX);
    actionPanel.getElement().getStyle().setMarginBottom(5, Style.Unit.PX);

    Label exitButton = new Label();
    exitButton.setText("Выход");
    exitButton.addStyleName("newSearch");
    exitButton.getElement().getStyle().setColor("white");
    exitButton.getElement().getStyle().setPaddingLeft(2, Style.Unit.PX);
    FocusPanel focusPanel = new FocusPanel();
    HorizontalPanel hExitPanel = new HorizontalPanel();
    Image imgExit = new Image();
    imgExit.setUrl("images/logout.png");

    hExitPanel.add(imgExit);
    hExitPanel.add(exitButton);
    focusPanel.add(hExitPanel);
    focusPanel.getElement().getStyle().setPaddingLeft(10, Style.Unit.PX);
    focusPanel.getElement().getStyle().setPaddingRight(5, Style.Unit.PX);
    focusPanel.getElement().getStyle().setPaddingTop(5, Style.Unit.PX);
    focusPanel.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        getUiHandlers().logout();
      }
    });
    actionPanel.add(focusPanel);
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    super.setInSlot(slot, content);
  }
}
