package ru.otus.expressions.main;

import lombok.AllArgsConstructor;
import ru.otus.annotations.ExpressionsComponent;
import ru.otus.expressions.Expression;
import ru.otus.expressions.ExpressionFactory;
import ru.otus.model.enums.Expressions;
import ru.otus.model.enums.ScopePackages;

@ExpressionsComponent(
        expression = Expressions.ERROR_MESSAGE,
        scopePackages = ScopePackages.DEFAULT,
        description = "Отправить сообщение об ошибке")
@AllArgsConstructor
public class ErrorMessageExpression implements ExpressionFactory {
    @Override
    public Expression create(Object... args) {
        return context -> {
            if(args.length > 0) {
                throw new RuntimeException(String.valueOf(args[0]));
            } else {
                throw new RuntimeException();
            }
        };
    }
}
