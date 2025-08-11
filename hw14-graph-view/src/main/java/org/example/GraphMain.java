package org.example;

public class GraphMain {

    public static void main(String[] args) {
        System.out.println("Перечисление множеств");
        System.out.println();
        DemoGraph.print(new SetGraph<>());

        System.out.println();
        System.out.println("Матрица смежности для ненаправленного графа");
        System.out.println();
        DemoGraph.print(new UndirectedArrayAdjacencyGraph<>());

        System.out.println();
        System.out.println("Матрица инцидентности для ненаправленного графа");
        System.out.println();
        DemoGraph.print(new UndirectedArrayIncidentsGraph<>());

        System.out.println();
        System.out.println("Перечень рёбер");
        System.out.println();
        DemoGraph.print(new EdgesListGraph<>());

        System.out.println();
        System.out.println("Вектор смежности");
        System.out.println();
        DemoGraph.print(new AdjacencyVectorGraph<>());

        System.out.println();
        System.out.println("Список смежности");
        System.out.println();
        DemoGraph.print(new AdjacencyListGraph<>());
    }
}