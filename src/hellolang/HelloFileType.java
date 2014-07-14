package hellolang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * A singleton object defining the .hello file type
 */
public class HelloFileType extends LanguageFileType {
    public static final String DEFAULT_EXTENSION = "hello";
    public static final LanguageFileType INSTANCE = new HelloFileType();

    private HelloFileType() {
        super(HelloLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return HelloLanguage.NAME;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Hello file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/hellolang/icons/hello_file.png");
    }
}
