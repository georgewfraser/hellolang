package hellolang;

import com.intellij.lang.Language;

/**
 * A singleton object representing the Helloworld language
 */
public class HelloLanguage extends Language {
    /**
     * A unique identifier for the Hello language
     */
    public static final String ID = "hello";

    /**
     * A human-readable name for the Hello language
     */
    public static final String NAME = "Hello";

    public static final HelloLanguage INSTANCE = new HelloLanguage();

    private HelloLanguage() {
        super(ID);
    }

    public String getDisplayName() {
        return NAME;
    }
}
