package pro.crzang.web.server.handler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import pro.crzang.web.shared.LoginAction;
import pro.crzang.web.shared.LoginResult;

import javax.servlet.ServletContext;

//
//  Обработка RPC события получения json из файла.
//  Created by crzang.
//

public class LoginHandler
    implements ActionHandler<LoginAction, LoginResult> {
  private final ServletContext servletContext;

  @Inject
  public LoginHandler(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Override
  public LoginResult execute(LoginAction action, ExecutionContext context) throws ActionException {
    LoginResult result = null;
    if (action.getLogin().equals("demo") && action.getLogin().equals("demo")) {
      result=new LoginResult(true,1);
    }
    else {
      result=new LoginResult(false,-1);
    }
    return result;
  }

  @Override
  public Class<LoginAction> getActionType() {
    return null;
  }

  @Override
  public void undo(LoginAction action, LoginResult result, ExecutionContext context) throws ActionException {

  }
}
