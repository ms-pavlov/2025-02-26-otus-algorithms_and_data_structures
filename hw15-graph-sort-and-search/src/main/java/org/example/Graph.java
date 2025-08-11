package org.example;

public interface Graph<V> {

    void addEdge(V v, V v1);

    void addVertex(V v);

    void print();
}