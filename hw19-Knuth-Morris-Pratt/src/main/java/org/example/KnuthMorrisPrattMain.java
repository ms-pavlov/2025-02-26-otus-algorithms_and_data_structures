package org.example;

public class KnuthMorrisPrattMain {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Построение автомата");
        Test autoTest = new Tester(AutoSearch::new);
        autoTest.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Алгоритм Кнута-Морриса-Пратта");
        Test KMPTest = new Tester(KMPSearch::new);
        KMPTest.run();
    }
}