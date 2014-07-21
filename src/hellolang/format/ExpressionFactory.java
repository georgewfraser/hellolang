package hellolang.format;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import hellolang.HelloFile;
import hellolang.HelloFileType;
import hellolang.psi.LetExpression;
import hellolang.psi.SymbolDefinitionExpression;
import hellolang.psi.SymbolReferenceExpression;
import org.jetbrains.annotations.NotNull;

/**
 * Generate PSIElement objects by creating a dummy file, parsing a string, and extracting the PSIElement
 *
 * This seems like an overly complicated way to do this, but it is how all the IntelliJ plugins work
 */
public class ExpressionFactory {
    public final Project project;

    public ExpressionFactory(Project project) {
        this.project = project;
    }

    public ExpressionFactory(PsiElement element) {
        this(element.getProject());
    }

    public SymbolReferenceExpression createSymbolReference(@NotNull String name) {
        HelloFile dummyFile = createHelloFile(name);

        return dummyFile.findChildByClass(SymbolReferenceExpression.class);
    }

    public SymbolDefinitionExpression createSymbolDefinition(@NotNull String name) {
        String text = "let " + name + " = \"\" in " + name;
        HelloFile dummyFile = createHelloFile(text);
        LetExpression let = dummyFile.findChildByClass(LetExpression.class);

        return let.symbol();
    }

    public HelloFile createHelloFile(String text) {
        PsiFileFactory fileFactory = PsiFileFactory.getInstance(project);
        PsiFile file = fileFactory.createFileFromText("dummy.hello", HelloFileType.INSTANCE, text);

        return (HelloFile) file;
    }
}
