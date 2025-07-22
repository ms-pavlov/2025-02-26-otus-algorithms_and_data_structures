package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Реализовать алгоритм \"Решето Эратосфена\" для быстрого " +
                "поиска простых чисел O(N Log Log N)");

        Test test3 = new Tester(new Eratosphen());

        test3.run();

        System.out.println("Алгоритм поиска простых чисел с оптимизациями поиска " +
                "и делением только на простые числа, O(N * Sqrt(N) / Ln (N)).");

        Test test2 = new Tester(new OptimalEnumPrime());

        test2.run();

        System.out.println("Алгоритм поиска количества простых чисел через перебор делителей, O(N^2)");

        Test test1 = new Tester(new EnumPrime());

        test1.run();
    }
}