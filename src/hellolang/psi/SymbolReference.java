package hellolang.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import hellolang.format.ExpressionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is necessary to implement jump-to-symbol when you ctrl+click a symbol
 *
 * It doesn't really do anything, it just wraps the element that we are referring to.
 */
public class SymbolReference extends PsiReferenceBase<SymbolReferenceExpression> {
    private final SymbolReferenceExpression reference;
    private final SymbolDefinitionExpression target;

    public SymbolReference(SymbolReferenceExpression reference, SymbolDefinitionExpression target) {
        // confusingly, the second argument is the text range WITHIN the reference expression
        super(reference, new TextRange(0, reference.getTextLength()));
        this.reference = reference;
        this.target = target;
    }

    @Override
    public PsiElement handleElementRename(String newName) {
        SymbolReferenceExpression replacement = new ExpressionFactory(target).createSymbolReference(newName);

        return reference.replace(replacement);
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
