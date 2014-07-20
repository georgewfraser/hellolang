These classes are IntelliJ's version of an abstract syntax tree, with several exceptions:
  * They are mutable

  * They may contain extra elements, wrong elements, or missing elements

  * They include wrapper nodes such as ScopeExpression and LetValueExpression which have no semantic meaning.
    The purpose of these classes is to make it easier to traverse the tree with PsiTreeUtil.

  * They include some very specific node types such as SymbolDefinitionExpression and SymbolReferenceExpression,
    which differentiate between `let x (SymbolDefinition) = "foo" in x (SymbolReference)`
    This distinction is traditionally not made in an abstract syntax tree, because it is implied by the context.
    But here it is convenient to be able to know whether a symbol is a definition or a reference, without having
    to look at its parent.

