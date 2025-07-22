package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поиска чисел Фибоначчи O(LogN) через умножение матриц");

        Test test3 = new Tester(new MatrixFibonacci());

        test3.run();

        System.out.println("Итеративный O(N) алгоритм поиска чисел Фибоначчи");

        Test test2 = new Tester(new IterativeFibonacci());

        test2.run();

        System.out.println("Рекурсивный O(2^N) алгоритм поиска чисел Фибоначчи");

        Test test1 = new Tester(new RecursiveFibonacci());

        test1.run();
    }
}