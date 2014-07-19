package hellolang;

import com.intellij.psi.tree.IElementType;

/**
 * This class is just a wrapper around an enum of all the available token types
 */
public class HelloTokenType extends IElementType {

    enum Type {
        LET,
        IN,
        SYMBOL,
        EQUAL,
        STRING,
        SPACE,
        ERROR
    }

    public final Type type;

    private HelloTokenType(Type type) {
        super(type.toString(), HelloLanguage.INSTANCE);

        this.type = type;
    }

    public static final HelloTokenType LET = new HelloTokenType(Type.LET);
    public static final HelloTokenType IN = new HelloTokenType(Type.IN);
    public static final HelloTokenType SYMBOL = new HelloTokenType(Type.SYMBOL);
    public static final HelloTokenType EQUAL = new HelloTokenType(Type.EQUAL);
    public static final HelloTokenType STRING = new HelloTokenType(Type.STRING);
    public static final HelloTokenType SPACE = new HelloTokenType(Type.SPACE);
    public static final HelloTokenType ERROR = new HelloTokenType(Type.ERROR);
}
