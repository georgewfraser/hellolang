import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import hellolang.HelloFileType;
import hellolang.psi.LetExpression;
import hellolang.psi.ScopeExpression;
import hellolang.psi.StringExpression;
import hellolang.psi.SymbolReferenceExpression;

/**
 * Create a headless IntelliJ and test that files are highlighted correctly
 */
public class Parse extends LightCodeInsightFixtureTestCase {
    public void testParse() {
        String text = "let x = \"Hello world!\" in x";
        PsiFile file = super.createLightFile(HelloFileType.INSTANCE, text);

        LetExpression let = PsiTreeUtil.findElementOfClassAtOffset(file, 0, LetExpression.class, false);

        assertNotNull(let);
        assertNotNull(let.symbol());
        assertEquals("x", let.symbol().name());

        assertNotNull(let.value());
        assertInstanceOf(let.value().body(), StringExpression.class);
        StringExpression value = (StringExpression) let.value().body();
        assertEquals("Hello world!", value.value());

        assertNotNull(let.body());
        ScopeExpression scope = let.body();
        assertInstanceOf(scope.body(), SymbolReferenceExpression.class);
        SymbolReferenceExpression body = (SymbolReferenceExpression) scope.body();
        assertNotNull(body);
        assertEquals("x", body.name());
    }
}
