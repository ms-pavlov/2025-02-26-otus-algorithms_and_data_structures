package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.KeyWords;
import ru.otus.model.enums.SyntaxElementType;

@Component("scenarioParser")
public class ScenarioParser extends AbstractParser {

    private final Parser actionsParser;

    public ScenarioParser(@Qualifier("actionsParser") Parser actionsParser) {
        this.actionsParser = actionsParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.SCENARIO;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();

        boolean hasScenario = eat(KeyWords.EXECUTE.getWord(), tokenScanner) && eat(KeyWords.OCB.getWord(), tokenScanner);
        result.add(actionsParser.parse(tokenScanner));
        hasScenario = hasScenario && eat(KeyWords.CCB.getWord(), tokenScanner);
        if (hasScenario) {
            return result;
        }
        return null;
    }
}
