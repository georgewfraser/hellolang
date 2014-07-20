package hellolang.parser;

import com.intellij.psi.tree.IElementType;
import hellolang.HelloLanguage;

/**
 * A wrapper around an enum of all the available expression types
 */
public class HelloExpressionType extends IElementType {

    public enum Type {
        LET,
        LET_VALUE,
        SCOPE,
        SYMBOL_DEFINITION,
        SYMBOL_REFERENCE,
        STRING
    }

    public final Type type;

    private HelloExpressionType(Type type) {
        super(type.toString(), HelloLanguage.INSTANCE);

        this.type = type;
    }

    public static final HelloExpressionType LET = new HelloExpressionType(Type.LET);
    public static final HelloExpressionType LET_VALUE = new HelloExpressionType(Type.LET_VALUE);
    public static final HelloExpressionType SCOPE = new HelloExpressionType(Type.SCOPE);
    public static final HelloExpressionType SYMBOL_DEFINITION = new HelloExpressionType(Type.SYMBOL_DEFINITION);
    public static final HelloExpressionType SYMBOL_REFERENCE = new HelloExpressionType(Type.SYMBOL_REFERENCE);
    public static final HelloExpressionType STRING = new HelloExpressionType(Type.STRING);
}
