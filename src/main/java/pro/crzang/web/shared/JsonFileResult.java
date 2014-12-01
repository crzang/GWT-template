package pro.crzang.web.shared;

import com.gwtplatform.dispatch.shared.Result;

// Результат запроса. Json из файла.
public class JsonFileResult implements Result {

  private String jsonValue;

  public JsonFileResult(String jsonValue) {
    this.jsonValue = jsonValue;
  }

  @SuppressWarnings("unused")
  public JsonFileResult() {
  }

  public String getJsonValue() {
    return jsonValue;
  }
}
