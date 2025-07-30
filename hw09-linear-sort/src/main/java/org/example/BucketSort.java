package org.example;

import java.util.ArrayList;
import java.util.List;

public class BucketSort extends Sorting {

    private final int numBaskets;

    public BucketSort(int numBaskets) {
        this.numBaskets = numBaskets;
    }

    @Override
    public void sort(int[] arr) {
        cleanUp();

        List<Integer>[] baskets = new List[numBaskets];

        int j;
        int max = getMax(arr);

        for (int value : arr) {
            int m = (value * numBaskets) / (max + 1);
            if (baskets[m] == null) {
                baskets[m] = new ArrayList<>();
            }
            if (baskets[m].isEmpty()) {
                incrementChangeCount();
                baskets[m].add(value);
            } else {
                j = 0;
                while ((j < baskets[m].size()) && (baskets[m].get(j) < value)) {
                    j++;
                    incrementComparisonCount();
                }
                baskets[m].add(j, value);
                incrementChangeCount();
            }
        }
        j = 0;
        for (List<Integer> basket : baskets) {
            if (basket == null) {
                continue;
            }
            for (Integer integer : basket) {
                arr[j++] = integer;
            }
        }

    }
}
