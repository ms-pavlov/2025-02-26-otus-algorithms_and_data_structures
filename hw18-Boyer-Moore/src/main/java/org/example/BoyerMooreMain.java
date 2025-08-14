package org.example;

public class BoyerMooreMain {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Алгоритм полного перебора");
        Test fullScanTest = new Tester(SearchFullScan::new);
        fullScanTest.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Алгоритм обратного полного перебора");
        Test backScanTest = new Tester(BackScanSearch::new);
        backScanTest.run();


        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Оптимизация с использованием сдвига по префиксу шаблона");
        Test BMH1Test = new Tester(SearchBMH1::new);
        BMH1Test.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Оптимизация с использованием сдвига по суффиксу текста");
        Test BMHTest = new Tester(SearchBMH::new);
        BMHTest.run();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Алгоритм Бойера-Мура");
        Test BMTest = new Tester(SearchBM::new);
        BMTest.run();
    }
}