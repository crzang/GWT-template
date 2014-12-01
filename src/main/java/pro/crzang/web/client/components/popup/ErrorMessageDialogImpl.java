package pro.crzang.web.client.components.popup;

import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import pro.crzang.web.client.model.bean.ErrorInfoBean;

/**
 * Created by crzang.
 */
class ErrorMessageDialogImpl implements ErrorMessageDialog {

  @Inject
  private Provider<DialogPresenter> dialogPresenterProvider;

  @Inject
  ErrorMessageDialogImpl() {
  }

  @Override
  public void show(ErrorInfoBean errorInfoBean) {
    FlowPanel panel = new FlowPanel();
    panel.add(new HTMLPanel(errorInfoBean.getMessage()));
    DisclosurePanel disclosurePanel = new DisclosurePanel("Детали");
    ScrollPanel scrollPanel = new ScrollPanel();
    disclosurePanel.add(scrollPanel);
    FlowPanel stackPanel = new FlowPanel();
    stackPanel.setHeight("130px");
    stackPanel.setWidth("500px");
    scrollPanel.add(stackPanel);
    for (String s : errorInfoBean.getStackTrace()) {
      stackPanel.add(new HTMLPanel(s));
    }
    panel.add(disclosurePanel);
    dialogPresenterProvider.get().show("Ошибка получения данных",
        panel);
  }
}
