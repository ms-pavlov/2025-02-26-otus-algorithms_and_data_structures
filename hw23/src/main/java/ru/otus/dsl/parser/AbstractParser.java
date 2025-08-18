package ru.otus.dsl.parser;


import ru.otus.model.SyntaxNode;
import ru.otus.model.Token;
import ru.otus.model.enums.SyntaxElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class AbstractParser implements Parser {

    public abstract SyntaxElementType getType();

    public abstract SyntaxNode parse(TokenScanner tokenScanner);

    public SyntaxNode prepare() {
        return new SyntaxNode(getType());
    }

    // Анализ содержимого строки
    public String eatStringContent(TokenScanner tokenScanner) {
        StringBuilder stringBuilder = new StringBuilder();
        Optional<String> current = getSymbol(tokenScanner);

        while (current.isPresent()) {
            tokenScanner.increment();
            stringBuilder.append(current.orElseThrow());
            current = getSymbol(tokenScanner);
        }
        return stringBuilder.toString();
    }

    // Вспомогательный метод для проверки токена
    public boolean eat(String expected, TokenScanner tokenScanner) {
        trimSpace(tokenScanner);
        for (Token token : peek(tokenScanner)) {
            if (token.getText().equals(expected)) {
                tokenScanner.setCurrentIndex(token.getEnd() + 1);
                return true;
            }
        }
        trimSpace(tokenScanner);
        return false;
    }

    public boolean peek(String expected, TokenScanner tokenScanner) {
        for (Token token : peek(tokenScanner)) {
            if (token.getText().equals(expected)) {
                return true;
            }
        }
        return false;
    }

    // Просмотр следующего токена без продвижения
    public List<Token> peek(TokenScanner tokenScanner) {
        if (tokenScanner.getCurrentIndex() < tokenScanner.getTokens().size()) {
            return tokenScanner.getTokens().get(tokenScanner.getCurrentIndex());
        }
        return new ArrayList<>();
    }

    public void trimSpace(TokenScanner tokenScanner) {
        while (peek(" ", tokenScanner)) {
            tokenScanner.increment();
        }
    }

    private Optional<String> getSymbol(TokenScanner tokenScanner) {
        return peek(tokenScanner).stream()
                .map(Token::getText)
                .filter(test -> test.matches("[А-Яа-яA-Za-z0123456789 ().,:;-]+"))
                .findFirst();
    }
}
