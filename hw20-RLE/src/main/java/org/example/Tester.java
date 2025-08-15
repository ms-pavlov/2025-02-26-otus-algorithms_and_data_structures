package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.function.Supplier;

public class Tester implements Test {

    private final Supplier<UnZip> initializer;

    public Tester(Supplier<UnZip> initializer) {
        this.initializer = initializer;
    }

    @Override
    public void run() {
        System.out.println(ConsoleColors.WHITE_BRIGHT + "Текст");
        test("text.txt");
        System.out.println(ConsoleColors.WHITE_BRIGHT + "Картинка");
        test("image.jpg");
        System.out.println(ConsoleColors.WHITE_BRIGHT + "Аудио");
        test("audio.mp3");

    }

    private void test(String file) {
        String source = getString(file);
        UnZip unZip = initializer.get();
        long begin = new Date().getTime();
        String zip = unZip.zip(source);
        long end = new Date().getTime();

        System.out.println("Время сжатия " + (end - begin) + "мс");
        System.out.println("Степень сжатия " + (100.0 * zip.length())/source.length() + "%");

        begin = new Date().getTime();
        String result = unZip.unzip(zip);
        end = new Date().getTime();

        System.out.println("Время распаковки " + (end - begin) + "мс");
        System.out.println("Тест " + (source.equals(result) ? ConsoleColors.GREEN_BOLD + "ПРОЙДЕН" : ConsoleColors.RED_BOLD + "НЕ ПРОЙДЕН") + ConsoleColors.WHITE_BRIGHT);
    }

    private String getString(String file) {
        try {
            URL url = Resources.getResource(file);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
