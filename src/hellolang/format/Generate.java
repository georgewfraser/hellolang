package hellolang.format;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import hellolang.parser.HelloExpressionType;

public class Generate {
    public static HelloBlock block(ASTNode node) {
        IElementType type = node.getElementType();

        // Expressions that participate in the PSI tree
        if (type instanceof HelloExpressionType) {
            switch (((HelloExpressionType) type).type) {
                case LET:
                    return new LetBlock(node);
                case LET_VALUE:
                    return new LetValueBlock(node);
                case SCOPE:
                    return new ScopeBlock(node);
                case SYMBOL_DEFINITION:
                case SYMBOL_REFERENCE:
                case STRING:
                    return new LeafBlock(node, null, null, null);
                default:
                    throw new RuntimeException("Don't know what to do with " + type);
            }
        }
        // Keywords like let, in that don't participate in the PSI tree
        else
            return new LeafBlock(node, null, null, null);
    }
}
