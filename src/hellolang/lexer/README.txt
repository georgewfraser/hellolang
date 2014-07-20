These classes implement an incremental, regex-based lexer.

HelloTokenType is a wrapper around an enum of all the available token types.
HelloLexer implements the lexer.
HelloHighlighter applies syntax coloring to the tokens output by HelloLexer.

IntelliJ includes adapters for JFlex, a lexer generator, but in order to present the plugin APIs in the simplest
possible context we are not using it here.