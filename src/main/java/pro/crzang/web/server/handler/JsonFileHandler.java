package pro.crzang.web.server.handler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.apache.commons.io.IOUtils;
import pro.crzang.web.shared.JsonFileAction;
import pro.crzang.web.shared.JsonFileResult;

import javax.servlet.ServletContext;
import java.io.InputStream;

 //
 //  Обработка RPC события получения json из файла.
 //  Created by crzang.
 //

public class JsonFileHandler
    implements ActionHandler<JsonFileAction, JsonFileResult> {
  private final ServletContext servletContext;

  @Inject
  public JsonFileHandler(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Override
  public JsonFileResult execute(JsonFileAction action, ExecutionContext context)
      throws ActionException {
    String fileName = action.getFileName();
    if (!fileName.endsWith(".json")) {
      fileName += ".json";
    }
    String resultJson = "";
    try {
      InputStream in = servletContext.getResourceAsStream("/WEB-INF/" + fileName);
      resultJson = IOUtils.toString(in, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new JsonFileResult(resultJson);
  }

  @Override
  public Class<JsonFileAction> getActionType() {
    return JsonFileAction.class;
  }

  @Override
  public void undo(JsonFileAction action, JsonFileResult result,
                   ExecutionContext context) throws ActionException {

  }

}
