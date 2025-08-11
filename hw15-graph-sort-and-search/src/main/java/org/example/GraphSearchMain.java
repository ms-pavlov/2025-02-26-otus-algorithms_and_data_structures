package org.example;

public class GraphSearchMain {
    public static void main(String[] args) {
        Graph<String> graph = new AdjacencyVectorGraph<>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "4");
        graph.addEdge("2", "6");
        graph.addEdge("3", "2");
        graph.addEdge("4", "6");
        graph.addEdge("6", "5");

        graph.print();
    }
}