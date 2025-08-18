package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.SyntaxElementType;

@Component("actionsParser")
public class ActionsParser extends AbstractParser {

    private final Parser actionParser;

    public ActionsParser(@Qualifier("actionParser") Parser actionParser) {
        this.actionParser = actionParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.ACTIONS;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();

        SyntaxNode action = null;
        do {
            action = actionParser.parse(tokenScanner);
            if (null != action) {
                result.add(action);
            }
        } while (null != action);

        return result;
    }
}
