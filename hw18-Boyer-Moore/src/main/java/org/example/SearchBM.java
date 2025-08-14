package org.example;

public class SearchBM extends AbstractSearch {

    private int[] suffix;

    protected SearchBM(String text, String mask) {
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
            int c = getMask().length() - 1 - m;
            t += suffix[c];
        }
        return -1;
    }

    private void prepare() {
        suffix = new int[getMask().length()];
        suffix[0] = 1;
        for (int i = 1; i < getMask().length(); i++) // совпавший суффикс
        {
            for (int k = 1; k <= getMask().length(); k++) // поиск такого же суффикса
            {
                int cnt = 0;
                for (int j = 0; j < i; j++) // количество совпавших символов
                {
                    if ((j + k + 1) > getMask().length()) // вышли за границы шаблона
                        break;
                    if (getMask().charAt(getMask().length() - 1 - j) != getMask().charAt(getMask().length() - 1 - j - k))
                        break;             // суффиксы не совпали
                    cnt++;
                }
                if (cnt < i)         // сравниваем начало и конец шаблона
                {
                    if ((k + cnt) == getMask().length()) // начало и конец совпали на cnt символов
                    {
                        suffix[i] = k;
                        break;
                    }
                }
                suffix[i] = k; // i символов совпали, между совпадениями k позиций
                break;
            }
        }


    }
}
