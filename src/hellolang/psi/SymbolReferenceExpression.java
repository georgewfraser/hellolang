package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import hellolang.lexer.HelloTokenType;
import hellolang.parser.SymbolReference;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SymbolReferenceExpression extends HelloExpression implements PsiNamedElement {
    @Nullable
    public String name() {
        return super.getText();
    }

    public SymbolReferenceExpression(ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
//        ASTNode oldSymbol = super.getNode().findChildByType(HelloTokenType.SYMBOL_REFERENCE);
        ASTNode newSymbol = new LeafPsiElement(HelloTokenType.SYMBOL, newName);

//        this.getNode().replaceChild(oldSymbol, newSymbol);

        return new SymbolReferenceExpression(newSymbol);
    }

    @Override
    public SymbolReference getReference() {
        LetExpression let = Finder.parentDefinition(this, this.name());

        if (let != null && let.symbol() != null)
            return new SymbolReference(this, let.symbol());
        else
            return null;
    }
}
