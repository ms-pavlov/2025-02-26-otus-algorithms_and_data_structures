package org.example;

public class ShellSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        int len = arr.length;
        int halfLen = len / 2;
        for (int gap = halfLen; gap > 0; gap /= 2) {
            for (int j = gap; j < len; j++) {
                for (int i = j; i >= gap; i -= gap) {
                    if (compare(arr, i - gap, i) <= 0) {
                        break;
                    }
                    swap(arr, i - gap, i);
                }
            }
        }

    }
}
