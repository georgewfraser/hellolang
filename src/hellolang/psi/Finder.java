package hellolang.psi;

import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

public class Finder {
    /**
     * Find the nearest parent {@code let $symbol = $value in $body} where {@code target}
     * is in {@code $body}.
     *
     * If {@code target} is in {$value}, that let will be skipped.
     *
     * @param target
     * @return
     */
    @Nullable
    public static LetExpression parentLet(HelloExpression target) {
        // Finding the correct parent is tricky in cases like `let x = "foo" in let x = x in x`
        // Consider the example of looking up the definition of [x] for `let x = "foo" in let x = [x] in x`
        // The correct answer is `let [x] = "foo" in let x = x in x`
        // But if we are sloppy, we might incorrectly find `let x = "foo" in let [x] = x in x`

        // First, find the nearest scope parent
        // In the above example, that would be `let x = "foo" in [let x = x in x]`
        ScopeExpression scope = PsiTreeUtil.getParentOfType(target, ScopeExpression.class);

        if (scope == null)
            return null;

        // Second, find the nearest let parent of the scope
        // Continuing the example, that would be the outermost let,
        // the whole expression: `[let x = "foo" in let x = x in x]`
        return PsiTreeUtil.getParentOfType(scope, LetExpression.class);
    }

    /**
     * Find the nearest parent {@code let $symbol = $value in $body} where {@code target}
     * is in {@code $body} and {@code $symbol.name == name}
     *
     * @param target
     * @param name
     * @return
     */
    @Nullable
    public static LetExpression parentDefinition(HelloExpression target, String name) {
        LetExpression parent = Finder.parentLet(target);

        while (parent != null) {
            if (parent.symbol() != null && parent.symbol().name().equals(name))
                return parent;
            else
                parent = Finder.parentLet(parent);
        }

        return null;
    }
}
