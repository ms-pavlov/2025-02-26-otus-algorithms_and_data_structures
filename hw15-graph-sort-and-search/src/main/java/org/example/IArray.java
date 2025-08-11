package org.example;

public interface IArray<T> {
    int size();
    void add(T item);
    void add(T item, int index);
    T remove(int index);
    T get(int index);

    boolean contains(T item);
    int indexOf(T item);
}
