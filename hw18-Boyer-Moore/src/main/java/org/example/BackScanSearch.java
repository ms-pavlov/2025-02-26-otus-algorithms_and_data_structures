package org.example;

public class BackScanSearch extends AbstractSearch {
    protected BackScanSearch(String text, String mask) {
        super(text, mask);
    }

    @Override
    public int run() {
        int t = 0;
        while (t <= getText().length() - getMask().length()) {
            int m = getMask().length() - 1;
            while ((m >= 0) && (getText().charAt(t + m) == getMask().charAt(m))) {
                m--;
            }
            if (m < 0)
                return t;
            t += 1;
        }
        return -1;
    }
}
