package ru.otus.annotations;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.otus.model.enums.Expressions;
import ru.otus.model.enums.ScopePackages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Order(0)
public @interface ExpressionsComponent {

    ScopePackages[] scopePackages();

    Expressions expression();

    String description() default "";
}
