package org.example;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Resources;
import net.agkn.hll.HLL;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Tester implements Test {

    private final static int N = 10000;
    public static final String FILE = "text.txt";

    private final HashFunction hash;

    public Tester(HashFunction hash) {
        this.hash = hash;
    }


    @Override
    public void run() {

        String text = getText();

        String[] words = text.split("[\\s\t\r\n(),. —«»:;?!]+");

        Set<String> strings = new HashSet<>();

        HLL hll = new HLL(14, 5);

        for (String word : words) {
            strings.add(word.toLowerCase());
            long hashedValue =  hash.newHasher().putString(word.toLowerCase(), Charsets.UTF_8).hash().asLong();
            hll.addRaw(hashedValue);
        }

        long cardinality = hll.cardinality();
        long size = strings.size();
        System.out.println("Количество уникальных слов(приблизительно):" + hll.cardinality());
        System.out.println("Количество уникальных слов(точно): " + strings.size());
        System.out.println("Расхождение: " + Math.abs(size - cardinality));
        System.out.println("Процент ошибок: " + ((100.0*Math.abs(size - cardinality))/strings.size()) +" %");
        System.out.println("Количество слов:" + words.length);
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
