package org.example.model;

public class SpaceArray<T> implements IArray<T>{

    private final int vector;
    private int size;
    private final IArray<IArray<T>> array;

    public SpaceArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (array.size() == 0  || array.get(array.size() - 1).size()  == vector) {
            array.add(new VectorArray<>(vector));
        }
        array.get(array.size() - 1).add(item);
        size ++;
    }

    @Override
    public void add(T item, int index) {
        if (array.size() == 0  || array.get(array.size() - 1).size()  == vector) {
            array.add(new VectorArray<>(vector));
        }

        int pointer = 0;
        int offset = 0;

        while(array.get(pointer).size() + offset <= index) {
            offset += array.get(pointer).size();
            pointer ++;
        }
        int next = pointer;
        while(array.get(next).size() == vector) {
            next++;
        }
        for(int i = next; i> pointer; i--) {
            array.get(i).add(array.get(i - 1).remove(vector-1), 0);
        }
        array.get(pointer).add(item, index-offset);
    }

    @Override
    public T remove(int index) {
        int pointer = 0;
        int offset = 0;

        while(array.get(pointer).size() + offset <= index) {
            offset += array.get(pointer).size();
            pointer ++;
        }

        return array.get(pointer).remove(index-offset);
    }

    @Override
    public T get(int index) {
        int pointer = 0;
        int offset = 0;

        while(array.get(pointer).size() + offset <= index) {
            offset += array.get(pointer).size();
            pointer ++;
        }
        return array.get(pointer).get(index-offset);
    }
}
