package org.example;

public class SearchBMH extends AbstractSearch {

    private int[] shift;

    protected SearchBMH(String text, String mask) {
        super(text, mask);
        prepare();
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
            t += shift[getText().charAt(t + getMask().length() - 1)];
        }
        return -1;
    }

    private void prepare() {
        int size = 256;
        shift = new int[size];
        for (int i = 0; i < size; i++)
            shift[i] = getMask().length();
        for (int i = 0; i < getMask().length() - 1; i++)
            shift[getMask().charAt(i)] = getMask().length() - i - 1;

    }
}
