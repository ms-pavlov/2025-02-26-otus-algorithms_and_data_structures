package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.function.Supplier;

public class Tester implements Test{


    private final int n;
    private final int l;
    private final Supplier<Trie<Integer>> supplier;

    public Tester(int n, int l, Supplier<Trie<Integer>> supplier) {
        this.n = n;
        this.l = l;
        this.supplier = supplier;
    }

    @Override
    public void run() {
        System.out.println(ConsoleColors.WHITE_BRIGHT + "====    Добавление уникальных N = "+ n +" строк в trie    ====");
        long begin;
        long end;

        Trie<Integer> trie = supplier.get();

        String[] values = new String[n];

        for (int i = 0; i < n; i++) {
            values[i] = RandomStringUtils.randomAlphabetic(l).toLowerCase();
        }

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            trie.insert(values[i], i);
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

        System.out.println("====    Проверка правильности заполнения    ====");

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (!trie.search(values[i])) {
                flag = false;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != trie.get(values[i])) {
                flag = false;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!trie.startsWith(values[i].substring(0, values[i].length() -2))) {
                flag = false;
                break;
            }
        }

        System.out.println("Тест " + (flag ? ConsoleColors.GREEN_BOLD + "ПРОЙДЕН" : ConsoleColors.RED_BOLD + "НЕ ПРОЙДЕН") + ConsoleColors.WHITE_BRIGHT);


        System.out.println("====    Поиск уникальных N = "+ n +" строк    ====");

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            trie.search(values[i]);
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

        System.out.println("====    Проверка 'начинается с' для N = "+ n +" значений    ====");

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            trie.startsWith(values[i]);
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

        System.out.println("====    Удаление N = "+ n +" значений    ====");

        begin = new Date().getTime();
        for (int i = 0; i < n; i++) {
            trie.delete(values[i]);
        }
        end = new Date().getTime();

        System.out.println("Время выполнения  " + (end - begin) + "мс");

    }
}
