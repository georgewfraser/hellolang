The simplest possible IntelliJ custom language plugin.

# Building

Running 'Build -> Prepare Plugin Module 'hello' for deployment' will produce hello.jar in the base directory.

'hello.jar' can be installed with 'File -> Settings', 'Plugins', 'Install plugin from disk'.

Once you've installed the plugin, the 'test.hello' file should show up with a fancy icon with the word 'Hi' in green text.

# Layout

## .idea/
Intellij project files

## META-INF/
Contains plugin.xml, the plugin configuration file.

For some reason this directory gets included in hello.jar, even though it's neither a source root nor a resources root.

## resources/
Icons.

This directory is marked as a source root even though it's really a resources folder, though I'm not sure that's important.

## src/
Java sources.

## test.hello
Once you've built and installed the plugin, this file should show up in the project tree with a special icon with the
word 'Hi' in green text.