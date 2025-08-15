package org.example;

public class RLEOptimization implements UnZip {
    @Override
    public String zip(String source) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < source.length()) {
            StringBuilder sub = new StringBuilder();
            char curr = source.charAt(i);
            char pos = 0;
            while (i + 1 < source.length() && source.charAt(i) != source.charAt(i + 1) && pos < Character.MAX_VALUE/2) {
                sub.append(source.charAt(i));
                pos++;
                i++;
            }

            pos = (char) (-pos);

            if (pos == 0) {
                while (i + pos < source.length() && curr == source.charAt(i + pos) && pos < Character.MAX_VALUE) {
                    pos++;
                }
                i += pos;
                sub.append(curr);
            }

            sb.append(pos);
            sb.append(sub);
        }
        return sb.toString();
    }

    @Override
    public String unzip(String source) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < source.length()) {
            char pos = source.charAt(i);
            if (pos >= Character.MAX_VALUE/2) {
                pos = (char) -pos;
                for (int j = 1; j <= pos; j++) {
                    sb.append(source.charAt(i + j));
                }
                i += pos + 1;
            } else {
                char curr = source.charAt(i + 1);
                for (char j = 0; j < pos; j++) {
                    sb.append(curr);
                }
                i += 2;
            }
        }
        return sb.toString();
    }
}
