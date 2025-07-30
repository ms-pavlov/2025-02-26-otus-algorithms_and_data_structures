package org.example;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArrayFileReader {

    private final BufferedReader bufferedReader;

    private String line = "begin";

    public ArrayFileReader(String filePath) {
        try {
            this.bufferedReader = Files.newReader(Paths.get(filePath).toFile(), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> readInteger(int count) {
        try {
            List<Integer> outList = new ArrayList<>();
            int i = 0;
            while ((line = bufferedReader.readLine()) != null && i < count) {
                i++;
                if (line.isEmpty()) {
                    line = null;
                } else {
                    outList.add(Integer.parseInt(line));
                }
            }
            return outList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer readInteger() {
        try {
            line = bufferedReader.readLine();
            if (null == line || line.isEmpty()) {
                line = null;
                return null;
            } else {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEnd() {
        return null == line;
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
