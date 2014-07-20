package hellolang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;

/**
 * Every expression of the form {@code let $name = $value in $body} is interpreted as
 * {@code LetExpression($name, $value, ScopeExpression($body))}
 *
 * This wrapper makes it simple to implement {@code Finder.parentLet}
 */
public class ScopeExpression extends HelloExpression {
    @Nullable
    public HelloExpression body() {
        return super.findChildByClass(HelloExpression.class);
    }

    public ScopeExpression(ASTNode child) {
        super(child);
    }
}
