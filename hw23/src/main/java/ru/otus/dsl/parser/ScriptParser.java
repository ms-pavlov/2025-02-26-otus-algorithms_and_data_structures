package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.SyntaxElementType;

@Component("scriptParser")
public class ScriptParser extends AbstractParser{


    private final Parser headerParser;
    private final Parser rolesParser;
    private final Parser scenarioParser;

    public ScriptParser(
            @Qualifier("headerParser") Parser headerParser,
            @Qualifier("rolesParser") Parser rolesParser,
            @Qualifier("scenarioParser") Parser scenarioParser) {
        this.headerParser = headerParser;
        this.rolesParser = rolesParser;
        this.scenarioParser = scenarioParser;
    }


    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.SCRIPT;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();
        SyntaxNode header = headerParser.parse(tokenScanner);
        SyntaxNode roles = rolesParser.parse(tokenScanner);
        SyntaxNode scenario = scenarioParser.parse(tokenScanner);

        if(null != header && null != roles && null != scenario) {
            result.add(header);
            result.add(roles);
            result.add(scenario);
            return result;
        }

        return null;
    }
}
