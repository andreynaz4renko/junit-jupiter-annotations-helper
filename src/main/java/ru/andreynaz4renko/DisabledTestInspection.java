package ru.andreynaz4renko;

import static ru.andreynaz4renko.Utils.findAnnotation;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DisabledTestInspection extends AbstractBaseJavaLocalInspectionTool {

  @Override
  public @NotNull PsiElementVisitor buildVisitor(
      @NotNull final ProblemsHolder holder, boolean isOnTheFly) {
    var project = holder.getProject();
    var settings = ProjectSettings.getInstance(project);
    var settingsState = settings.getState();

    if (settingsState == null || !settingsState.checkDisabledTestsPrefix) {
      return new JavaElementVisitor() {};
    }

    return new JavaElementVisitor() {

      @Override
      public void visitMethod(@NotNull PsiMethod method) {
        super.visitMethod(method);

        var annotations = method.getAnnotations();
        var testAnnotation = findAnnotation(annotations, Test.class);
        var disabledAnnotation = findAnnotation(annotations, Disabled.class);
        var displayNameAnnotation = findAnnotation(annotations, DisplayName.class);

        if (testAnnotation == null || displayNameAnnotation == null) {
          return;
        }

        var displayNameValue = displayNameAnnotation.findAttributeValue("value");
        if (displayNameValue == null) {
          return;
        }

        var displayNameText = displayNameValue.getText();
        if (displayNameText == null) {
          return;
        }

        var displayNameExpectedPrefix = settingsState.disabledTestsPrefix;
        var displayNameStartsWithPrefix =
            displayNameText.trim().substring(1).startsWith(displayNameExpectedPrefix);

        if (disabledAnnotation == null) {
          if (displayNameStartsWithPrefix) {
            registerProblem(holder, displayNameAnnotation, displayNameExpectedPrefix, false);
          }
        } else if (!displayNameStartsWithPrefix) {
          registerProblem(holder, displayNameAnnotation, displayNameExpectedPrefix, true);
        }
      }

      private void registerProblem(
          ProblemsHolder holder, PsiAnnotation annotation, String prefix, boolean isDisabled) {
        var message =
            isDisabled
                ? "DisplayName should start with \"" + prefix + "\" when test is disabled"
                : "DisplayName should not start with \"" + prefix + "\" when test is enabled";
        holder.registerProblem(
            annotation, message, new FixDisplayNamePrefixQuickFix(prefix, isDisabled));
      }
    };
  }

  private record FixDisplayNamePrefixQuickFix(String prefix, boolean shouldHavePrefix)
      implements LocalQuickFix {

    @NotNull
    @Override
    public String getFamilyName() {
      return "Fix DisplayName prefix";
    }

    @NotNull
    @Override
    public String getName() {
      return shouldHavePrefix
          ? "Add \"" + prefix + "\" prefix to DisplayName"
          : "Remove \"" + prefix + "\" prefix from DisplayName";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
      var element = descriptor.getPsiElement();
      if (element instanceof PsiAnnotation annotation) {
        var value = annotation.findAttributeValue("value");
        if (value == null) return;

        var currentText = value.getText();
        var newText = "";

        if (shouldHavePrefix) {
          newText = "\"" + prefix + currentText.substring(1, currentText.length() - 1) + "\"";
        } else {
          var textWithoutPrefix =
              currentText.substring(prefix.length() + 1, currentText.length() - 1);
          newText = "\"" + textWithoutPrefix + "\"";
        }

        annotation.setDeclaredAttributeValue(
            "value",
            JavaPsiFacade.getElementFactory(project).createExpressionFromText(newText, null));
      }
    }
  }
}
