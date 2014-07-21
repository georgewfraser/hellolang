The simplest possible [IntelliJ custom language plugin](http://confluence.jetbrains.com/display/IDEADEV/Developing+Custom+Language+Plugins+for+IntelliJ+IDEA).
Implements the Hello language, whose purpose is to exercise the features of the IntelliJ API in the simplest possible
context.

# Hello language
The Hello language consists of:

## Strings
`"Hello world!"`

## Symbols
`x`

## Let expressions
`let x = "Hello world!" in x`

# Plugin features
* Syntax highlighting
* Annotations
* Symbol references
* In-place renaming
* Code formatting
* Test fixtures

# TODO plugin features
* Reference Java symbols from Hello
* Reference Hello symbols from Java (requires additional syntax, PsiReferenceContributor?)
* Run configuration that invokes Hello interpreter
* Compilation of Hello files using ASM

# Building

Running 'Build -> Prepare Plugin Module 'hello' for deployment' will produce hello.jar in the base directory.

'hello.jar' can be installed with 'File -> Settings', 'Plugins', 'Install plugin from disk'.

Once you've installed the plugin, the 'test.hello' file should show up with a fancy icon with the word 'Hi' in green text.

# Layout

## .idea/
Intellij project files

## META-INF/
Contains plugin.xml, the plugin configuration file.

## resources/
Icons.

## src/
Java sources.

## test.hello
Once you've built and installed the plugin, this file should show up in the project tree with a special icon with the
word 'Hi' in green text.