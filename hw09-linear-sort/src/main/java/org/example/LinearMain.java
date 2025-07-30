package org.example;

public class LinearMain {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка подсчётом" + ConsoleColors.WHITE_BRIGHT);
        Test countingSort = new Tester(CountingSort::new, 7);
        countingSort.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Поразрядная сортировка" + ConsoleColors.WHITE_BRIGHT);
        Test radixSort = new Tester(() -> new RadixSort(5, 10), 7);
        radixSort.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Блочная сортировка" + ConsoleColors.WHITE_BRIGHT);
        Test bucketSort = new Tester(() -> new BucketSort(10), 7);
        bucketSort.run();
    }
}