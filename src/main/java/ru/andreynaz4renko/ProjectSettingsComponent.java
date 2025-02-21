package ru.andreynaz4renko;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class ProjectSettingsComponent {

  private final JPanel mainPanel;
  private final JBTextField disabledTestsPrefix = new JBTextField();
  private final JBCheckBox checkDisabledTests =
      new JBCheckBox("Проверять префикс @Disabled тестов");

  private final JBTextField beforeAllMethodName = new JBTextField();
  private final JBCheckBox checkBeforeAllMethodName =
      new JBCheckBox("Проверять имя метода @BeforeAll");

  private final JBTextField beforeEachMethodName = new JBTextField();
  private final JBCheckBox checkBeforeEachMethodName =
      new JBCheckBox("Проверять имя метода @BeforeEach");

  private final JBTextField afterAllMethodName = new JBTextField();
  private final JBCheckBox checkAfterAllMethodName =
      new JBCheckBox("Проверять имя метода @AfterAll");

  private final JBTextField afterEachMethodName = new JBTextField();
  private final JBCheckBox checkAfterEachMethodName =
      new JBCheckBox("Проверять имя метода @AfterEach");

  public ProjectSettingsComponent() {
    mainPanel =
        FormBuilder.createFormBuilder()
            .addLabeledComponent(
                new JBLabel("Префикс @Disabled тестов"), disabledTestsPrefix, 1, false)
            .addComponent(checkDisabledTests, 1)
            .addSeparator()
            .addLabeledComponent(
                new JBLabel("Имя метода @BeforeAll"), beforeAllMethodName, 1, false)
            .addComponent(checkBeforeAllMethodName, 1)
            .addLabeledComponent(
                new JBLabel("Имя метода @BeforeEach"), beforeEachMethodName, 1, false)
            .addComponent(checkBeforeEachMethodName, 1)
            .addLabeledComponent(new JBLabel("Имя метода @AfterAll"), afterAllMethodName, 1, false)
            .addComponent(checkAfterAllMethodName, 1)
            .addLabeledComponent(
                new JBLabel("Имя метода @AfterEach"), afterEachMethodName, 1, false)
            .addComponent(checkAfterEachMethodName, 1)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
  }

  public JPanel getPanel() {
    return mainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return disabledTestsPrefix;
  }

  @NotNull
  public String getDisabledTestsPrefix() {
    return disabledTestsPrefix.getText();
  }

  public void setDisabledTestsPrefix(@NotNull String newText) {
    disabledTestsPrefix.setText(newText);
  }

  public boolean getCheckDisabledTests() {
    return checkDisabledTests.isSelected();
  }

  public void setCheckDisabledTests(boolean newStatus) {
    checkDisabledTests.setSelected(newStatus);
  }

  @NotNull
  public String getBeforeAllMethodName() {
    return beforeAllMethodName.getText();
  }

  public void setBeforeAllMethodName(@NotNull String newText) {
    beforeAllMethodName.setText(newText);
  }

  public boolean getCheckBeforeAllMethodName() {
    return checkBeforeAllMethodName.isSelected();
  }

  public void setCheckBeforeAllMethodName(boolean newStatus) {
    checkBeforeAllMethodName.setSelected(newStatus);
  }

  @NotNull
  public String getBeforeEachMethodName() {
    return beforeEachMethodName.getText();
  }

  public void setBeforeEachMethodName(@NotNull String newText) {
    beforeEachMethodName.setText(newText);
  }

  public boolean getCheckBeforeEachMethodName() {
    return checkBeforeEachMethodName.isSelected();
  }

  public void setCheckBeforeEachMethodName(boolean newStatus) {
    checkBeforeEachMethodName.setSelected(newStatus);
  }

  @NotNull
  public String getAfterAllMethodName() {
    return afterAllMethodName.getText();
  }

  public void setAfterAllMethodName(@NotNull String newText) {
    afterAllMethodName.setText(newText);
  }

  public boolean getCheckAfterAllMethodName() {
    return checkAfterAllMethodName.isSelected();
  }

  public void setCheckAfterAllMethodName(boolean newStatus) {
    checkAfterAllMethodName.setSelected(newStatus);
  }

  @NotNull
  public String getAfterEachMethodName() {
    return afterEachMethodName.getText();
  }

  public void setAfterEachMethodName(@NotNull String newText) {
    afterEachMethodName.setText(newText);
  }

  public boolean getCheckAfterEachMethodName() {
    return checkAfterEachMethodName.isSelected();
  }

  public void setCheckAfterEachMethodName(boolean newStatus) {
    checkAfterEachMethodName.setSelected(newStatus);
  }
}
