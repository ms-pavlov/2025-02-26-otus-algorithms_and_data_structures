package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Date;

public class Tester implements Test {

    private final Fibonacci fibonacci;

    public Tester(Fibonacci fibonacci) {
        this.fibonacci = fibonacci;
    }

    @Override
    public void run() {
        int iterator = 0;

        String in = getFirstLine("test." + iterator + ".in");
        String out = getFirstLine("test." + iterator + ".out");

        while (in != null && out != null) {

            int n = Integer.parseInt(in.trim());
            BigInteger expect = new BigInteger(out.trim());
            long begin = new Date().getTime();
            BigInteger result = fibonacci.get(n);
            long end = new Date().getTime();

            System.out.println("Тест для значения N = " + n);
//            System.out.println("Ожидаемой значение " + expect + ", результат " + result);
            System.out.println("Время выполнения  " + (end - begin) + "мс");
            System.out.println("Тест " + (expect.compareTo(result) == 0 ? "ПРОЙДЕН" : "НЕ ПРОЙДЕН"));


            iterator++;

            in = getFirstLine("test." + iterator + ".in");
            out = getFirstLine("test." + iterator + ".out");
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
