package pro.crzang.web.client.components.tablayout;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crzang.
 */
class TabLayoutView extends ViewWithUiHandlers<TabLayoutUiHandlers>
    implements TabLayoutPresenterImpl.ITabLayoutView, SelectionHandler<Integer> {

  FlowPanel mainPanel = new FlowPanel();
  HorizontalPanel tabPanel     = new HorizontalPanel();
  SimplePanel     contentPanel = new SimplePanel();
  private final List<Tab> tabList = new ArrayList<Tab>();
  Tab currentActiveTab;

  @Inject
  TabLayoutView() {
    initWidget(mainPanel);
    mainPanel.add(tabPanel);
    mainPanel.add(contentPanel);
  }

  @Override
  public void setHeight(String s) {

  }

  @Override
  public void onSelection(SelectionEvent<Integer> event) {

  }

  @Override
  public Tab addTab(TabData tabData, String historyToken) {
    final Tab newTab = new RoundTab(tabData);
    int beforeIndex;
    for (beforeIndex = 0; beforeIndex < tabList.size(); ++beforeIndex) {
      if (newTab.getPriority() < tabList.get(beforeIndex).getPriority()) {
        break;
      }
    }
    tabPanel.add(newTab.asWidget());
    tabList.add(beforeIndex, newTab);
    newTab.setTargetHistoryToken(historyToken);
    newTab.asWidget().addDomHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        getUiHandlers().onSelectTab(newTab);
      }
    },ClickEvent.getType());
    //setTabVisibility(newTab);
    return newTab;
  }

  @Override
  public void removeTab(Tab tab) {
    tabPanel.getElement().removeChild(tab.asWidget().getElement());
    tabList.remove(tab);
  }

  @Override
  public void removeTabs() {
    for (Tab tab : tabList) {
      tabPanel.getElement().removeChild(tab.asWidget().getElement());
    }
    tabList.clear();
  }

  @Override
  public void setActiveTab(Tab tab) {
    if (currentActiveTab != null) {
      currentActiveTab.deactivate();
    }
    if (tab != null) {
      tab.activate();
    }
    currentActiveTab = tab;
    //getUiHandlers().onSelectTab(tab);
  }

  @Override
  public void changeTab(Tab tab, TabData tabData, String historyToken) {
    tab.setText(tabData.getLabel());
    tab.setTargetHistoryToken(historyToken);
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == TabLayoutPresenter.TYPE_SetTabContent) {
      contentPanel.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}
