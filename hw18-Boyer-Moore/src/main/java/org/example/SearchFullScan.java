package org.example;

public class SearchFullScan extends AbstractSearch {
    protected SearchFullScan(String text, String mask) {
        super(text, mask);
    }

    @Override
    public int run() {
        int t = 0;
        while (t <= getText().length() - getMask().length()) {
            int m = 0;
            while ((m < getMask().length()) && (getText().charAt(t + m) == getMask().charAt(m))) {
                m++;
            }
            if (m == getMask().length())
                return t;
            t++;
        }
        return -1;

    }
}
