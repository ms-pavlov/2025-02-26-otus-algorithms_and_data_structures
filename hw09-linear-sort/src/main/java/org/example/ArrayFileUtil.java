package org.example;


import java.util.Random;

public class ArrayFileUtil {

    public static void writeRandomArray(String pathString, int count, int maxValue) {
        ArrayFileWriter outFile =  new ArrayFileWriter(pathString);
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            outFile.writeLine(Integer.toString(r.nextInt(maxValue)));
        }
        outFile.close();
    }

}
