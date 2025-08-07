package org.example;

public class PyramidMain {
    public static void main(String[] args) {
//        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Шейкер-сортировка" + ConsoleColors.WHITE_BRIGHT);
//        Test shakeSort = new Tester(ShakeSort::new, 7);
//        shakeSort.run();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка выбором" + ConsoleColors.WHITE_BRIGHT);
        Test selectionSort = new Tester(SelectionSort::new, 7);
        selectionSort.run();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Пирамидальная сортировка" + ConsoleColors.WHITE_BRIGHT);
        Test heapSort = new Tester(HeapSort::new, 7);
        heapSort.run();
    }
}