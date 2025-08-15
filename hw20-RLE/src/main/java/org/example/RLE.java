package org.example;

public class RLE implements UnZip{
    @Override
    public String zip(String source) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < source.length()) {
            char curr = source.charAt(i);
            char pos = 1;
            while (i + pos < source.length() && curr == source.charAt(i + pos) && pos < 127 ) {
                pos++;
            }
            sb.append(pos);
            sb.append(curr);
            i+=pos;
        }
        return sb.toString();
    }

    @Override
    public String unzip(String source) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < source.length(); i += 2) {
            char pos = source.charAt(i);
            char curr = source.charAt(i+1);
            for(char j = 0; j < pos; j ++) {
                sb.append(curr);
            }
        }
        return sb.toString();
    }
}
