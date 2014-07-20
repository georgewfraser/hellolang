package hellolang.psi;

import com.intellij.lang.ASTNode;
import hellolang.parser.HelloExpressionType;

/**
 * This is just a wrapper around {@code $value} in {@code let $symbol = $value in $body}
 * It makes it easier to implement {@code LetExpression.value()}
 */
public class LetValueExpression extends WrapperBase {
    public LetValueExpression(ASTNode child) {
        super(child);
    }

    @Override
    public HelloExpressionType.Type type() {
        return HelloExpressionType.Type.LET_VALUE;
    }
}
