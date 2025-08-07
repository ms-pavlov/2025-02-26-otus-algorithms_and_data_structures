package org.example;

public class InsertionSort3 extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int record = arr[i];
            int pos = binarySearch(arr, record, 0, i - 1);
            for (int j = i - 1; j >= pos; j--) {
                shiftRight(arr, j, j + 1);
            }
            arr[pos] = record;
        }

    }
}
