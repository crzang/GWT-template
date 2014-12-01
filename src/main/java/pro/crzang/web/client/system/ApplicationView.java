package pro.crzang.web.client.system;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * Корневой виджет.
 * Created by crzang.
 */
class ApplicationView extends ViewImpl implements
        ApplicationPresenterImpl.IApplicationView {

    DockPanel mainPanel = new DockPanel();

    @Inject
    ApplicationView() {
        initWidget(mainPanel);
        mainPanel.setWidth("100%");
        mainPanel.setHeight(String.valueOf(Window.getClientHeight()) + "px");
        mainPanel.setSpacing(4);

        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                mainPanel.setHeight(String.valueOf(event.getHeight()) + "px");
                mainPanel.setWidth(String.valueOf(event.getWidth()) + "px");
            }
        });
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.SET_MAIN_CONTENT) {
            mainPanel.add(content, DockPanel.CENTER);
            mainPanel.setCellHeight(content, "100%");
        } else if (slot == ApplicationPresenter.SLOT_HEADER_PRESENTER) {
            /**--------------------------- Шапка клиента ---------------------------*/
            mainPanel.add(content, DockPanel.NORTH);
        } else {
            super.setInSlot(slot, content);
        }
    }
}
