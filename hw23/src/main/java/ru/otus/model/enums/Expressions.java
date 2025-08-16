package ru.otus.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Expressions {

    RESOLVE_EXPRESSIONS("ExpressionService::resolve", "Зарегистрировать выражения"),
    GET_CURRENT_SCOPE("Token::getScope", "Получить область видимости из токена"),
    DO_EXPRESSIONS("Expression::do", "Выполнить действия"),
    PARSE_TOKEN("Token::parse", "Верифицировать и распарить токен"),
    CHANGE_SCOPE("Scope::change", "Сменить область видимости"),
    DO_ACTION("OrderAction::execute", "Выполнить действие"),
    GET_ORDER_BY_ID("Order::getById", "Найти задачу по ID"),
    PARSE_ORDER("Order::parse", "Распарить заказ"),
    SET_ORDER_CUSTOMER("Order::setCustomer", "Задать заказчика в заказе"),
    SET_ORDER_MANAGER("Order::setManager", "Назначить менеджера для заказа"),
    SET_ORDER_STATUS("Order::setOrderStatus", "Назначить статус у заказа"),
    GET_ALL_ORDERS("Order::getList", "Получить список всех заказов"),
    ORDERS_FILTER("Order::filter", "Отфильтровать список заказов для текущего пользователя"),

    PROCESS_ORDER_MESSAGE("OrderAction::process", "Выполнить действие над заказом"),
    CREATE_ORDER("Order::create", "Создать заказ"),
    SAVE_ORDER("Order::save", "Сохранить заказ"),
    VALID_ORDER_STATUS("Order::validStatus", "Валидация статуса"),
    GET_ORDERS("Order::getAll", "Получить список заказов для текущего пользователя"),
    GET_ORDERS_IN_WORK("Order::getInWork", "Взять заказ в работу"),
    SEND_ORDERS_FOR_PAYMENT("Order::sendForPayment", "Выставить счет на оплату"),
    SET_ORDER_PAID("Order::setPaid", "Счет оплачен");


    private final static Map<String, Expressions> EXPRESSIONS = Arrays.stream(Expressions.values())
            .collect(Collectors.toMap(
                    Expressions::getExpressionName,
                    expressions -> expressions));

    private final String expressionName;
    private final String expressionDescription;

    public static Expressions getByName(String name) {
        return EXPRESSIONS.get(name);
    }
}
