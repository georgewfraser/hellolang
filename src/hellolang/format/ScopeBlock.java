package hellolang.format;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class ScopeBlock extends NodeBlock {
    /** Always wrap scope when auto-formatting */
    public static final Wrap ALWAYS_WRAP = Wrap.createWrap(WrapType.ALWAYS, true);
    /** Do a normal indent */
    public static final Indent INDENT_NORMAL = Indent.getNormalIndent();
    /** No alignment */
    public static final Alignment NO_ALIGNMENT = null;

    /**
     * Corresponds to `in $body` in `let $symbol = $value in $body`
     * @param node
     */
    public ScopeBlock(ASTNode node) {
        super(node, ALWAYS_WRAP, INDENT_NORMAL, NO_ALIGNMENT);
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(null, null);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
