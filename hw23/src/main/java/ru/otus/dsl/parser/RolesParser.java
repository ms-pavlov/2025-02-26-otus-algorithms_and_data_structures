package ru.otus.dsl.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.KeyWords;
import ru.otus.model.enums.SyntaxElementType;

@Component("rolesParser")
public class RolesParser extends AbstractParser {

    private final Parser parametersParser;

    public RolesParser(@Qualifier("parametersParser") Parser parametersParser) {
        this.parametersParser = parametersParser;
    }

    @Override
    public SyntaxElementType getType() {
        return SyntaxElementType.ROLES;
    }

    @Override
    public SyntaxNode parse(TokenScanner tokenScanner) {
        SyntaxNode result = prepare();
        boolean hasRoles = eat(KeyWords.SCOPE.getWord(), tokenScanner) && eat(KeyWords.OB.getWord(), tokenScanner);

        SyntaxNode parameters = parametersParser.parse(tokenScanner);
        if (null != parameters) {
            result.add(parameters);
        } else {
            hasRoles = false;
        }

        hasRoles = hasRoles && eat(KeyWords.CB.getWord(), tokenScanner);
        if (hasRoles) {
            return result;
        }
        return null;
    }
}
