package org.example;

import java.util.Arrays;

public class DeikstraMain {
    public static void main(String[] args) {
        Graph<Integer> graph = new UndirectedArrayAdjacencyGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(1, 2, 7);
        graph.addEdge(1, 3, 9);
        graph.addEdge(1, 6, 14);
        graph.addEdge(2, 3, 10);
        graph.addEdge(2, 4, 15);
        graph.addEdge(3, 4, 11);
        graph.addEdge(3, 6, 2);
        graph.addEdge(4, 5, 6);
        graph.addEdge(6, 5, 9);
        System.out.println("Алгоритм Дейкстры");
        ShortWay<Integer> deikstraFord = new DeikstraForUndirectedGraph<>(graph);
        ShortWay<Integer> bellmannFord = new BellmannFordForUndirectedGraph<>(graph);

        bellmannFord.getWay(4);

        graph.getVertexes().stream().forEach(
                v -> {
                    System.out.println("Минимальные пути из вершины " + v + ":");
                    System.out.println(Arrays.toString(deikstraFord.getWay(v)));
        });
        System.out.println("Алгоритм Беллмана-Форда");
        graph.getVertexes().stream().forEach(
                v -> {
                    System.out.println("Минимальные пути из вершины " + v + ":");
                    System.out.println(Arrays.toString(bellmannFord.getWay(v)));
                });
    }
}