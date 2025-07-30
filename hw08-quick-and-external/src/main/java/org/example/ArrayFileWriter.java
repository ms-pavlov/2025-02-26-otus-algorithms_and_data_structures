package org.example;

import com.google.common.io.Files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArrayFileWriter {

    private final BufferedWriter bufferedWriter;

    public ArrayFileWriter(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if(java.nio.file.Files.exists(path)) {
                java.nio.file.Files.delete(path);
            }
            this.bufferedWriter = Files.newWriter(path.toFile(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String value) {
        try {
            this.bufferedWriter.write(value);
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
