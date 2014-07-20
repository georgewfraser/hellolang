package hellolang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.AnnotationHolder;
import hellolang.parser.HelloExpressionType;
import org.jetbrains.annotations.Nullable;

public class LetExpression extends HelloExpression {
    @Nullable
    public SymbolDefinitionExpression symbol() {
        return super.findChildByClass(SymbolDefinitionExpression.class);
    }

    @Nullable
    public LetValueExpression value() {
        return super.findChildByClass(LetValueExpression.class);
    }

    @Nullable
    public ScopeExpression body() {
        return super.findChildByClass(ScopeExpression.class);
    }

    public LetExpression(ASTNode node) {
        super(node);
    }

    @Override
    public HelloExpressionType.Type type() {
        return HelloExpressionType.Type.LET;
    }

    @Override
    public void annotateSyntaxErrors(AnnotationHolder errors) {
        if (symbol() == null)
            errors.createErrorAnnotation(this, "Missing symbol");
        if (value() == null)
            errors.createErrorAnnotation(this, "Missing value");
        if (body() == null)
            errors.createErrorAnnotation(this, "Missing body");
    }
}
