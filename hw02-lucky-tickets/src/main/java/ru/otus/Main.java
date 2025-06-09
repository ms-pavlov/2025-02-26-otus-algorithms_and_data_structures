package ru.otus;

public class Main {

    public static void main(String... args) {
        Test test = new Tester(new LuckyTickets());

        test.run();
    }
}
