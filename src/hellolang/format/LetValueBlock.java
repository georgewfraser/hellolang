package hellolang.format;

import com.google.common.collect.ImmutableList;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LetValueBlock extends HelloBlock {
    /** Don't wrap when auto-formatting */
    private static final Wrap LEAVE_WRAP = null;
    /** If the user wraps, do a continuation indent */
    private static final Indent CONTINUATION_INDENT = Indent.getContinuationIndent();
    /** No alignment */
    private static final Alignment NO_ALIGNMENT = null;

    private final List<Block> children;

    /**
     * Corresponds to $value in `let $symbol = $value in $body`
     * @param node
     */
    public LetValueBlock(ASTNode node) {
        super(node, LEAVE_WRAP, CONTINUATION_INDENT, NO_ALIGNMENT);

        this.children = children(node);
    }

    private List<Block> children(ASTNode node) {
        ImmutableList.Builder<Block> acc = ImmutableList.builder();

        for (ASTNode child : node.getChildren(null)) {
            acc.add(Generate.block(child));
        }

        return acc.build();
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        return children;
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
