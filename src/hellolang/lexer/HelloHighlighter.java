package hellolang.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class HelloHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey[] EMPTY_ATTR = new TextAttributesKey[0];
    public static final TextAttributesKey[] KEYWORD_ATTR = { DefaultLanguageHighlighterColors.KEYWORD };
    public static final TextAttributesKey[] IDENTIFIER_ATTR = { DefaultLanguageHighlighterColors.IDENTIFIER };
    public static final TextAttributesKey[] OPERATOR_ATTR = { DefaultLanguageHighlighterColors.OPERATION_SIGN };
    public static final TextAttributesKey[] STRING_ATTR = { DefaultLanguageHighlighterColors.STRING };
    public static final TextAttributesKey[] ERROR_ATTR = { CodeInsightColors.ERRORS_ATTRIBUTES };
    public static final TextAttributesKey[] INLINE_COMMENT_ATTR = { DefaultLanguageHighlighterColors.BLOCK_COMMENT };

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new HelloLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        // We use a single class to represent all token types, HelloTokenType
        // It's just a wrapper around an enum
        if (type instanceof HelloTokenType) {
            switch (((HelloTokenType) type).type) {
                case LET:
                    return KEYWORD_ATTR;
                case IN:
                    return KEYWORD_ATTR;
                case SYMBOL:
                    return IDENTIFIER_ATTR;
                case EQUAL:
                    return OPERATOR_ATTR;
                case STRING:
                    return STRING_ATTR;
                case SPACE:
                    return EMPTY_ATTR;
                case INLINE_COMMENT:
                    return INLINE_COMMENT_ATTR;
                case ERROR:
                    return ERROR_ATTR;
            }
        }

        // This should never happen
        throw new RuntimeException("Don't know how to highlight " + type.getClass());
    }
}
