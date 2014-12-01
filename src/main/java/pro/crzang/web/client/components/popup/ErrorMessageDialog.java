package pro.crzang.web.client.components.popup;

import pro.crzang.web.client.model.bean.ErrorInfoBean;

/**
 * Диалог отображающий ошибку и стектрейс.
 * Created by crzang.
 */
public interface ErrorMessageDialog {
  /**
   * Показать диалог.
   * @param errorInfoBean описание ошибки.
   */
  void show(ErrorInfoBean errorInfoBean);
}
