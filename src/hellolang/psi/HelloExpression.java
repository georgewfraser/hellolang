package hellolang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.AnnotationHolder;
import hellolang.parser.HelloExpressionType;
import org.jetbrains.annotations.NotNull;

public abstract class HelloExpression extends ASTWrapperPsiElement {
    public HelloExpression(@NotNull ASTNode astNode) {
        super(astNode);
    }

    public abstract HelloExpressionType.Type type();

    /**
     * Any errors at this level of the expression tree (not errors in children) should
     * be registered when this function is called.
     *
     * @param errors
     */
    public void annotateSyntaxErrors(AnnotationHolder errors) {
        // Default to nothing
    }
}
