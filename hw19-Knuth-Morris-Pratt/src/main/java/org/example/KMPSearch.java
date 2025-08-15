package org.example;

import java.util.ArrayList;
import java.util.List;

public class KMPSearch extends AbstractSearch {

    private int[] pi;

    protected KMPSearch(String text, String mask) {
        super(text, mask);
        createPi(mask + "#" + text);
    }

    @Override
    public List<Integer> run() {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < pi.length; i++)
        {
            if (pi[i] == getMask().length())
                res.add(i - getMask().length()*2-1);
        }
        return res;

    }

    private void createPi(String pattern) {
        pi = new int[pattern.length() + 1];
        pi[0] = 0;
        pi[1] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int len = pi[i];
            while ((len > 0) && (pattern.charAt(len) != pattern.charAt(i))) {
                len = pi[len];
            }
            if (pattern.charAt(len) == pattern.charAt(i)) {
                len++;
            }
            pi[i + 1] = len;
        }
    }

}
