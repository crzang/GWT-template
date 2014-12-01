package pro.crzang.web.client.rest;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Command;
import com.google.inject.Inject;
import pro.crzang.web.client.model.ObjectDataSource;
import pro.crzang.web.client.model.bean.JsonFile;

/**
 * @author by crzang.
 */
class JsonFileProviderImpl extends AbstractRestProviderImpl<JsonFile>
    implements JsonFileProvider {
  @Inject
  public JsonFileProviderImpl() {
    super(true);
  }

  @Override
  public void registerEntryPoint(String json, Command command) {
    if(json!=null) {
      ObjectDataSource jsonValue = (ObjectDataSource) new ObjectDataSource().setValue(JSONParser
          .parseStrict(json));
      restProviderProvider.get().setEntryPoint(jsonValue.getStringField
          ("entryPoint"));
    }
    else{
      restProviderProvider.get().setEntryPoint(UriConstants.ENTRY_POINT);
    }
    command.execute();
  }
}
