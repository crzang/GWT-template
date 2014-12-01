package pro.crzang.web.client.system;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Корневой презентер.
 * В слоты добавляется SystemView и HeaderView
 * Created by crzang.
 */
class ApplicationPresenterImpl extends ApplicationPresenter {
  @Inject
  private Provider<HeaderPresenter> headerPresenterProvider;

  @Inject
  ApplicationPresenterImpl(EventBus eventBus,
      IApplicationView view, ApplicationProxy proxy) {
    super(eventBus, view, proxy, RevealType.Root);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    setInSlot(SLOT_HEADER_PRESENTER, headerPresenterProvider.get());
  }

}
