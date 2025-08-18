package ru.otus.dsl.parser;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.KeyWords;
import ru.otus.model.enums.SyntaxElementType;

@Component("parameterParser")
public class ParameterParser extends AbstractParser {

    private final Parser stringParser;

    public ParameterParser(@Qualifier("stringParser") Parser stringParser) {
        this.stringParser = stringParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.PARAMETER;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();
        boolean hasParameter = eat(KeyWords.QUOTE.getWord(), tokenScanner);

        SyntaxNode string = stringParser.parse(tokenScanner);
        if (null != string) {
            result.add(string);
        }

        if (hasParameter && eat(KeyWords.QUOTE.getWord(), tokenScanner)) {
            return result;
        }
        return null;
    }
}
