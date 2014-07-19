package hellolang;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple regex lexer
 */
public class HelloLexer extends LexerBase {
    private CharSequence buffer;
    private int bufferEnd, firstTokenStart, firstTokenEnd;
    private HelloTokenType firstToken;

    public static final Pattern LET = Pattern.compile("let");
    public static final Pattern IN = Pattern.compile("in");
    public static final Pattern SYMBOL = Pattern.compile("\\w+");
    public static final Pattern EQUAL = Pattern.compile("=");
    public static final Pattern STRING = Pattern.compile("\"[^\"]+\"");
    public static final Pattern SPACE = Pattern.compile("\\s+");

    /**
     * Try to match pattern between firstTokenEnd and bufferEnd
     *
     * If match succeeds, update firstTokenStart, firstTokenEnd to the boundaries of the match
     *
     * @param pattern A regular expression for a token
     * @return if the match succeeds
     */
    private boolean tryMatch(Pattern pattern) {
        Matcher matcher = pattern.matcher(buffer).region(firstTokenEnd, bufferEnd);

        if (matcher.lookingAt()) {
            MatchResult result = matcher.toMatchResult();

            firstTokenStart = result.start();
            firstTokenEnd = result.end();

            return true;
        }
        else
            return false;
    }

    @Override
    public void advance() {
        // The order is important, since the SYMBOL regex encompasses let and in
        if (firstTokenEnd == bufferEnd)
            firstToken = null;
        else if (tryMatch(LET))
            firstToken = HelloTokenType.LET;
        else if (tryMatch(IN))
            firstToken = HelloTokenType.IN;
        else if (tryMatch(SYMBOL))
            firstToken = HelloTokenType.SYMBOL;
        else if (tryMatch(EQUAL))
            firstToken = HelloTokenType.EQUAL;
        else if (tryMatch(STRING))
            firstToken = HelloTokenType.STRING;
        else if (tryMatch(SPACE))
            firstToken = HelloTokenType.SPACE;
        else {
            firstTokenStart = firstTokenEnd;
            firstTokenEnd = firstTokenEnd + 1;
            firstToken = HelloTokenType.ERROR;
        }

        CharSequence start = buffer.subSequence(0, firstTokenStart);
        CharSequence middle = buffer.subSequence(firstTokenStart, firstTokenEnd);
        CharSequence end = buffer.subSequence(firstTokenEnd, buffer.length());

        System.out.println("ADVANCE: \"" +  "" + start + "[" + middle + "]" + end + "\"");
    }

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.firstTokenStart = startOffset;
        this.firstTokenEnd = startOffset;
        this.bufferEnd = endOffset;

        CharSequence start = buffer.subSequence(0, startOffset);
        CharSequence middle = buffer.subSequence(startOffset, endOffset);
        CharSequence end = buffer.subSequence(endOffset, buffer.length());

        System.out.println("START: \"" + start + "[" + middle + "]" + end + "\"");

        advance();
    }

    @Override
    public int getState() {
        // This lexer has no state
        return 0;
    }

    @Nullable
    @Override
    public IElementType getTokenType() {
        return firstToken;
    }

    @Override
    public int getTokenStart() {
        return firstTokenStart;
    }

    @Override
    public int getTokenEnd() {
        return firstTokenEnd;
    }

    @NotNull
    @Override
    public CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return bufferEnd;
    }
}
