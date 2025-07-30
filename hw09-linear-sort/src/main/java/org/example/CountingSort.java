package org.example;

public class CountingSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();

        int len = arr.length;
        int max = getMax(arr);
        int[] T = new int[max + 1];
        int[] Z = new int[len];
        for (int j : arr) {
            T[j]++;
        }
        int z = 0;
        for (int i = 0; i <= max; i++) {
            z += T[i];
            T[i] = z;
            incrementChangeCount();
        }
        for (int i = len - 1; i >= 0; i--) {
            T[arr[i]]--;
            Z[T[arr[i]]] = arr[i];
            incrementChangeCount();
        }
        System.arraycopy(Z, 0, arr, 0, len);

    }
}
