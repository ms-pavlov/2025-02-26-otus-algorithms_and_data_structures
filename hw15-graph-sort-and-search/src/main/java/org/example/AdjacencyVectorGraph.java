package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdjacencyVectorGraph<V> implements Graph<V> {

    private final List<V> vertex;
    private Integer[][] vectors;
    private int max;

    public AdjacencyVectorGraph() {
        this.vertex = new ArrayList<>();
        max = 0;
        vectors = new Integer[0][max];
    }


    @Override
    public void addEdge(V v, V v1) {
        if (!vertex.contains(v)) {
            addVertex(v);
        }
        if (!vertex.contains(v1)) {
            addVertex(v1);
        }
        if(Arrays.stream(vectors[vertex.indexOf(v)]).noneMatch(value -> value != null && value == vertex.indexOf(v1))) {
            int count = (int) Arrays.stream(vectors[vertex.indexOf(v)])
                    .filter(Objects::nonNull)
                    .count();
            if(count >= max) {
                resize();
            }
            vectors[vertex.indexOf(v)][count] = vertex.indexOf(v1);
        }

    }

    @Override
    public void addVertex(V v) {
        if (!vertex.contains(v)) {
            vertex.add(v);
            Integer[][] newIncidents = new Integer[vertex.size()][max];
            System.arraycopy(vectors, 0, newIncidents, 0, vectors.length);
            vectors = newIncidents;
        }
    }
    private void resize() {
        max++;
        Integer[][] old = vectors;
        for (int i = 0; i < old.length; i++) {
            Integer[] newLine = new Integer[max];
            System.arraycopy(old[i], 0, newLine, 0, old[i].length);
            vectors[i] = newLine;
        }
    }

    @Override
    public void print() {
        for (var item : vertex) {
            System.out.printf("%s |  %5s",
                    item,
                    Arrays.stream(vectors[vertex.indexOf(item)])
                            .map(value ->  null != value? vertex.get(value).toString(): "--")
                            .collect(Collectors.joining(" |  ")));
            System.out.println();
        }
    }
}
