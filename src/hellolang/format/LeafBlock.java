package hellolang.format;

import com.google.common.collect.ImmutableList;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LeafBlock extends HelloBlock {

    public LeafBlock(ASTNode node, Wrap wrap, Indent indent, Alignment alignment) {
        super(node, wrap, indent, alignment);
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        return ImmutableList.of();
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(null, null);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
