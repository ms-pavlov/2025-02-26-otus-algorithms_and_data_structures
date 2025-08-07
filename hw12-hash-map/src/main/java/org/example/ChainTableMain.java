package org.example;

import java.util.Random;

public class ChainTableMain {

    private final static Random RANDOM = new Random();

    public static void main(String[] args) {
        final int n = 1_000_000;

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "хеш-таблица, использующая метод цепочек" + ConsoleColors.WHITE_BRIGHT);
        System.out.println();

        Test chainTableMap = new Tester(n, () -> new ChainTableMap<>(3, 0.75f));
        chainTableMap.run();

        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "хеш-таблица с открытой адресацией (линейный пробинг)" + ConsoleColors.WHITE_BRIGHT);
        System.out.println();

        Test lineOpenAddressMap = new Tester(n, () -> new OpenAddressMap<>(n/20, n/20) {
            @Override
            public int getNextProbing() {
                return getAndIncProbing()/2;
            }
        });
        lineOpenAddressMap.run();

        System.out.println();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "хеш-таблица с открытой адресацией (квадратичный пробинг)" + ConsoleColors.WHITE_BRIGHT);
        System.out.println();

        Test squareOpenAddressMap = new Tester(n, () -> new OpenAddressMap<>(n/20, n/20) {
            @Override
            public int getNextProbing() {
                int probing = getAndIncProbing();

                return ((probing * probing)/2) - probing/2;
            }
        });
        squareOpenAddressMap.run();


    }
}