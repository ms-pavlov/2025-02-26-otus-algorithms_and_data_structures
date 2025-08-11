package org.example;

import java.util.Arrays;

public class GraphSearchMain {
    public static void main(String[] args) {
        Graph<String> graph = new AdjacencyVectorGraph(
                new Integer[][]{
                        {1,3},
                        {5, null},
                        {1, null},
                        {5, null},
                        {null, null},
                        {4, null}
                }
        );

        graph.print();
        System.out.println();
        System.out.println("Алгоритм Демукрона");
        System.out.println(Arrays.deepToString(graph.demukron()));
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