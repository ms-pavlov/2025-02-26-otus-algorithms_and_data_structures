package ru.otus.dsl.scaner;

import ru.otus.model.Token;

import java.util.List;

public interface Search {

    List<Token> scan(String text);
}
