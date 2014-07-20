package hellolang.format;

import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LetBlock extends NodeBlock {
    public LetBlock(ASTNode node) {
        super(node, null, null, null);
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        // let x = ["foo"] in x
        if (newChildIndex == 3)
            return new ChildAttributes(Indent.getContinuationIndent(), null);
        // let x = "foo" in [x]
        else if (newChildIndex == 4)
            return new ChildAttributes(Indent.getNormalIndent(), null);
        else
            return new ChildAttributes(null, null);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
