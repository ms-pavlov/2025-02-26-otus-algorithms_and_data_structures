package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileBucketSort extends FileSorting {

    private final int maxValue;
    private final int numBaskets;

    public FileBucketSort(int maxValue, int numBaskets) {
        this.maxValue = maxValue;
        this.numBaskets = numBaskets;
    }

    @Override
    public String sort(String filePath) {
        String outFile = "out-" + UUID.randomUUID() + ".txt";

        ArrayFileReader fileReader = new ArrayFileReader(filePath);
        ArrayFileWriter fileWriter = new ArrayFileWriter(outFile);

        List<Integer>[] baskets = new List[numBaskets];

        int j;
        Integer value;
        while ((value = fileReader.readInteger()) != null) {
            int m = BigInteger.valueOf((long) value * numBaskets).divide(BigInteger.valueOf(maxValue + 1)).intValue();
            if (baskets[m] == null) {
                baskets[m] = new ArrayList<>();
            }
            if (baskets[m].isEmpty()) {
                baskets[m].add(value);
            } else {
                j = 0;
                while ((j < baskets[m].size()) && (baskets[m].get(j) < value)) {
                    j++;
                }
                baskets[m].add(j, value);
            }
        }

        for (List<Integer> basket : baskets) {
            if (basket != null) {
                for (Integer integer : basket) {
                    fileWriter.writeLine(String.valueOf(integer));
                }
            }
        }

        fileWriter.close();
        fileReader.close();

        return outFile;
    }
}
