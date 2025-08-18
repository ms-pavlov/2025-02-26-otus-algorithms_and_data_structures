package ru.otus.dsl.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.expressions.ExpressionFactory;
import ru.otus.model.enums.Expressions;
import ru.otus.model.enums.ScopePackages;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ExpressionObject {
    private Expressions expressions;
    private List<ScopePackages> scopePackages;
    private ExpressionFactory expressionFactory;

    public boolean isFull() {
        return Objects.nonNull(expressions) && Objects.nonNull(scopePackages) && Objects.nonNull(expressionFactory);
    }
}
