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

    public int binarySearch(int[] arr, int key, int left, int right) {
        comparisonCount++;
        if (left > right)
            return left;
        int mid = (left + right) / 2;
        comparisonCount++;
        if (key < arr[mid]) {
            return binarySearch(arr, key, left, mid - 1);
        }
        return binarySearch(arr, key, mid + 1, right);
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

    public void shiftRight(int[] arr, int begin, int end) {
        arr[end] = arr[begin];
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
