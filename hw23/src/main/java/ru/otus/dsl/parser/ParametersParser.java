package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.KeyWords;
import ru.otus.model.enums.SyntaxElementType;

@Component("parametersParser")
public class ParametersParser extends AbstractParser {
    private final Parser parameterParser;

    public ParametersParser(@Qualifier("parameterParser") Parser parameterParser) {
        this.parameterParser = parameterParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.PARAMETERS;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();

        SyntaxNode parameter = null;
        do {
            parameter = parameterParser.parse(tokenScanner);
            if (null != parameter) {
                result.add(parameter);
            }
        } while (null != parameter && eat(KeyWords.COMMA.getWord(), tokenScanner));
        if (!result.getNodes().isEmpty()) {
            return result;
        }
        return null;
    }
}
