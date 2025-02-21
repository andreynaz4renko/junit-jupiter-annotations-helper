package ru.andreynaz4renko;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import java.util.Objects;
import javax.swing.*;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

final class ProjectSettingsConfigurable implements Configurable {

  private ProjectSettingsComponent settingsComponent;
  private final Project project;

  public ProjectSettingsConfigurable(Project project) {
    this.project = project;
  }

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "SDK: Application Settings Example";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return settingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    settingsComponent = new ProjectSettingsComponent();
    return settingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    var state = Objects.requireNonNull(ProjectSettings.getInstance(project).getState());
    return !settingsComponent.getDisabledTestsPrefix().equals(state.disabledTestsPrefix)
        || settingsComponent.getCheckDisabledTests() != state.checkDisabledTestsPrefix
        || !settingsComponent.getBeforeAllMethodName().equals(state.beforeAllMethodName)
        || settingsComponent.getCheckBeforeAllMethodName() != state.checkBeforeAllMethodName
        || !settingsComponent.getBeforeEachMethodName().equals(state.beforeEachMethodName)
        || settingsComponent.getCheckBeforeEachMethodName() != state.checkBeforeEachMethodName
        || !settingsComponent.getAfterAllMethodName().equals(state.afterAllMethodName)
        || settingsComponent.getCheckAfterAllMethodName() != state.checkAfterAllMethodName
        || !settingsComponent.getAfterEachMethodName().equals(state.afterEachMethodName)
        || settingsComponent.getCheckAfterEachMethodName() != state.checkAfterEachMethodName;
  }

  @Override
  public void apply() {
    var state = Objects.requireNonNull(ProjectSettings.getInstance(project).getState());
    state.disabledTestsPrefix = settingsComponent.getDisabledTestsPrefix();
    state.checkDisabledTestsPrefix = settingsComponent.getCheckDisabledTests();
    state.beforeAllMethodName = settingsComponent.getBeforeAllMethodName();
    state.checkBeforeAllMethodName = settingsComponent.getCheckBeforeAllMethodName();
    state.beforeEachMethodName = settingsComponent.getBeforeEachMethodName();
    state.checkBeforeEachMethodName = settingsComponent.getCheckBeforeEachMethodName();
    state.afterAllMethodName = settingsComponent.getAfterAllMethodName();
    state.checkAfterAllMethodName = settingsComponent.getCheckAfterAllMethodName();
    state.afterEachMethodName = settingsComponent.getAfterEachMethodName();
    state.checkAfterEachMethodName = settingsComponent.getCheckAfterEachMethodName();
  }

  @Override
  public void reset() {
    var state = Objects.requireNonNull(ProjectSettings.getInstance(project).getState());
    settingsComponent.setDisabledTestsPrefix(state.disabledTestsPrefix);
    settingsComponent.setCheckDisabledTests(state.checkDisabledTestsPrefix);
    settingsComponent.setBeforeAllMethodName(state.beforeAllMethodName);
    settingsComponent.setCheckBeforeAllMethodName(state.checkBeforeAllMethodName);
    settingsComponent.setBeforeEachMethodName(state.beforeEachMethodName);
    settingsComponent.setCheckBeforeEachMethodName(state.checkBeforeEachMethodName);
    settingsComponent.setAfterAllMethodName(state.afterAllMethodName);
    settingsComponent.setCheckAfterAllMethodName(state.checkAfterAllMethodName);
    settingsComponent.setAfterEachMethodName(state.afterEachMethodName);
    settingsComponent.setCheckAfterEachMethodName(state.checkAfterEachMethodName);
  }

  @Override
  public void disposeUIResources() {
    settingsComponent = null;
  }
}
