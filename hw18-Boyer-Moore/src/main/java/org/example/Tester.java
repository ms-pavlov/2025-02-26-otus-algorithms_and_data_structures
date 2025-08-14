package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.function.BiFunction;

public class Tester implements Test {

    private final static int N = 10000;
    public static final String FILE = "text.txt";

    private final BiFunction<String, String, Search> initializer;

    public Tester(BiFunction<String, String, Search> initializer) {
        this.initializer = initializer;
    }

    @Override
    public void run() {

        String text = getText();

        System.out.println("Среднее время выполнения " + N + " повторений " + (test(text, "substantial ", 12105) +
                test(text, "Dream-like", 9387) +
                test(text, "Mort saw", 8747) +
                test(text, "His free hand", 7035) +
                test(text, "SEE THE MAN", 6012) +
                test(text, "The appearance of Death", 3924)) / 6 + "мс");
    }

    private long test(String text, String mask, int expect) {
        System.out.println(ConsoleColors.WHITE_BRIGHT + "Шаблон: " + mask);

        int result = -2;

        boolean flag = true;
        Search search = initializer.apply(text, mask);

        long begin = new Date().getTime();
        for (int i = 0; i < N; i++) {
            result = search.run();
            if (result != expect) {
                flag = false;
            }
        }
        long end = new Date().getTime();


        System.out.println("Время выполнения " + N + " повторений " + (end - begin) + "мс");
        System.out.println("Позиция: " + result);
        System.out.println("Тест " + (flag ? ConsoleColors.GREEN_BOLD + "ПРОЙДЕН" : ConsoleColors.RED_BOLD + "НЕ ПРОЙДЕН") + ConsoleColors.WHITE_BRIGHT);
        return (end - begin);
    }

    private String getText() {
        try {
            URL url = Resources.getResource(FILE);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
