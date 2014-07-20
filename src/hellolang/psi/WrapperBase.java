package hellolang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;

public abstract class WrapperBase extends HelloExpression {
    @Nullable
    public HelloExpression body() {
        return super.findChildByClass(HelloExpression.class);
    }

    public WrapperBase(ASTNode child) {
        super(child);
    }
}
