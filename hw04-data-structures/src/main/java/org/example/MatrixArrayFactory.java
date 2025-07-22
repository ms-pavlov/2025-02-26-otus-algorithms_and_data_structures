package org.example;

import org.example.model.IArray;
import org.example.model.MatrixArray;

public class MatrixArrayFactory implements ArrayFactory{

    private final int vector;

    public MatrixArrayFactory(int vector) {
        this.vector = vector;
    }

    @Override
    public <T> IArray<T> create() {
        return new MatrixArray<>(vector);
    }
}
