package org.example;

public class HeapSort extends Sorting{
    @Override
    public void sort(int[] arr) {
        heap(arr);
        for(int i=arr.length-1; i>=0; i--)
        {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }

    }


}
