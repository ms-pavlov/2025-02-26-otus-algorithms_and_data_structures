package org.example;

public class SortMain {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Метод «пузырька»" + ConsoleColors.WHITE_BRIGHT);
        Test bubbleSort = new Tester(BubbleSort::new, 7);
        bubbleSort.run();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Метод «пузырька» с улучшением" + ConsoleColors.WHITE_BRIGHT);
        Test bubbleSort2 = new Tester(BubbleSort2::new, 7);
        bubbleSort2.run();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка вставками" + ConsoleColors.WHITE_BRIGHT);
        Test insertionSort = new Tester(InsertionSort::new, 7);
        insertionSort.run();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка вставками с бинарным поиском" + ConsoleColors.WHITE_BRIGHT);
        Test insertionSort3 = new Tester(InsertionSort3::new, 7);
        insertionSort3.run();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Сортировка Шелла" + ConsoleColors.WHITE_BRIGHT);
        Test shellSort = new Tester(ShellSort::new, 7);
        shellSort.run();
    }
}