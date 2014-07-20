package hellolang.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import hellolang.HelloLanguage;

/**
 * A wrapper around an enum of all the available token types
 */
public class HelloTokenType extends IElementType {

    public enum Type {
        LET,
        IN,
        SYMBOL,
        EQUAL,
        STRING,
        SPACE,
        INLINE_COMMENT,
        ERROR
    }

    public static final HelloTokenType LET = new HelloTokenType(Type.LET);
    public static final HelloTokenType IN = new HelloTokenType(Type.IN);
    public static final HelloTokenType SYMBOL = new HelloTokenType(Type.SYMBOL);
    public static final HelloTokenType EQUAL = new HelloTokenType(Type.EQUAL);
    public static final HelloTokenType STRING = new HelloTokenType(Type.STRING);
    public static final HelloTokenType SPACE = new HelloTokenType(Type.SPACE);
    public static final HelloTokenType INLINE_COMMENT = new HelloTokenType(Type.INLINE_COMMENT);
    public static final HelloTokenType ERROR = new HelloTokenType(Type.ERROR);

    public static final TokenSet WHITE_SPACE_TYPES = TokenSet.create(SPACE);
    public static final TokenSet COMMENT_TYPES = TokenSet.create(INLINE_COMMENT);
    public static final TokenSet STRING_TYPES = TokenSet.create(STRING);

    public final Type type;

    private HelloTokenType(Type type) {
        super(type.toString(), HelloLanguage.INSTANCE);

        this.type = type;
    }
}
