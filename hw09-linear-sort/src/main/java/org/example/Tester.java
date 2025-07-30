package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

public class Tester implements Test {

    private final Supplier<Sorting> initializer;
    private final int count;

    public Tester(Supplier<Sorting> initializer, int count) {
        this.initializer = initializer;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(ConsoleColors.WHITE_BRIGHT + "====    массив из случайных цифр    ====");
        test("digits/");
        System.out.println("====    массив из случайных чисел    ====");
        test("random/");
        System.out.println("====    на 99% отсортированный массив    ====");
        test("sorted/");
        System.out.println("====    обратно-отсортированный массив    ====");
        test("revers/");
    }

    private void test(String path) {
        for (int i = 0; i < count; i++) {
            List<String> in = readLines(path + "test." + i + ".in");
            String out = getFirstLine(path + "test." + i + ".out");

            if(in.size() != 2) {
                throw new RuntimeException("Неверный формат тестового файла");
            }

            int n = Integer.parseInt(in.get(0).trim());
            int[] inputArray = parseArray(n, in.get(1));
            int[] expectArray = parseArray(n, out.trim());
            long begin = new Date().getTime();
            Sorting sorter = initializer.get();
            sorter.sort(inputArray);

            long end = new Date().getTime();

            System.out.println("Тест для значения N = " + n);

            boolean assertFlag = true;
            if (expectArray.length == inputArray.length) {
                for (int j = 0; j < expectArray.length; j ++) {
                    if (expectArray[j] != inputArray[j]) {
                        assertFlag = false;
                        break;
                    }
                }
            } else {
                assertFlag = false;
            }

            System.out.println("Время выполнения  " + (end - begin) + "мс");
            sorter.printMetric();
            System.out.println("Тест " + (assertFlag ? ConsoleColors.GREEN_BOLD + "ПРОЙДЕН" : ConsoleColors.RED_BOLD + "НЕ ПРОЙДЕН") + ConsoleColors.WHITE_BRIGHT);
        }
    }

    private  int[] parseArray(int n, String in) {
        int[] result = new int[n];
        int iterator = 0;
        for (String item : in.split(" ")) {
            result[iterator++] = Integer.parseInt(item);
        }
        return result;
    }

    private String getFirstLine(String path) {
        try {
            URL url = Resources.getResource(path);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<String> readLines(String path) {
        try {
            URL url = Resources.getResource(path);
            return Resources.readLines(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
