Parsing proceeds in two stages:

1. The output of HelloLexer is encapsulated in a PsiBuilder and passed to HelloPsiParser.parse(...)
   HelloPsiParser.parse(...) cuts the stream of tokens into a hierarchy

   For example, the input:
     let x = "foo" in x
   Would be transformed into:
     {let {x} = {"foo"} in {x}}

   PsiBuilder creates an ASTNode at each node of this hierarchy.  ASTNode is a misleading name -- it is not similar to
   an abstract syntax tree as the term is used in the computer science literature, it is just a hierarchy imposed on
   the text.

2. Each ASTNode is passed to HelloParserDefinition.createElement(...), which wraps it in one of the classes in
   hellolang.psi.*

   These classes are closer to the conventional definition of an abstract syntax tree, with two exceptions:
     * They are mutable
     * They can contain errors
   More details in psi/README.txt
