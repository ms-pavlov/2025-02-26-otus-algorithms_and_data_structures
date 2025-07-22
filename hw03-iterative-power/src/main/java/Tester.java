import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class Tester implements Test {

    private final Power power;

    public Tester(Power power) {
        this.power = power;
    }

    @Override
    public void run() {
        int iterator = 0;

        do {
            List<String> in = getLines("test." + iterator + ".in");
            String out = getFirstLine("test." + iterator + ".out");

            if (in.size() != 2) {
                throw new RuntimeException("Неверное количество переменных");
            }

            Double base = Double.parseDouble(in.get(0).trim());
            BigInteger pow = new BigInteger(in.get(1).trim());
            Double expect = Double.parseDouble(out.trim());
            long begin = new Date().getTime();
            Double result = power.power(base, pow);
            long end = new Date().getTime();

            System.out.println("Тест для значения " + base + "^" + pow);
            System.out.println("Ожидаемой значение " + expect + ", результат " + result);
            System.out.println("Время выполнения  " + (end - begin) + "мс");
            System.out.println("Тест " + (Math.abs(expect - result) < 0.000001 ? "ПРОЙДЕН" : "НЕ ПРОЙДЕН"));

            iterator++;

        } while (iterator < 10);

    }

    private List<String> getLines(String path) {
        try {
            URL url = Resources.getResource(path);
            return Resources.readLines(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
