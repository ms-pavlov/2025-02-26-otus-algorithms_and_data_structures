package org.example;

import lombok.Getter;

@Getter
public abstract class AbstractSearch implements Search{

    private final String text;
    private final String mask;

    protected AbstractSearch(String text, String mask) {
        this.text = text;
        this.mask = mask;
    }


}
