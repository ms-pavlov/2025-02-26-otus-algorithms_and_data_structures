package ru.otus.dsl.parser;


import ru.otus.model.SyntaxNode;

public interface Parser {

    SyntaxNode parse(TokenScanner tokenScanner);
}
