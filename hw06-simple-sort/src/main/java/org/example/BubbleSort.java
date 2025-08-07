package org.example;

public class BubbleSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = len - 2; j >= 0; j--) {
                if (compare(arr, j, j + 1) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}
