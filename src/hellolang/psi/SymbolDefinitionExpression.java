package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import hellolang.lexer.HelloTokenType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SymbolDefinitionExpression extends HelloExpression implements PsiNamedElement {
    public String name() {
        return super.getText();
    }

    public SymbolDefinitionExpression(ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
//        ASTNode oldSymbol = super.getNode().findChildByType(HelloTokenType.SYMBOL_REFERENCE);
        ASTNode newSymbol = new LeafPsiElement(HelloTokenType.SYMBOL, newName);

//        this.getNode().replaceChild(oldSymbol, newSymbol);

        return new SymbolDefinitionExpression(newSymbol);
    }
}
