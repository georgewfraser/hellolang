package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import hellolang.format.ExpressionFactory;
import hellolang.parser.HelloExpressionType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    // When rename isn't done in place (a popup menu asks you for the name), this method is called to rename the symbol.
    // This occurs when renaming across files.
    // It can be forced by setting HelloRefactoringSupportProvider.isInplaceRenameAvailable() to return false
    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
        SymbolDefinitionExpression replacement = new ExpressionFactory(this).createSymbolDefinition(newName);

        return this.replace(replacement);
    }

    @NotNull
    @Override
    public SearchScope getUseScope() {
        LetExpression let = PsiTreeUtil.getParentOfType(this, LetExpression.class);

        if (let == null)
            return LocalSearchScope.EMPTY;

        // 'Scope' is a bit of a misnomer here: we are allowed to return a LARGER section than the actual
        // scope (in the computer science sense of the word). IntelliJ will still check whether getReference()
        // resolves to this symbol. The purpose of this scope is just to limit the search area so that the
        // instant-rename appears quickly
        return new LocalSearchScope(let);
    }

    // When rename is done in-place (with the red text background, and all the variables updating instantly),
    // the symbol definition is treated as a reference to itself and handleElementRename() is used to update the
    // name, bypassing setName() above
    @Override
    public PsiReference getReference() {
        return new PsiReferenceBase<SymbolDefinitionExpression>(this, new TextRange(0, this.getTextLength())) {

            @Nullable
            @Override
            public PsiElement resolve() {
                return SymbolDefinitionExpression.this;
            }

            @NotNull
            @Override
            public Object[] getVariants() {
                return new Object[0];
            }

            @Override
            public PsiElement handleElementRename(String newName) {
                ExpressionFactory factory = new ExpressionFactory(SymbolDefinitionExpression.this);
                SymbolDefinitionExpression replacement = factory.createSymbolDefinition(newName);

                return SymbolDefinitionExpression.this.replace(replacement);
            }
        };
    }
}
