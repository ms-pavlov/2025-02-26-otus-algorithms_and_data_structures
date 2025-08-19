package ru.otus.dsl.mapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.model.enums.Expressions;

@Getter
@Setter
@Builder
public class ActoinObject {
    private Expressions expression;
    private Object[] objects;
}
