package org.example;

public abstract class Sorting {

    private long comparisonCount = 0;
    private long changeCount = 0;

    protected Sorting() {

    }

    public int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public void incrementChangeCount() {
        changeCount++;
    }

    public void incrementComparisonCount() {
        comparisonCount++;
    }


    public void cleanUp() {
        comparisonCount = 0;
        changeCount = 0;
    }

    public void printMetric() {
        System.out.printf("Сравнений: %d", comparisonCount);
        System.out.println();
        System.out.printf("Обменов: %d", changeCount);
        System.out.println();
    }

    public abstract void sort(int[] arr);
}
