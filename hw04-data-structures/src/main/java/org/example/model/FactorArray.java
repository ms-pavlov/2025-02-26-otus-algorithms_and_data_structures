package org.example.model;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private final int factor;
    private int size;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        size = 0;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    public void add(T item, int index) {
        if(index >= size) {
            add(item);
            return;
        }
        if (size() == array.length) {
            resize();
        }
        System.arraycopy(array, index, array, index+1, size - index);
        array[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        if(index >= size) {
            return null;
        }
        T result = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    private void resize() {
        Object[] newArray = new Object[array.length + array.length * factor / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
