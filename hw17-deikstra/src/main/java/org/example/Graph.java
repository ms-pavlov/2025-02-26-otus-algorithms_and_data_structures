package org.example;

public interface Graph<V> {

    void addEdge(V v, V v1, int weight);

    void addVertex(V v);

    IArray<Edge<V>> getEdges();

    IArray<V> getVertexes();

    void print();

}