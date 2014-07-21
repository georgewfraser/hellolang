package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import hellolang.format.ExpressionFactory;
import hellolang.parser.HelloExpressionType;
import org.jetbrains.annotations.NotNull;
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
    public PsiReference getReference() {
        LetExpression let = Finder.parentDefinition(this, this.name());

        if (let != null && let.symbol() != null) {
            final SymbolDefinitionExpression target = let.symbol();

            return new PsiReferenceBase<SymbolReferenceExpression>(this, new TextRange(0, this.getTextLength())) {
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

                @Override
                public PsiElement handleElementRename(String newName) {
                    ExpressionFactory factory = new ExpressionFactory(SymbolReferenceExpression.this);
                    SymbolReferenceExpression replacement = factory.createSymbolReference(newName);

                    return SymbolReferenceExpression.this.replace(replacement);
                }
            };
        }
        else
            return null;
    }
}
