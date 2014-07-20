package hellolang.format;

import com.google.common.collect.ImmutableList;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class NodeBlock extends HelloBlock {
    public final List<Block> children;

    public NodeBlock(ASTNode node,
                     @Nullable Wrap wrap,
                     @Nullable Indent indent,
                     @Nullable Alignment alignment) {
        super(node, wrap, indent, alignment);

        this.children = children(node);
    }

    private List<Block> children(ASTNode node) {
        ImmutableList.Builder<Block> acc = ImmutableList.builder();

        for (ASTNode child : node.getChildren(null)) {
            // White space tokens don't participate in block structure
            IElementType type = child.getElementType();

            if (type != TokenType.WHITE_SPACE) {
                acc.add(Generate.block(child));
            }
        }

        return acc.build();
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        return children;
    }

}
