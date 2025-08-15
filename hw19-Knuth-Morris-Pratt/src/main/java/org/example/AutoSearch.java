package org.example;

import java.util.ArrayList;
import java.util.List;

public class AutoSearch extends AbstractSearch {

    private String alphabet;
    private int[][] delta;
    private final int length;


    protected AutoSearch(String text, String mask) {
        super(text, mask);
        length = getMask().length();
        createAlphabet();
        createDelta();
    }

    @Override
    public List<Integer> run() {
        List<Integer> res = new ArrayList<>();
        int z = 0;
        while (true) {
            z = searchOne(z + 1);
            if (z == -1) return res;
            res.add(z);
        }
    }

    private void createAlphabet() {
        List<Character> alfa = new ArrayList<>();
        for (char c : getText().toCharArray()) {
            if (alfa.contains(c))
                continue;
            alfa.add(c);
        }
        alfa.sort(Character::compareTo);
        StringBuilder sb = new StringBuilder();
        for (char c = alfa.get(0); c <= alfa.get(alfa.size() - 1); c++)
            sb.append(c);
        alphabet = sb.toString();
    }

    private void createDelta() {
        delta = new int[length + 1][alphabet.length()];
        for (int i = 0; i <= length; i++) {
            for (char c : alphabet.toCharArray()) {
                int k = i + 1;
                if (k > length)
                    k--;
                String line = left(getMask(),i) + c;
                while ((k >=0) && ((!left(getMask(), k).equals(right(line, k)))))
                    k--;

                delta[i][c - alphabet.charAt(0)] = k;
            }
        }
    }

    private int searchOne(int pos) {
        int q = 0;
        for (int i = pos; i < getText().length(); i++) {
            q = delta[q][getText().charAt(i) - alphabet.charAt(0)];
            if (q == length)
                return i - length + 1;
        }
        return -1;
    }

    private static String left(String line, int x)
    {
        return line.substring(0, x);
    }
    private static String right(String line, int x)
    {
        return line.substring(line.length() - x);
    }
}
