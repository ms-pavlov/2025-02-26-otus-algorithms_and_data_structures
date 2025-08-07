package org.example;

import java.util.Stack;

public class QuickSort extends Sorting{
    @Override
    public void sort(int[] arr) {
        cleanUp();
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right)
    {
        if (arr == null || arr.length == 0)
            return;


        if (left >= right)
            return;


        Stack<Integer> stack = new Stack<>();
        stack.push(left);
        stack.push(right);


        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();

            int median = split(arr, left, right);

            if (median - 1 > left) {
                stack.push(left);
                stack.push(median - 1);
            }

            if (median + 1 < right) {
                stack.push(median + 1);
                stack.push(right);
            }
        }
    }

}
