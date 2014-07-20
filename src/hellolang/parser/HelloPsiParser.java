package hellolang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import hellolang.lexer.HelloTokenType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Converts a series of tokens from HelloLexer into an ASTNode
 *
 * The name PsiParser is misleading, because it doesn't generate PsiElements (that is done by HelloParserDefinition)
 *
 * The name ASTNode is also misleading, because it doesn't correspond to the traditional notion of an abstract
 * syntax tree from the computer science literature. Instead, it is just a series of tokens that have been cut
 * into a hierarchical structure, for example:
 *
 * <code>let x = "foo" in let y = x in y</code>
 *
 * would be cut into:
 *
 * <code>
 *     let
 *       x
 *       =
 *       "foo"
 *       in
 *       let
 *           y
 *           =
 *           x
 *           in
 *           y
 * </code>
 *
 * An ASTNode can contain extra elements, missing elements, wrong elements, bad tokens. This is very helpful for
 * recovering from errors and presenting reasonable annotations for the user.
 *
 * PsiElement enforces the syntax onto the ASTNode and is closer to the traditional notion of an abstract syntax tree.
 */
public class HelloPsiParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(IElementType rootType, PsiBuilder builder) {
        // Turning on debug mode gives much better errors messages when you fail to correctly close() markers
        // created by builder.mark()  This should probably be turned off for deployment
        builder.setDebugMode(true);

        PsiBuilder.Marker root = builder.mark();
        new ParserHelper(builder).parseSequence();
        root.done(rootType);

        return builder.getTreeBuilt();
    }

    /**
     * Captures the PsiBuilder so that we don't have to continually pass it around between methods
     * 
     * Implements a recursive descent parser of the grammar:
     * 
     * EXPR : LET | SYMBOL_REFERENCE | STRING
     * LET : let SYMBOL_DEFINITION = EXPR in EXPR
     */
    private class ParserHelper {
        private final PsiBuilder builder;

        public ParserHelper(PsiBuilder builder) {
            this.builder = builder;
        }

        private void pop() {
            builder.advanceLexer();
        }

        @Nullable
        private HelloTokenType peekType() {
            return (HelloTokenType) builder.getTokenType();
        }

        public void parseSequence() {
            while (peekType() != null)
                parseExpression();
        }

        public void parseExpression() {
            PsiBuilder.Marker start = builder.mark();
            HelloTokenType next = peekType();

            if (next == null) {
                start.error("Expected expression");
                return;
            }

            switch (next.type) {
                case LET:
                    pop();
                    parseSymbolDefinition();
                    match(HelloTokenType.Type.EQUAL);
                    parseLetValue();
                    parseScope();
                    start.done(HelloExpressionType.LET);
                    break;
                case SYMBOL:
                    pop();
                    start.done(HelloExpressionType.SYMBOL_REFERENCE);
                    break;
                case STRING:
                    pop();
                    start.done(HelloExpressionType.STRING);
                    break;
                case IN:
                case EQUAL:
                case ERROR:
                    pop();
                    start.error("Expected expression");
            }
        }

        private void parseScope() {
            PsiBuilder.Marker start = builder.mark();

            match(HelloTokenType.Type.IN);
            parseExpression();

            start.done(HelloExpressionType.SCOPE);
        }

        private void parseSymbolDefinition() {
            PsiBuilder.Marker start = builder.mark();
            HelloTokenType next = peekType();

            if (next == null) {
                start.error("Expected symbol");
                return;
            }

            switch (next.type) {
                case SYMBOL:
                    pop();
                    start.done(HelloExpressionType.SYMBOL_DEFINITION);
                    break;
                default:
                    start.error("Expected symbol");
            }
        }

        private void parseLetValue() {
            PsiBuilder.Marker start = builder.mark();

            parseExpression();

            start.done(HelloExpressionType.LET_VALUE);
        }

        private void match(HelloTokenType.Type expectedTokenType) {
            // We'll use this marker for the error if necessary
            PsiBuilder.Marker start = builder.mark();
            HelloTokenType next = peekType();

            if (next == null) {
                start.error("Expected " + expectedTokenType.name());
                return;
            }

            // If next token matches expected type, pop it and drop the error marker
            if (next.type == expectedTokenType) {
                start.drop();
                pop();
            }
            // Otherwise assume that there is a missing token and leave it in place
            else
                start.error("Expected " + expectedTokenType.name());
        }
    }
}
