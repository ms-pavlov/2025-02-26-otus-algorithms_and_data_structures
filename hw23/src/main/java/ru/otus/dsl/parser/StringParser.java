package ru.otus.dsl.parser;

import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.SyntaxElementType;

@Component("stringParser")
public class StringParser extends AbstractParser {

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.STRING;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();
        String value = eatStringContent(tokenScanner);
        if (!value.isEmpty()) {
            result.setElement(value);
            return result;
        }
        return null;
    }
}
