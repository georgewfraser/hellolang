package hellolang.psi;

import com.intellij.lang.ASTNode;
import hellolang.parser.HelloExpressionType;

/**
 * Every expression of the form {@code let $name = $value in $body} is interpreted as
 * {@code LetExpression($name, $value, ScopeExpression($body))}
 *
 * This wrapper makes it simple to implement {@code Finder.parentLet}
 */
public class ScopeExpression extends WrapperBase {
    public ScopeExpression(ASTNode body) {
        super(body);
    }

    @Override
    public HelloExpressionType.Type type() {
        return HelloExpressionType.Type.SCOPE;
    }
}
