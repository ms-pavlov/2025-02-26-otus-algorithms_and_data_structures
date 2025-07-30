package org.example;

import java.util.UUID;

public class CountingFileSort extends FileSorting{

    private final int maxValue;

    public CountingFileSort(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public String sort(String filePath) {
        String outFile = "out-" + UUID.randomUUID() + ".txt";
        ArrayFileReader fileReader = new ArrayFileReader(filePath);
        ArrayFileWriter fileWriter = new ArrayFileWriter(outFile);


        int[] T = new int[maxValue + 1];

        Integer value;
        while ((value = fileReader.readInteger()) != null) {
            T[value]++;
        }

        for(int i = 0; i <= maxValue; i++) {
            for(int j = 0; j<T[i]; j++) {
                fileWriter.writeLine(String.valueOf(i));
            }
        }

        fileWriter.close();
        fileReader.close();
        return outFile;
    }
}
