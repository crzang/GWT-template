package pro.crzang.web.client.event;

import com.google.web.bindery.event.shared.Event;

/**
 * Created by crzang.
 */
public class ContentResizeEvent extends Event<ContentResizeHandler> {
  public ContentResizeEvent(int height) {
  }

  @Override
  public Type<ContentResizeHandler> getAssociatedType() {
    return null;
  }

  @Override
  protected void dispatch(ContentResizeHandler handler) {

  }
}
