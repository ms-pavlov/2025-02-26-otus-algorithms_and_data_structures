package ru.otus.dsl.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.expressions.Expression;
import ru.otus.expressions.ExpressionFactory;
import ru.otus.expressions.services.ExpressionService;
import ru.otus.model.SyntaxNode;
import ru.otus.model.enums.Expressions;
import ru.otus.model.enums.ScopePackages;
import ru.otus.model.enums.SyntaxElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component("scriptSemanticAnalyzer")
@RequiredArgsConstructor
public class ScriptSemanticAnalyzer implements SemanticAnalyzer {

    private final ExpressionService expressionService;

    @Override
    public ExpressionObject map(SyntaxNode syntaxNode) {
        if (null == syntaxNode) {
            return null;
        }
        ExpressionObject expressionObject = new ExpressionObject();
        if (SyntaxElementType.SCRIPT.equals(syntaxNode.getType())) {
            syntaxNode.getNodes().forEach(
                    node -> {
                        if (SyntaxElementType.HEADER.equals(node.getType())) {
                            expressionObject.setExpressions(getExpression(node));
                        }
                        if (SyntaxElementType.ROLES.equals(node.getType())) {
                            expressionObject.setScopePackages(getScopePackage(node));
                        }
                        if (SyntaxElementType.SCENARIO.equals(node.getType())) {
                            expressionObject.setExpressionFactory(getExpressionFactory(node));
                        }
                    }
            );
            if (expressionObject.isFull()) {
                return expressionObject;
            }
        }
        return null;
    }

    private Expressions getExpression(SyntaxNode syntaxNode) {
        return Optional.ofNullable(syntaxNode)
                .map(SyntaxNode::getNodes)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(node -> SyntaxElementType.PARAMETER.equals(node.getType()))
                .findFirst()
                .map(this::getParameter)
                .map(this::getString)
                .map(Expressions::getByDescription)
                .orElseThrow();
    }


    private List<ScopePackages> getScopePackage(SyntaxNode syntaxNode) {
        List<ScopePackages> scopePackages = getParameters(syntaxNode).stream()
                .map(this::getParameter)
                .filter(Objects::nonNull)
                .map(this::getString)
                .map(ScopePackages::getByDescription)
                .toList();

        if (!scopePackages.isEmpty()) {
            return scopePackages;
        }
        return null;
    }

    private ExpressionFactory getExpressionFactory(SyntaxNode syntaxNode) {
        return args -> context -> expressionService.resolve(Expressions.DO_EXPRESSIONS, getExpressions(syntaxNode))
                .interpret(context);
    }

    private Object[] getExpressions(SyntaxNode syntaxNode) {
        return Optional.ofNullable(syntaxNode)
                .filter(item -> SyntaxElementType.SCENARIO.equals(item.getType()))
                .map(SyntaxNode::getNodes)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(item -> SyntaxElementType.ACTIONS.equals(item.getType()))
                .map(SyntaxNode::getNodes)
                .findFirst()
                .orElseGet(ArrayList::new)
                .stream()
                .filter(item -> SyntaxElementType.ACTION.equals(item.getType()))
                .map(this::getActoinObject)
                .filter(Objects::nonNull)
                .map(actoinObject -> expressionService.resolve(actoinObject.getExpression(), actoinObject.getObjects()))
                .toArray(Expression[]::new);
    }

    private ActoinObject getActoinObject(SyntaxNode item) {
        List<String> parameters = getParameters(item).stream()
                .map(this::getParameter)
                .filter(Objects::nonNull)
                .map(this::getString)
                .toList();
        if (!parameters.isEmpty()) {
            return ActoinObject.builder()
                    .expression(Expressions.getByDescription(parameters.get(0)))
                    .objects(parameters.stream().filter(parameter -> parameters.indexOf(parameter) != 0)
                            .toArray())
                    .build();
        }
        return null;
    }

    private List<SyntaxNode> getParameters(SyntaxNode syntaxNode) {
        return Optional.ofNullable(syntaxNode)
                .map(SyntaxNode::getNodes)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(syntaxNode1 -> SyntaxElementType.PARAMETERS.equals(syntaxNode1.getType()))
                .findFirst()
                .map(SyntaxNode::getNodes)
                .orElseGet(ArrayList::new)
                .stream()
                .toList();
    }

    private SyntaxNode getParameter(SyntaxNode syntaxNode) {
        if (SyntaxElementType.PARAMETER.equals(syntaxNode.getType())) {
            return syntaxNode;
        }
        return null;
    }

    private String getString(SyntaxNode syntaxNode) {
        for (SyntaxNode level1 : syntaxNode.getNodes()) {
            if (SyntaxElementType.STRING.equals(level1.getType())) {
                return level1.getElement();
            }
        }
        return null;
    }
}
