package org.example;

import org.example.model.IArray;

public interface ArrayFactory {

    <T> IArray<T> create();
}
