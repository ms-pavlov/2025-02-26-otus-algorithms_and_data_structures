package org.example;

import java.util.Arrays;

public class SpanningMain {
    public static void main(String[] args) {

        Graph<Integer> graph = new UndirectedArrayAdjacencyGraph();
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 5, 1);
        graph.addEdge(1, 7, 3);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 2);
        graph.addEdge(5, 6, 4);
        graph.addEdge(6, 7, 2);

        System.out.println(Arrays.toString(new Craskal().build(graph)));
        System.out.println(Arrays.toString(new PrimForUndirectedGraph(0).build(graph)));
    }
}