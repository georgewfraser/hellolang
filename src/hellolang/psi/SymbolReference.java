package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import hellolang.lexer.HelloTokenType;
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

    @Override
    public PsiElement handleElementRename(String newName) {
        ASTNode node = target.getNode();
        ASTNode oldSymbol = node.findChildByType(HelloTokenType.SYMBOL);
        ASTNode newSymbol = new LeafPsiElement(HelloTokenType.SYMBOL, newName);
        target.getNode().replaceChild(oldSymbol, newSymbol);

        return target;
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
