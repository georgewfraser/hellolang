package hellolang.psi;

import com.intellij.lang.ASTNode;
import hellolang.parser.HelloExpressionType;

public class StringExpression extends HelloExpression {
    public String value() {
        return super.getText().substring(1, super.getTextLength() - 1);
    }

    public StringExpression(ASTNode node) {
        super(node);
    }

    @Override
    public HelloExpressionType.Type type() {
        return HelloExpressionType.Type.STRING;
    }
}
