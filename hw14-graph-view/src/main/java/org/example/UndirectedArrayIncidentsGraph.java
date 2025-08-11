package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UndirectedArrayIncidentsGraph<V> implements Graph<V> {

    private final List<V> vertex;
    private final List<String> edges;
    private int[][] incidents;

    public UndirectedArrayIncidentsGraph() {
        this.vertex = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.incidents = new int[0][0];
    }


    @Override
    public void addEdge(V v, V v1) {
        if(!vertex.contains(v)) {
            vertex.add(v);
            int[][] newIncidents = new int[vertex.size()][edges.size()];
            System.arraycopy(incidents, 0, newIncidents, 0, incidents.length);
            incidents = newIncidents;
        }
        if(!vertex.contains(v1)) {
            vertex.add(v1);
            int[][] newIncidents = new int[vertex.size()][edges.size()];
            System.arraycopy(incidents, 0, newIncidents, 0, incidents.length);
            incidents = newIncidents;
        }
        String edgeName = generateEdge(v, v1);
        if(!edges.contains(edgeName)) {
            edges.add(edgeName);
            for (int i = 0; i < incidents.length; i++) {
                int[] newLine = new int[edges.size()];
                System.arraycopy(incidents[i], 0, newLine, 0, incidents[i].length);
                incidents[i] = newLine;
            }
        }

        incidents[vertex.indexOf(v)][edges.indexOf(edgeName)] = 1;
        incidents[vertex.indexOf(v1)][edges.indexOf(edgeName)] = 1;
    }

    @Override
    public void addVertex(V v) {
        if(!vertex.contains(v)) {
            vertex.add(v);
            int[][] newIncidents = new int[vertex.size()][edges.size()];
            System.arraycopy(incidents, 0, newIncidents, 0, incidents.length);
            incidents = newIncidents;
        }
    }

    private String generateEdge(V v, V v1) {
        return v + " - " + v1;
    }

    @Override
    public void print() {
        System.out.printf("     %s",
                edges.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" | ")));
        System.out.println();
        for (var item : vertex) {
            System.out.printf("%s |    %2s",
                    item,
                    Arrays.stream(incidents[vertex.indexOf(item)])
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining("    |    ")));
            System.out.println();
        }
    }
}
