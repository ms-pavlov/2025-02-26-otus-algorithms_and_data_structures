package ru.otus.dsl.mapper;

import ru.otus.model.SyntaxNode;

public interface SemanticAnalyzer {

    ExpressionObject map(SyntaxNode syntaxNode);
}
