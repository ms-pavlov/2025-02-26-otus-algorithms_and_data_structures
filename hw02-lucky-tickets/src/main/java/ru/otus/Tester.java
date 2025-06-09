package ru.otus;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tester implements Test {

    private final LuckyTickets luckyTickets;

    public Tester(LuckyTickets luckyTickets) {
        this.luckyTickets = luckyTickets;
    }

    @Override
    public void run() {
        int iterator = 0;

        String in = getFirstLine("test." + iterator + ".in");
        String out = getFirstLine("test." + iterator + ".out");

        while (in != null && out != null) {

            int n = Integer.parseInt(in.trim());
            BigInteger expect = new BigInteger(out.trim());

            System.out.println("Тест для значения N = " + n);
            System.out.println("Ожидаемой значение " + expect);
            System.out.println("Тест " + (expect.compareTo(luckyTickets.getLuckyTicketsCount(n)) == 0 ? "ПРОЙДЕН" : "НЕ ПРОЙДЕН"));


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
