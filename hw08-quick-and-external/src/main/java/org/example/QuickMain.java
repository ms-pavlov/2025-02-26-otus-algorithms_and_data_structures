package org.example;


import java.io.IOException;
import java.util.Date;
import java.util.List;

public class QuickMain {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Быстрая сортировка" + ConsoleColors.WHITE_BRIGHT);
        Test quickSort = new Tester(QuickSort::new, 7);
        quickSort.run();


        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка слиянием" + ConsoleColors.WHITE_BRIGHT);
        Test mergeUpSort = new Tester(MergeUpSort::new, 7);
        mergeUpSort.run();

        String pathString = "text.txt";
        ArrayFileUtil.writeRandomArray(pathString, 200, 20000);

        List<Integer> sizes = List.of(
                100,
                1000,
                10000,
                100000,
                1000000
        );
        ExternalSort externalSort = new ExternalSort(100);

        for (int size : sizes) {
            ArrayFileUtil.writeRandomArray("text.txt", size, 10);

            long begin = new Date().getTime();
            externalSort.sort(pathString);
            long end = new Date().getTime();

            System.out.println("Время выполнения для N=" + size + ", T = 10: " + (end - begin) + "мс");

            ArrayFileUtil.writeRandomArray("text.txt", size, size);

            begin = new Date().getTime();
            externalSort.sort(pathString);
            end = new Date().getTime();

            System.out.println("Время выполнения для N=" + size + ", T = " + size + ": " + (end - begin) + "мс");

        }
    }


}