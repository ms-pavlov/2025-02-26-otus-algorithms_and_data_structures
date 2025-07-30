package org.example;

import java.util.Date;

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

        String fileBucketSort = "fileBucketSort.txt";
        int maxValue = 65535;
        int count = 1_000_000_00;
        ArrayFileUtil.writeRandomArray(fileBucketSort, count, maxValue);

        FileSorting bucketFileSort = new FileBucketSort(maxValue, maxValue);

        long begin = new Date().getTime();
        System.out.println(bucketFileSort.sort(fileBucketSort));
        long end = new Date().getTime();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Блочная сортировка файла" + ConsoleColors.WHITE_BRIGHT);
        System.out.println("Время выполнения  " + (end - begin) + "мс");

        FileSorting countingFileSort = new CountingFileSort(maxValue);

        begin = new Date().getTime();
        System.out.println(countingFileSort.sort(fileBucketSort));
        end = new Date().getTime();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка подсчётом файла" + ConsoleColors.WHITE_BRIGHT);
        System.out.println("Время выполнения  " + (end - begin) + "мс");

        FileSorting radixFileSort = new RadixFileSort(3, 10);

        begin = new Date().getTime();
        System.out.println(radixFileSort.sort(fileBucketSort));
        end = new Date().getTime();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Поразрядная сортировка файла" + ConsoleColors.WHITE_BRIGHT);
        System.out.println("Время выполнения  " + (end - begin) + "мс");
    }
}