package pro.crzang.web.shared;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * Запрос json файла.
 * Created by crzang.
 */
public class JsonFileAction extends UnsecuredActionImpl<JsonFileResult> {

  private String fileName;

  public JsonFileAction(String fileName) {
    this.fileName = fileName;
  }

  @SuppressWarnings("unused")
  public JsonFileAction() {
  }

  public String getFileName() {
    return fileName;
  }
}
