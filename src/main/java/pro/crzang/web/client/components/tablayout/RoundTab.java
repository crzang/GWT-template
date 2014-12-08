package pro.crzang.web.client.components.tablayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;

/**
 * Created by crzang.
 */
public class RoundTab extends Composite implements Tab {

  protected interface Style extends CssResource {
    String active();

    String inactive();

    String round1();

    String tab_inner();

    String round4();

    String round2();

    String tab();
  }

  private static RoundTabUiBinder ourUiBinder = GWT.create(RoundTabUiBinder.class);
  @UiField
  Style style;
  @UiField
  Label label;
  private final float priority;
  public RoundTab(TabData tabData) {
    initWidget(ourUiBinder.createAndBindUi(this));
    priority=tabData.getPriority();
    label.setText(tabData.getLabel());
  }

  @Override
  public void activate() {
    removeStyleName(style.inactive());
    addStyleName(style.active());
  }

  @Override
  public void deactivate() {
    removeStyleName(style.active());
    addStyleName(style.inactive());
  }

  @Override
  public float getPriority() {
    return priority;
  }

  @Override
  public String getText() {
    return label.getText();
  }

  @Override
  public void setText(String text) {
    label.setText(text);
  }

  @Override
  public void setTargetHistoryToken(String historyToken) {

  }

  interface RoundTabUiBinder
      extends UiBinder<HTMLPanel, RoundTab> {
  }
}