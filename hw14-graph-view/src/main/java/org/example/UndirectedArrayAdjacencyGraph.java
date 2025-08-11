package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UndirectedArrayAdjacencyGraph<V> implements Graph<V> {
    private final List<V> vertex;
    private int[][] edges;

    public UndirectedArrayAdjacencyGraph() {
        this.vertex = new ArrayList<>();
        this.edges = new int[0][0];
    }


    @Override
    public void addEdge(V v, V v1) {
        if (!vertex.contains(v)) {
            vertex.add(v);
            resize();
        }
        if (!vertex.contains(v1)) {
            vertex.add(v1);
            resize();
        }

        int i = vertex.indexOf(v);
        int j = vertex.indexOf(v1);

        edges[i][j] = 1;
        edges[j][i] = 1;
    }

    @Override
    public void addVertex(V v) {
        if (!vertex.contains(v)) {
            vertex.add(v);
            resize();
        }
    }

    @Override
    public void print() {
        System.out.printf("     %s",
                vertex.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" | ")));
        System.out.println();
        for (var item : vertex) {
            System.out.printf("%s |  %2s",
                    item,
                    Arrays.stream(edges[vertex.indexOf(item)])
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" |  ")));
            System.out.println();
        }
    }

    private void resize() {
        int[][] old = edges;
        edges = Arrays.copyOf(edges, vertex.size());
        for (int i = 0; i < old.length; i++) {
            int[] newLine = new int[vertex.size()];
            System.arraycopy(old[i], 0, newLine, 0, old[i].length);
            edges[i] = newLine;

        }
        edges[edges.length - 1] = new int[vertex.size()];
    }
}
