package pro.crzang.web.client.model.bean;


import pro.crzang.web.client.model.Bean;

import java.util.List;

/**
 * Created by crzang.
 */
public interface ErrorInfoBean extends Bean {
  void setCaught(Throwable caught);


  String getUrl();


  String getMessage();


  List<String> getStackTrace();

  boolean isError();
}
