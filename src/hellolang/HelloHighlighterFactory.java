package hellolang;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.jetbrains.annotations.NotNull;

/**
 * Our highlighter is always the same, so we extend SingleLazyInstanceSyntaxHighlighterFactory
 *
 * If we wanted to change the highlighter depending on the project or file, we would extend SyntaxHighlighterFactory directly
 */
public class HelloHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
    @NotNull
    @Override
    public SyntaxHighlighter createHighlighter() {
        return new HelloHighlighter();
    }
}
