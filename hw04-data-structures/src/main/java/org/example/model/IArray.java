package org.example.model;

public interface IArray<T> {
    int size();
    void add(T item);
    void add(T item, int index);
    T remove(int index);
    T get(int index);
    // HW
    // void add(T item, int index); // with shift to tail
    // T remove(int index); // return deleted element
}
