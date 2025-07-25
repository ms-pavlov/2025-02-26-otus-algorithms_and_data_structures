package org.example;

public class SelectionSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (compare(arr, j, min) < 0) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }

    }
}
