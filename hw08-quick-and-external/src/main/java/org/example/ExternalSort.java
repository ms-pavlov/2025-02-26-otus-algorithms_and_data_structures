package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExternalSort {

    private final static String OUT_FILE = "out.txt";
    private final static String TMP1_FILE = "tmp1.txt";
    private final static String TMP2_FILE = "tmp2.txt";

    private final int count;

    public ExternalSort(int count) {
        this.count = count;
    }

    public void sort(String pathString) {

        delete(OUT_FILE);
        delete(TMP1_FILE);
        delete(TMP2_FILE);

        ArrayFileReader fileReader = new ArrayFileReader(pathString);

        List<Integer> outList = fileReader.readInteger(count);
        outList.sort(Integer::compareTo);
        delete(TMP1_FILE);
        writeArray(TMP1_FILE, outList);

        while (!fileReader.isEnd()) {
            List<Integer> values = fileReader.readInteger(count);
            values.sort(Integer::compareTo);
            delete(TMP2_FILE);
            writeArray(TMP2_FILE, values);

            merge(OUT_FILE, TMP1_FILE, TMP2_FILE);
            move(OUT_FILE, TMP1_FILE);
        }
        move(TMP1_FILE, OUT_FILE);
        fileReader.close();
    }

    public static void merge(String out, String tmp1, String tmp2) {

        ArrayFileReader f1 = new ArrayFileReader(tmp1);
        ArrayFileReader f2 = new ArrayFileReader(tmp2);
        ArrayFileWriter outFile = new ArrayFileWriter(out);

        Integer item1 = f1.readInteger();
        Integer item2 = f2.readInteger();

        while (!f1.isEnd() && !f2.isEnd()) {
            if (item1 < item2) {
                item1 = writeAndGetNext(outFile, item1, f1);
            } else {
                item2 = writeAndGetNext(outFile, item2, f2);
            }
        }

        while (!f1.isEnd()) {
            item1 = writeAndGetNext(outFile, item1, f1);
        }
        while (!f2.isEnd()) {
            item2 = writeAndGetNext(outFile, item2, f2);
        }

        f1.close();
        f2.close();
        outFile.close();
    }

    private static Integer writeAndGetNext(ArrayFileWriter outFile, Integer item1, ArrayFileReader f1) {
        outFile.writeLine(Integer.toString(item1));
        return f1.readInteger();
    }

    public static void move(String source, String target) {
        try {
            Path targetPath = Paths.get(target);
            if (Files.exists(targetPath)) {
                Files.delete(targetPath);
            }
            Files.move(Paths.get(source), targetPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeArray(String path, List<Integer> arr) {
        ArrayFileWriter outFile = new ArrayFileWriter(path);
        for (int item : arr) {
            outFile.writeLine(Integer.toString(item));
        }
        outFile.close();
    }

    public static void delete(String pathString) {
        try {
            Path path = Paths.get(pathString);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
