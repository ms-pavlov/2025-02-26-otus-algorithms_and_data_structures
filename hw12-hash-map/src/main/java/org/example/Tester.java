package org.example;

import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

public class Tester implements Test{

    private final int n;
    private final Supplier<Map<String, Integer>> supplier;

    public Tester(int n, Supplier<Map<String, Integer>> supplier) {
        this.n = n;
        this.supplier = supplier;
    }

    @Override
    public void run() {
        System.out.println(ConsoleColors.WHITE_BRIGHT + "====    Добавление уникальных N = "+ n +" пар ключ-значение    ====");
        long begin;
        long end;

        Map<String, Integer> map = supplier.get();

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            map.put(i+"", i);
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

        System.out.println("====    Проверка правильности заполнения    ====");

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (map.get(i+"") == null) {
                flag = false;
                break;
            }
        }

        System.out.println("Тест " + (flag ? ConsoleColors.GREEN_BOLD + "ПРОЙДЕН" : ConsoleColors.RED_BOLD + "НЕ ПРОЙДЕН") + ConsoleColors.WHITE_BRIGHT);


        System.out.println("====    Получение уникальных N = "+ n +" значений по ключу    ====");

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            map.get(i+"");
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

        System.out.println("====    Удаление уникальных N = "+ n +" значений по ключу    ====");

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            map.remove(i+"");
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

    }
}
