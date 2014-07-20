package hellolang.format;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HelloBlock implements Block {
    public final ASTNode node;
    public final Wrap wrap;
    public final Indent indent;
    public final Alignment alignment;

    public HelloBlock(ASTNode node,
                      @Nullable Wrap wrap,
                      @Nullable Indent indent,
                      @Nullable Alignment alignment) {
        this.node = node;
        this.wrap = wrap;
        this.indent = indent;
        this.alignment = alignment;
    }

    @NotNull
    @Override
    public TextRange getTextRange() {
        return node.getTextRange();
    }

    @Nullable
    @Override
    public Wrap getWrap() {
        return wrap;
    }

    @Nullable
    @Override
    public Indent getIndent() {
        return indent;
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        return alignment;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return null;
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }
}
