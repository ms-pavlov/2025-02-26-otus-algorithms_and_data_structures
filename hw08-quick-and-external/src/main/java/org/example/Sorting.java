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

    public int split(int[] arr, int left, int right) {
        int pivot = arr[right];
        int median = left - 1;
        for (int j = left; j <= right; j++) {
            comparisonCount++;
            if (pivot >= arr[j]) {
                median++;
                if (median != j) {
                    changeCount++;
                    swap(arr, median, j);
                }
            }
        }
        return median;
    }


    public void incrementChangeCount() {
        changeCount++;
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
