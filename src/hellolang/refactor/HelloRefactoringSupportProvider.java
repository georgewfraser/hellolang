package hellolang.refactor;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import hellolang.psi.SymbolDefinitionExpression;
import org.jetbrains.annotations.NotNull;

/**
 * This file lets IntelliJ know that in-place variable renaming is available for some symbols
 */
public class HelloRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        // We don't need to worry about SymbolReferenceExpression
        // IntelliJ will always resolve() the symbol before calling this function
        return element instanceof SymbolDefinitionExpression;
    }
}
