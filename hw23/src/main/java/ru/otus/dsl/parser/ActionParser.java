package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.KeyWords;
import ru.otus.model.enums.SyntaxElementType;

@Component("actionParser")
public class ActionParser extends AbstractParser{

    private final Parser parametersParser;

    public ActionParser(@Qualifier("parametersParser") Parser parametersParser) {
        this.parametersParser = parametersParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.ACTION;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();
        SyntaxNode parameters = parametersParser.parse(tokenScanner);
        if(null != parameters && eat(KeyWords.SEMI_COLON.getWord(), tokenScanner)) {
            result.add(parameters);
            return result;
        }
        return null;
    }
}
