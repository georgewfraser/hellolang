package hellolang.parser;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import hellolang.psi.SymbolDefinitionExpression;
import hellolang.psi.SymbolReferenceExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is necessary to implement jump-to-symbol when you ctrl+click a symbol
 *
 * It doesn't really do anything, it just wraps the element that we are referring to.
 */
public class SymbolReference extends PsiReferenceBase<SymbolReferenceExpression> {
    private final SymbolDefinitionExpression target;

    public SymbolReference(SymbolReferenceExpression reference, SymbolDefinitionExpression target) {
        // confusingly, the second argument is the text range WITHIN the reference expression
        super(reference, new TextRange(0, reference.getTextLength()));
        this.target = target;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return target;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }
}
