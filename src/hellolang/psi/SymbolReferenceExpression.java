package hellolang.psi;

import com.intellij.lang.ASTNode;
import hellolang.parser.HelloExpressionType;
import org.jetbrains.annotations.Nullable;

public class SymbolReferenceExpression extends HelloExpression {

    public SymbolReferenceExpression(ASTNode node) {
        super(node);
    }

    @Nullable
    public String name() {
        return super.getText();
    }

    @Override
    public HelloExpressionType.Type type() {
        return HelloExpressionType.Type.SYMBOL_REFERENCE;
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
