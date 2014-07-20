package hellolang.format;

import com.google.common.collect.ImmutableList;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FileBlock implements Block {
    public final PsiFile file;
    public final List<Block> subBlocks;

    public FileBlock(PsiFile file) {
        this.file = file;

        ImmutableList.Builder<Block> acc = ImmutableList.builder();

        for (ASTNode child : file.getNode().getChildren(null)) {
            acc.add(Generate.block(child));
        }

        subBlocks = acc.build();
    }

    @NotNull
    @Override
    public TextRange getTextRange() {
        return file.getTextRange();
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        return subBlocks;
    }

    @Nullable
    @Override
    public Wrap getWrap() {
        return null;
    }

    @Nullable
    @Override
    public Indent getIndent() {
        return Indent.getAbsoluteNoneIndent();
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        return null;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block block, @NotNull Block block2) {
        return null;
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int i) {
        return new ChildAttributes(getIndent(), getAlignment());
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
