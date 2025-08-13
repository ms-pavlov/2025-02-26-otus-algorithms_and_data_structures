package org.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdjacencyVectorGraph implements Graph<Integer> {

    private final IArray<Integer> vertex;
    private Integer[][] vectors;
    private int max;

    public AdjacencyVectorGraph() {
        this.vertex = new VectorArray<>();
        max = 0;
        vectors = new Integer[0][max];
    }

    public AdjacencyVectorGraph(Integer[][] vectors) {
        this.vertex = new VectorArray<>();
        this.vectors = vectors;
        if (vectors.length > 0) {
            max = vectors[0].length;
        }

        for(int i = 0; i < vectors.length; i++) {
            vertex.add(i);
        }
    }


    @Override
    public void addEdge(Integer v, Integer v1, int weight) {
        if (!vertex.contains(v)) {
            addVertex(v);
        }
        if (!vertex.contains(v1)) {
            addVertex(v1);
        }
        if (Arrays.stream(vectors[vertex.indexOf(v)]).noneMatch(value -> value != null && value == vertex.indexOf(v1))) {
            int count = (int) Arrays.stream(vectors[vertex.indexOf(v)])
                    .filter(Objects::nonNull)
                    .count();
            if (count >= max) {
                resize();
            }
            vectors[vertex.indexOf(v)][count] = vertex.indexOf(v1);
        }

    }

    @Override
    public void addVertex(Integer v) {
        if (!vertex.contains(v)) {
            vertex.add(v);
            Integer[][] newIncidents = new Integer[vertex.size()][max];
            System.arraycopy(vectors, 0, newIncidents, 0, vectors.length);
            vectors = newIncidents;
        }
    }

    @Override
    public IArray<Edge> getEdges() {
        IArray<Edge> result = new VectorArray<>();

        for (int i = 0; i < vectors.length; i++) {
            for (var item :  vectors[i]) {
                result.add(new Edge(vertex.get(i), vertex.get(item), 1));
            }
        }

        return result;
    }

    @Override
    public IArray<Integer> getVertexes() {
        return vertex;
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
        for (int i = 0; i < vertex.size(); i++) {
            System.out.printf("%s |  %5s",
                    vertex.get(i),
                    Arrays.stream(vectors[i])
                            .map(value -> null != value ? vertex.get(value) : "--")
                            .map(String::valueOf)
                            .collect(Collectors.joining(" |  ")));
            System.out.println();
        }
    }

}
