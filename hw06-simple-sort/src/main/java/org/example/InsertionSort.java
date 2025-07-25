package org.example;

public class InsertionSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            while (index >= 0) {
                if (compare(arr, index, index + 1) <= 0) {
                    break;
                }
                swap(arr, index, index + 1);
                index--;
            }
        }

    }
}
