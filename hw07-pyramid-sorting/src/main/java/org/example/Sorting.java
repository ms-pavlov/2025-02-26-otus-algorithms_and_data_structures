package org.example;

public abstract class Sorting {

    private long comparisonCount = 0;
    private long changeCount = 0;

    protected Sorting() {

    }


    public void cleanUp() {
        comparisonCount = 0;
        changeCount = 0;
    }

    public void heapify(int[] arr, int root, int size) {
        int p = root;
        int l = 2 * p + 1;
        int r = 2 * p + 2;
        comparisonCount += 2;
        if (l < size && arr[l] > arr[p]) {
            p = l;
        }
        if (r < size && arr[r] > arr[p]) {
            p = r;
        }
        if (p == root) {
            return;
        }
        comparisonCount++;
        swap(arr, root, p);
        heapify(arr, p, size);
    }

    public void heap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }


    public int compare(int[] arr, int begin, int end) {
        comparisonCount++;
        return Integer.compare(arr[begin], arr[end]);
    }

    public void swap(int[] arr, int begin, int end) {
        int tmp = arr[begin];
        arr[begin] = arr[end];
        arr[end] = tmp;
        changeCount++;
    }

    public void printMetric() {
        System.out.printf("Сравнений: %d", comparisonCount);
        System.out.println();
        System.out.printf("Обменов: %d", changeCount);
        System.out.println();
    }

    public abstract void sort(int[] arr);
}
