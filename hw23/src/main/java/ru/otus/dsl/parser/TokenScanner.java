package ru.otus.dsl.parser;

import lombok.Getter;
import lombok.Setter;
import ru.otus.model.Token;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TokenScanner {
    private Map<Integer, List<Token>> tokens;
    private int currentIndex = 0;

    public TokenScanner(Map<Integer, List<Token>> tokens) {
        this.tokens = tokens;
        this.currentIndex = 0;
    }

    public void increment() {
        currentIndex += 1;
    }

    public void increment(int delta) {
        currentIndex += delta;
    }
}
