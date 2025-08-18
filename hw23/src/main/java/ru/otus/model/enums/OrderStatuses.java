package ru.otus.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum OrderStatuses {

    CREATED("Новый"),
    AT_WORK("Взят в работу"),
    FOR_PAYMENT("К оплате"),
    PAID("Оплачен");

    private final String name;

    private final static Map<String, OrderStatuses> NAMES = Arrays.stream(OrderStatuses.values())
            .collect(Collectors.toMap(
                    OrderStatuses::getName,
                    orderStatuses -> orderStatuses));

    public static OrderStatuses getByName(String name) {
        return NAMES.get(name);
    }
}
