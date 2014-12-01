package pro.crzang.web.client.components.tablayout;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Created by crzang.
 */
class TabLayoutView extends ViewWithUiHandlers<TabLayoutUiHandlers>
    implements TabLayoutPresenterImpl.ITabLayoutView, SelectionHandler<Integer> {

  private TabLayoutPanel tabPanel;


  @Inject
  TabLayoutView() {
    tabPanel = new TabLayoutPanel(35, Style.Unit.PX);
    tabPanel.setAnimationDuration(250);
    tabPanel.setWidth("100%");
    tabPanel.setHeight("100%");

    tabPanel.addSelectionHandler(this);
    initWidget(tabPanel);
  }

  @Override
  public void setHeight(String s) {
    tabPanel.setHeight(s);
  }


  @Override
  public void setInSlot(Object slot, IsWidget content) {
      super.setInSlot(slot, content);
  }

  @Override
  public void onSelection(SelectionEvent<Integer> event) {

  }
}
