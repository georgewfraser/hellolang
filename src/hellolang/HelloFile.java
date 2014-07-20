package hellolang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class HelloFile extends PsiFileBase {
    public HelloFile(FileViewProvider viewProvider) {
        super(viewProvider, HelloLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return HelloFileType.INSTANCE;
    }
}
