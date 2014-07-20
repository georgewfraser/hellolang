package hellolang.annotate;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import hellolang.psi.HelloExpression;
import org.jetbrains.annotations.NotNull;

/**
 * Each HelloExpression defines an annotateSyntaxErrors(...) method. This annotator will call that method
 * on every HelloExpression in the syntax tree, giving it the opportunity to report syntax errors.
 */
public class SyntaxErrors implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof HelloExpression)
            ((HelloExpression) element).annotateSyntaxErrors(holder);
    }
}
