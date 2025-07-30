package org.example;

public class MergeUpSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        mergeSort(arr);
    }

    private void mergeSort(int[] arr) {
        int step = 1;
        int n = arr.length;
        int[] T = new int[n];
        while (step < n) {
            int index = 0;
            int l = 0;
            int m = l + step;
            int r = l + step * 2;
            do {
                m = Math.min(m, n);
                r = Math.min(r, n);
                int i1 = l, i2 = m;
                for (; i1 < m && i2 < r; ) {
                    incrementChangeCount();
                    if (compare(arr, i1, i2) <= 0)
                        T[index++] = arr[i1++];
                    else
                        T[index++] = arr[i2++];
                }
                while (i1 < m) {
                    T[index++] = arr[i1++];
                    incrementChangeCount();
                }
                while (i2 < r) {
                    T[index++] = arr[i2++];
                    incrementChangeCount();
                }
                l += step * 2;
                m += step * 2;
                r += step * 2;
            } while (l < n);
            System.arraycopy(T, 0, arr, 0, n);
            step *= 2;
        }

    }
}
