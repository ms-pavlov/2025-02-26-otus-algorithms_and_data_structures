package org.example;

public class GraphSearchMain {
    public static void main(String[] args) {
        Graph<String> graph = new AdjacencyVectorGraph<>();

        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");

        graph.addEdge("1", "2");
        graph.addEdge("1", "4");
        graph.addEdge("2", "6");
        graph.addEdge("3", "2");
        graph.addEdge("4", "6");
        graph.addEdge("6", "5");

        graph.print();
        System.out.println();
        System.out.println("Алгоритм Демукрона");
        graph.printDemukron();
        System.out.println("Алгоритм Тарьяна");
        graph.printTarian();
        System.out.println();

        graph.addEdge("5", "4");
        graph.addEdge("5", "2");

        graph.print();
        System.out.println();
        System.out.println("Алгоритм Демукрона");
        graph.printDemukron();
        System.out.println();

    }
}