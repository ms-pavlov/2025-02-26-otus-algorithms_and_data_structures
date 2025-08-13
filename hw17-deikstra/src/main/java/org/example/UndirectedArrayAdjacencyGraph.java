package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UndirectedArrayAdjacencyGraph<V> implements Graph<V> {

    private final IArray<V> vertex;
    private Integer[][] edges;

    public UndirectedArrayAdjacencyGraph() {
        this.vertex = new VectorArray<>();
        this.edges = new Integer[0][0];
    }


    @Override
    public void addEdge(V v, V v1, int weight) {
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

        edges[i][j] = weight;
        edges[j][i] = weight;
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
        for (int i = 0; i < vertex.size(); i++) {
            var item = vertex.get(i);
            System.out.printf("%s |  %2s",
                    item,
                    Arrays.stream(edges[vertex.indexOf(item)])
                            .map(String::valueOf)
                            .collect(Collectors.joining(" |  ")));
            System.out.println();
        }
    }

    @Override
    public IArray<Edge<V>> getEdges() {
        IArray<Edge<V>> result = new VectorArray<>();

        for (int i = 0; i < edges.length; i++) {
            for (int j = i+1; j < edges[i].length; j++) {
                if(null != edges[i][j]) {
                    result.add(new Edge<>(vertex.get(i), vertex.get(j), edges[i][j]));
                }
            }
        }

        return result;
    }

    @Override
    public IArray<V> getVertexes() {
        return vertex;
    }


    private void resize() {
        Integer[][] old = edges;
        edges = Arrays.copyOf(edges, vertex.size());
        for (int i = 0; i < old.length; i++) {
            Integer[] newLine = new Integer[vertex.size()];
            System.arraycopy(old[i], 0, newLine, 0, old[i].length);
            edges[i] = newLine;

        }
        edges[edges.length - 1] = new Integer[vertex.size()];
    }

}
