package pro.crzang.web.client.system;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Главная компановка всего приложения.
 * Created by Alexey on 29.07.2014.
 */
class SystemView extends ViewWithUiHandlers<SystemUiHandlesrs>
    implements
    SystemPresenterImpl.ISystemView {

  private final SimplePanel tabLayout = new SimplePanel();
  HTML userFolders;

  private SplitLayoutPanel mainSplit;

  @Inject
  SystemView() {
    mainSplit = new SplitLayoutPanel() {
      @Override
      public void onResize() {
        super.onResize();
        int height = mainSplit.getWidgetContainerElement(tabLayout)
            .getOffsetHeight();
        if (height > 0) {
          tabLayout.getWidget().setHeight(height + "px");
          getUiHandlers().onResize(height);
        }
      }
    };
    mainSplit.setHeight("100%");

    userFolders = new HTML("Папки пользователя");
    mainSplit.addSouth(userFolders, 0);
    mainSplit.add(tabLayout);

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(ResizeEvent resizeEvent) {
        mainSplit.onResize();
      }
    });
    initWidget(mainSplit);
  }

  @Override
  public void openUserFolders() {
    if (mainSplit.getWidgetSize(userFolders) > 0) {
      mainSplit.setWidgetSize(userFolders, 0);
    } else {
      mainSplit.setWidgetSize(userFolders, 400);
    }
    mainSplit.animate(500);

    Timer t = new Timer() {
      @Override
      public void run() {
        mainSplit.onResize();
      }
    };
    t.schedule(500);
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == SystemPresenter.SET_SYSTEM_CONTENT) {
      tabLayout.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }

    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        mainSplit.onResize();
      }
    });
  }

}