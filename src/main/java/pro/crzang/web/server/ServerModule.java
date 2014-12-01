package pro.crzang.web.server;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import pro.crzang.web.server.handler.JsonFileHandler;
import pro.crzang.web.server.handler.LoginHandler;
import pro.crzang.web.shared.JsonFileAction;
import pro.crzang.web.shared.LoginAction;

// **
//  * Модуль регистрации RPC.
//    * Created by crzang.
//  *
// TODO Это не регистрация RPC.
public class ServerModule extends HandlerModule {

  @Override
  protected void configureHandlers() {
    bindHandler(LoginAction.class, LoginHandler.class);
    bindHandler(JsonFileAction.class, JsonFileHandler.class);
  }
}
