package pro.crzang.web.client.model.bean;

import com.google.inject.Inject;
import pro.crzang.web.client.model.ObjectDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crzang.
 */
class ErrorInfoJ extends ObjectDataSource implements ErrorInfoBean {

  @Inject
  ErrorInfoJ() {
    super();
  }


  @Override
  public void setCaught(Throwable caught) {
    setStringField("message",caught.getLocalizedMessage());
    List<String> stackTrace=new ArrayList<String>();
    for(StackTraceElement s:caught.getStackTrace()){
      stackTrace.add(s.toString());
    }
    setListField("stackTrace",stackTrace,String.class);
  }

  @Override
  public String getUrl() {
    return getStringField("url");
  }

  @Override
  public String getMessage() {
    return getStringField("message");
  }

  @Override
  public List<String> getStackTrace() {
    return getListField("stackTrace",String.class);
  }

  @Override
  public boolean isError() {
    return getBooleanField("error");
  }

}
