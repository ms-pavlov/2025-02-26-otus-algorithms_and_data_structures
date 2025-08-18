package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.KeyWords;
import ru.otus.model.enums.SyntaxElementType;

@Component("headerParser")
public class HeaderParser extends AbstractParser {
    private final Parser parameterParser;

    public HeaderParser(@Qualifier("parameterParser") Parser parameterParser) {
        this.parameterParser = parameterParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.HEADER;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();

        boolean hasHeader = eat(KeyWords.ACTION.getWord(), tokenScanner);
        hasHeader = hasHeader && eat(KeyWords.OB.getWord(), tokenScanner);
        SyntaxNode parameter = parameterParser.parse(tokenScanner);
        if (null != parameter) {
            result.add(parameter);
        } else {
            hasHeader = false;
        }

        hasHeader = hasHeader && eat(KeyWords.CB.getWord(), tokenScanner);

        if (hasHeader) {
            return result;
        }
        return null;
    }
}
