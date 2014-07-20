package hellolang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;

/**
 * This is just a wrapper around {@code $value} in {@code let $symbol = $value in $body}
 * It makes it easier to implement {@code LetExpression.value()}
 */
public class LetValueExpression extends HelloExpression {
    @Nullable
    public HelloExpression body() {
        return super.findChildByClass(HelloExpression.class);
    }

    public LetValueExpression(ASTNode child) {
        super(child);
    }
}
