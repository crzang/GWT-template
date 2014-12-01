package pro.crzang.web.client.components.popup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

/**
 * Виджет диалога авторизации.
 * Created by crzang.
 */
class LoginView extends PopupViewImpl implements LoginPresenterImpl.ILoginView {

    private final TextBox loginBox;
    private final PasswordTextBox passwordTextBox;
    private final Button login;
    private LoginUiHandlers uiHandlers;
    private FlowPanel mainPanel = new FlowPanel();
    private PopupPanel popupPanel = new PopupPanel();
    private final Label wrongPasss;

    @Inject
    public LoginView(EventBus eventBus) {
        super(eventBus);
        initWidget(popupPanel);
        String width = "350px";
        //mainPanel.setWidth(width);
        mainPanel.add(new Label("Имя пользователя:"));
        loginBox = new TextBox();
        loginBox.setWidth(width);
        mainPanel.add(loginBox);
        mainPanel.add(new Label("Пароль:"));
        passwordTextBox = new PasswordTextBox();
        passwordTextBox.setWidth(width);
        mainPanel.add(passwordTextBox);
        wrongPasss = new Label("Не правильно введен имя пользователя или " +
                "пароль.");
        wrongPasss.getElement().getStyle().setColor("red");
        wrongPasss.setVisible(false);
        mainPanel.add(wrongPasss);
        login = new Button("Войти");
        HTMLPanel buttonsPanel = new HTMLPanel("");
        buttonsPanel.add(login);
        mainPanel.add(buttonsPanel);
        login.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                uiHandlers.onLogin(loginBox.getText(), passwordTextBox.getText());
            }
        });
        popupPanel.setModal(true);
        popupPanel.setGlassEnabled(true);
        popupPanel.setAnimationEnabled(true);
        popupPanel.setAutoHideEnabled(false);
        popupPanel.add(mainPanel);
        popupPanel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
                popupPanel
                        .setPopupPosition(Window.getClientWidth() / 2 - offsetWidth / 2,
                                Window.getClientHeight() / 2 - offsetHeight / 2);
            }
        });

        passwordTextBox.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent keyDownEvent) {
                if (keyDownEvent.getNativeKeyCode() == 13) {
                    login.click();
                }
            }
        });
    }

    @Override
    public void displayError() {
        wrongPasss.setVisible(true);
    }

    @Override
    public void setUiHandlers(LoginUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}
