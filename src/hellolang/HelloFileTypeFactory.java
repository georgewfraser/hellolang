package hellolang;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * A factory which is referenced in plugin.xml and registers HelloFileType.INSTANCE when IntelliJ starts up
 */
public class HelloFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(HelloFileType.INSTANCE, HelloFileType.DEFAULT_EXTENSION);
    }
}
