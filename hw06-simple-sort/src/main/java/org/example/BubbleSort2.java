package org.example;

public class BubbleSort2 extends Sorting {

    @Override
    public void sort(int[] arr) {
        cleanUp();
        boolean flag = false;
        int len = arr.length;
        do {
            if(len > 1) {
                flag = false;
            }
            for (int i = len - 2; i >= 0; i--) {
                if (compare(arr, i, i + 1) > 0) {
                    swap(arr, i, i + 1);
                    flag = true;
                }
            }
        } while (flag);
    }
}
