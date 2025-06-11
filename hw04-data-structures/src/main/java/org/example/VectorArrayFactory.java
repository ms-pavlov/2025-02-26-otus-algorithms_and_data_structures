package org.example;

import org.example.model.IArray;
import org.example.model.VectorArray;

public class VectorArrayFactory implements ArrayFactory{

    private final int vector;

    public VectorArrayFactory(int vector) {
        this.vector = vector;
    }

    @Override
    public <T> IArray<T> create() {
        return new VectorArray<>(vector);
    }
}
