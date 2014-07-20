import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import hellolang.HelloFileType;
import hellolang.psi.LetExpression;
import hellolang.psi.SymbolDefinitionExpression;
import hellolang.psi.SymbolReferenceExpression;

public class ResolveSymbols extends LightCodeInsightFixtureTestCase {
    // TODO test let $name = $value in $body doesn't define scope in $value

    /**
     * Check that reference to x resolves to definition
     */
    public void testGotoSymbol() {
        String text = "let x = \"Hello world!\" in x";
        PsiFile file = super.createLightFile(HelloFileType.INSTANCE, text);

        LetExpression let = PsiTreeUtil.findElementOfClassAtOffset(file, 0, LetExpression.class, false);

        SymbolDefinitionExpression defineX = let.symbol();
        SymbolReferenceExpression referenceX = (SymbolReferenceExpression) let.body().body();

        assertEquals(defineX, referenceX.getReference().resolve());
    }

    public void testProperScope() {
        String text = "let x = \"Hello world!\" in let x = x in x";
        PsiFile file = super.createLightFile(HelloFileType.INSTANCE, text);

        LetExpression outerLet = PsiTreeUtil.findElementOfClassAtOffset(file, 0, LetExpression.class, false);
        SymbolDefinitionExpression outerX = outerLet.symbol();

        LetExpression innerLet = (LetExpression) outerLet.body().body();
        SymbolDefinitionExpression innerX = innerLet.symbol();

        SymbolReferenceExpression referenceOuterX = (SymbolReferenceExpression) innerLet.value().body();
        SymbolReferenceExpression referenceInnerX = (SymbolReferenceExpression) innerLet.body().body();

        assertEquals(outerX, referenceOuterX.getReference().resolve());
        assertEquals(innerX, referenceInnerX.getReference().resolve());
    }
}
