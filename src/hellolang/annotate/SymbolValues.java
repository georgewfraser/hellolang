package hellolang.annotate;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import hellolang.psi.Finder;
import hellolang.psi.LetExpression;
import hellolang.psi.SymbolReferenceExpression;
import org.jetbrains.annotations.NotNull;

/**
 * Annotates all expressions with errors, and symbols with their (unevaluated) values
 */
public class SymbolValues implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SymbolReferenceExpression) {
            SymbolReferenceExpression symbol = (SymbolReferenceExpression) element;
            LetExpression definition = Finder.parentDefinition(symbol, symbol.name());

            if (definition != null && definition.value() != null)
                holder.createInfoAnnotation(element, definition.value().getText());
            else
                holder.createErrorAnnotation(element, "Undefined symbol");
        }
    }
}
