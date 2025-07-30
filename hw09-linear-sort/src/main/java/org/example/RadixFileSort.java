package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RadixFileSort extends FileSorting {

    private final RadixSort radixSort;

    public RadixFileSort( int base, int digits) {
        this.radixSort = new RadixSort(base, digits);
    }

    @Override
    public String sort(String filePath) {
        String outFile = "out-" + UUID.randomUUID() + ".txt";

        ArrayFileReader fileReader = new ArrayFileReader(filePath);
        ArrayFileWriter fileWriter = new ArrayFileWriter(outFile);

        List<Integer> arr = new ArrayList<>();

        Integer line;

        while ((line = fileReader.readInteger()) != null) {
            arr.add(line);
        }
        fileReader.close();


        int[] array = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            array[i] = arr.get(i);
        }

        radixSort.sort(array);

        for(int item : array) {
            fileWriter.writeLine(String.valueOf(item));
        }
        fileWriter.close();
        return outFile;
    }
}
