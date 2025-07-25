package org.example;

public class ShakeSort extends Sorting {
    @Override
    public void sort(int[] arr) {
        cleanUp();
        int left = 0;
        int right = arr.length - 1;
        boolean flag;
        while (left < right) {
            flag = false;
            for (int j = right; j > left; j--) {
                if (compare(arr, j, j - 1) < 0) {
                    flag = true;
                    swap(arr, j, j - 1);
                }
            }
            left++;
            for (int j = left; j < right; j++) {
                if (compare(arr, j, j + 1) > 0) {
                    flag = true;
                    swap(arr, j, j + 1);
                }
            }
            right--;
            if (!flag) {
                return;
            }
        }
    }
}
