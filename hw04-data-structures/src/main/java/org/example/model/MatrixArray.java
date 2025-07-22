package org.example.model;

public class MatrixArray<T> implements IArray<T> {

    private int size;
    private final int vector;
    private final IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    public MatrixArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector)
            array.add(new VectorArray<>(vector));
        array.get(size / vector).add(item);
        size ++;
    }

    @Override
    public void add(T item, int index) {
        if(index >= size) {
            add(item);
            return;
        }
        if (size == array.size() * vector) {
            array.add(new VectorArray<>(vector));
        }
        for(int i = size / vector ; i > index / vector; i --) {
            array.get(i).add(array.get(i - 1).remove(vector-1), 0);
        }
        array.get(index / vector).add(item, index % vector);
    }

    @Override
    public T remove(int index) {
        if(index >= size) {
            return null;
        }
        T result = get(index);
        array.get(index / vector).remove(index % vector);
        for(int i = index / vector; i < size / vector; i ++) {
            array.get(i).add(array.get(i + 1).remove(0));
        }
        return result;
    }

    @Override
    public T get(int index) {
        return array.get(index / vector).get(index % vector);
    }
}
