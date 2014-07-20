package hellolang.psi;

import com.intellij.lang.ASTNode;

public class StringExpression extends HelloExpression {
    public String value() {
        return super.getText().substring(1, super.getTextLength() - 1);
    }

    public StringExpression(ASTNode node) {
        super(node);
    }
}
