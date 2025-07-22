package org.example;

import org.example.model.IArray;
import org.example.model.SpaceArray;

public class SpaceArrayFactory implements ArrayFactory{

    private final int vector;

    public SpaceArrayFactory(int vector) {
        this.vector = vector;
    }


    @Override
    public <T> IArray<T> create() {
        return new SpaceArray<>(vector);
    }
}
