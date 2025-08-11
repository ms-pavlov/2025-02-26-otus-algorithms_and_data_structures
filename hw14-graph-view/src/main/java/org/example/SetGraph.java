package org.example;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SetGraph<V> implements Graph<V>{
    private final Set<V> vertex;
    private final Table<V, V, String> edges;

    public SetGraph() {
        this.edges = HashBasedTable.create();
        this.vertex = new HashSet<>();
    }


    private String generateEdge(V v, V v1) {
        return v + " - " + v1;
    }

    @Override
    public void addEdge(V v, V v1) {
        vertex.add(v);
        vertex.add(v1);
        edges.put(v, v1, generateEdge(v, v1));
    }

    @Override
    public void addVertex(V v) {
        vertex.add(v);
    }

    @Override
    public void print() {
        System.out.printf("V = {%s}", vertex.stream().map(Objects::toString).collect(Collectors.joining(",")));
        System.out.println();
        System.out.printf("E = {%s}", String.join(",", edges.values()));
        System.out.println();
    }

}
