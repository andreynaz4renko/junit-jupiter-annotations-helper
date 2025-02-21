package ru.andreynaz4renko;

import com.intellij.psi.PsiAnnotation;
import java.util.Arrays;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

public abstract class Utils {

  public static @Nullable PsiAnnotation findAnnotation(
      PsiAnnotation[] annotations, Class<?> annotationClass) {
    return Arrays.stream(annotations)
        .filter(
            annotation -> Objects.equals(annotation.getQualifiedName(), annotationClass.getName()))
        .findFirst()
        .orElse(null);
  }
}
