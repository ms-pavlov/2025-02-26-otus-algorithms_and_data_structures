package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Tester implements Test {

    private final Prime prime;

    public Tester(Prime prime) {
        this.prime = prime;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            String in = getFirstLine("test." + i + ".in");
            String out = getFirstLine("test." + i + ".out");

            int n = Integer.parseInt(in.trim());
            long expect = Long.parseLong(out.trim());
            long begin = new Date().getTime();
            long result = prime.getCount(n);
            long end = new Date().getTime();

            System.out.println("Тест для значения N = " + n);
            System.out.println("Ожидаемой значение " + expect + ", результат " + result);
            System.out.println("Время выполнения  " + (end - begin) + "мс");
            System.out.println("Тест " + (expect == result ? "ПРОЙДЕН" : "НЕ ПРОЙДЕН"));
        }
    }

    private String getFirstLine(String path) {
        try {
            URL url = Resources.getResource(path);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
