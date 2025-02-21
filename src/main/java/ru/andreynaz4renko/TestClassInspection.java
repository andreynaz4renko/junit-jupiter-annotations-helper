package ru.andreynaz4renko;

import static ru.andreynaz4renko.Utils.findAnnotation;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestClassInspection extends AbstractBaseJavaLocalInspectionTool {

  private static final Map<Class<?>, String> ANNOTATION_TO_SETTINGS =
      Map.of(
          BeforeAll.class, "checkBeforeAllMethodName",
          BeforeEach.class, "checkBeforeEachMethodName",
          AfterAll.class, "checkAfterAllMethodName",
          AfterEach.class, "checkAfterEachMethodName");

  @Override
  public @NotNull PsiElementVisitor buildVisitor(
      @NotNull ProblemsHolder holder, boolean isOnTheFly) {
    var settings = ProjectSettings.getInstance(holder.getProject());
    var settingsState = settings.getState();

    if (settingsState == null) {
      return new JavaElementVisitor() {};
    }

    return new JavaElementVisitor() {
      @Override
      public void visitMethod(@NotNull PsiMethod method) {
        super.visitMethod(method);
        checkMethodAnnotations(method);
      }

      private void checkMethodAnnotations(PsiMethod method) {
        var methodName = method.getName();
        var annotations = method.getAnnotations();

        for (var entry : ANNOTATION_TO_SETTINGS.entrySet()) {
          if (isCheckEnabled(entry.getValue())) {
            if (checkAnnotation(
                method,
                annotations,
                methodName,
                entry.getKey(),
                getExpectedMethodName(entry.getKey()))) {
              return;
            }
          }
        }
      }

      private boolean isCheckEnabled(String checkFieldName) {
        return switch (checkFieldName) {
          case "checkBeforeAllMethodName" -> settingsState.checkBeforeAllMethodName;
          case "checkBeforeEachMethodName" -> settingsState.checkBeforeEachMethodName;
          case "checkAfterAllMethodName" -> settingsState.checkAfterAllMethodName;
          case "checkAfterEachMethodName" -> settingsState.checkAfterEachMethodName;
          default -> false;
        };
      }

      private String getExpectedMethodName(Class<?> annotationClass) {
        if (annotationClass == BeforeAll.class) return settingsState.beforeAllMethodName;
        if (annotationClass == BeforeEach.class) return settingsState.beforeEachMethodName;
        if (annotationClass == AfterAll.class) return settingsState.afterAllMethodName;
        if (annotationClass == AfterEach.class) return settingsState.afterEachMethodName;
        return "";
      }

      private Boolean checkAnnotation(
          PsiMethod method,
          PsiAnnotation[] annotations,
          String methodName,
          Class<?> annotationClass,
          String expectedName) {
        var annotation = findAnnotation(annotations, annotationClass);
        if (annotation == null) {
          return false;
        }
        if (!expectedName.equals(methodName)) {
          registerProblem(method, annotation, expectedName);
        }
        return true;
      }

      private void registerProblem(
          PsiMethod method, PsiAnnotation annotation, String expectedName) {
        var message =
            String.format(
                "Method with annotation %s should be named \"%s\"",
                annotation.getQualifiedName(), expectedName);
        holder.registerProblem(method, message, new RenameMethodQuickFix(expectedName));
      }
    };
  }

  private record RenameMethodQuickFix(String expectedName) implements LocalQuickFix {

    @NotNull
    @Override
    public String getFamilyName() {
      return "Rename method";
    }

    @NotNull
    @Override
    public String getName() {
      return "Rename method to \"" + expectedName + "\"";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
      var element = descriptor.getPsiElement();
      if (element instanceof PsiNameIdentifierOwner method) {
        method.setName(expectedName);
      }
    }
  }
}
