package org.example;

public interface Graph<V> {

    void addEdge(V v, V v1);

    void addVertex(V v);

    IArray<V> demukronAsList();

    int[][] demukron();

    void printDemukron();

    IArray<V> tarian();

    void printTarian();

    void print();
}