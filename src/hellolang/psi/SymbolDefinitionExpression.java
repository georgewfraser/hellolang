package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import hellolang.lexer.HelloTokenType;
import hellolang.parser.HelloExpressionType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SymbolDefinitionExpression extends HelloExpression implements PsiNamedElement {

    public SymbolDefinitionExpression(ASTNode node) {
        super(node);
    }

    public String name() {
        return super.getText();
    }

    @Override
    public HelloExpressionType.Type type() {
        return HelloExpressionType.Type.SYMBOL_DEFINITION;
    }

    // Together with SymbolReference.handleElementRename, getName() and setName() implement rename refactoring

    @Override
    public String getName() {
        return super.getText();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
        ASTNode node = super.getNode();
        ASTNode oldSymbol = node.findChildByType(HelloTokenType.SYMBOL);
        ASTNode newSymbol = new LeafPsiElement(HelloTokenType.SYMBOL, newName);
        this.getNode().replaceChild(oldSymbol, newSymbol);

        return this;
    }
}
