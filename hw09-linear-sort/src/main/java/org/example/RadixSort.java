package org.example;

public class RadixSort extends Sorting {

    private final int base;
    private final int digits;

    public RadixSort(int base, int digits) {
        this.base = base;
        this.digits = digits;
    }

    @Override
    public void sort(int[] arr) {
        cleanUp();

        int[] T = new int[arr.length];
        int r = 1;
        for (int d = 0; d < digits; d++) {
            int[] radix = new int[10];
            for (int j : arr) {
                int dig = j / r % base;
                radix[dig]++;
                incrementChangeCount();
            }
            int z = 0;
            for (int i = 0; i < base; i++) {
                z += radix[i];
                radix[i] = z;
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                int dig = arr[i] / r % base;
                radix[dig]--;
                T[radix[dig]] = arr[i];
            }
            System.arraycopy(T, 0, arr, 0, T.length);
            r *= base;
        }
    }
}
