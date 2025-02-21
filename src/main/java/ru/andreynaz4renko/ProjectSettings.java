package ru.andreynaz4renko;

/*
 * Supports storing the application settings in a persistent way.
 * The {@link com.intellij.openapi.components.State State} and {@link Storage}
 * annotations define the name of the data and the filename where these persistent
 * application settings are stored.
 */

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

@State(name = "ru.andreynaz4renko.ProjectSettings", storages = @Storage("AnnotationHelperPluginSettings.xml"))
final class ProjectSettings implements PersistentStateComponent<ProjectSettings.State> {

  static class State {
    @NonNls public String disabledTestsPrefix = "[Disabled]";
    public boolean checkDisabledTestsPrefix = true;

    public String beforeAllMethodName = "beforeAll";
    public boolean checkBeforeAllMethodName = true;

    public String beforeEachMethodName = "beforeEach";
    public boolean checkBeforeEachMethodName = true;

    public String afterAllMethodName = "afterAll";
    public boolean checkAfterAllMethodName = true;

    public String afterEachMethodName = "afterEach";
    public boolean checkAfterEachMethodName = true;
  }

  private State state = new State();

  static ProjectSettings getInstance(Project project) {
    return project.getService(ProjectSettings.class);
  }

  @Override
  public State getState() {
    return state;
  }

  @Override
  public void loadState(@NotNull State state) {
    this.state = state;
  }
}
