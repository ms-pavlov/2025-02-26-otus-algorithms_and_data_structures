package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacencyListGraph<V> implements Graph<V>{

    private final List<V> vertex;
    private final List<List<V>> adjacency;

    public AdjacencyListGraph() {
        this.vertex = new ArrayList<>();
        this.adjacency = new ArrayList<>();
    }


    @Override
    public void addEdge(V v, V v1) {
        if (!vertex.contains(v)) {
            addVertex(v);
        }
        if (!vertex.contains(v1)) {
            addVertex(v1);
        }
        adjacency.get(vertex.indexOf(v)).add(v1);
    }

    @Override
    public void addVertex(V v) {
        if (!vertex.contains(v)) {
            vertex.add(v);
            adjacency.add(new ArrayList<>());
        }
    }

    @Override
    public void print() {
        for (int i = 0; i < adjacency.size(); i++) {
            System.out.printf("%s -> %s",
                    vertex.get(i),
                    adjacency.get(i).stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(" -> ")));
            System.out.println();
        }
    }
}
