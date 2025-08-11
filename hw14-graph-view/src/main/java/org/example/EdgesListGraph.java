package org.example;

import java.util.ArrayList;
import java.util.List;

public class EdgesListGraph<V> implements Graph<V>{

    private final List<V> vertex;
    private final List<String> edges;
    private int[][] edgesList;

    public EdgesListGraph() {
        this.vertex = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.edgesList = new int[0][2];
    }


    @Override
    public void addEdge(V v, V v1) {
        String edgeName = generateEdge(v, v1);
        if(!edges.contains(edgeName)) {
            edges.add(edgeName);
            int[][] newIncidents = new int[vertex.size()][2];
            System.arraycopy(edgesList, 0, newIncidents, 0, edgesList.length);
            edgesList = newIncidents;
        }

        edgesList[edges.indexOf(edgeName)][0] = vertex.indexOf(v);
        edgesList[edges.indexOf(edgeName)][1] = vertex.indexOf(v1);
    }

    @Override
    public void addVertex(V v) {
        if (!vertex.contains(v)) {
            vertex.add(v);
        }
    }

    private String generateEdge(V v, V v1) {
        return v + " - " + v1;
    }

    @Override
    public void print() {
        System.out.println("          Начало |  Конец");
        for (var item : edges) {
            System.out.printf("%s |    %s  |     %s",
                    item,
                    vertex.get(edgesList[edges.indexOf(item)][0]),
                    vertex.get(edgesList[edges.indexOf(item)][1]));
            System.out.println();
        }
    }
}
