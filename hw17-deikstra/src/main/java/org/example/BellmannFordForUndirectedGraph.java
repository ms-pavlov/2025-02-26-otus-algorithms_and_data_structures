package org.example;

import java.util.Arrays;

public class BellmannFordForUndirectedGraph<V> implements ShortWay<V> {

    private final Graph<V> graph;

    public BellmannFordForUndirectedGraph(Graph<V> graph) {
        this.graph = graph;
    }

    @Override
    public int[] getWay(V v) {
        IArray<V> vertexes = graph.getVertexes();
        int[] ways = new int[vertexes.size()];

        Arrays.fill(ways, graph.getEdges().stream().mapToInt(Edge::getWeight).sum());
        ways[vertexes.indexOf(v)] = 0;
        graph.getEdges().stream().forEach(
                edge -> {
                    if (edge.getV1().equals(v)) {
                        ways[vertexes.indexOf(edge.getV2())] = edge.getWeight();
                    }
                    if (edge.getV2().equals(v)) {
                        ways[vertexes.indexOf(edge.getV1())] = edge.getWeight();
                    }
                }
        );

        for (int k = 0; k < vertexes.size() - 1; k++) {
            graph.getEdges().stream().forEach( // перебор всех ребер
                    edge -> {
                        if (ways[vertexes.indexOf(edge.getV1())] + edge.getWeight() < ways[vertexes.indexOf(edge.getV2())]) {
                            ways[vertexes.indexOf(edge.getV2())] = ways[vertexes.indexOf(edge.getV1())] + edge.getWeight();
                        }
                        if (ways[vertexes.indexOf(edge.getV2())] + edge.getWeight() < ways[vertexes.indexOf(edge.getV1())]) {
                            ways[vertexes.indexOf(edge.getV1())] = ways[vertexes.indexOf(edge.getV2())] + edge.getWeight();
                        }
                    });
        }
        return ways;

    }
}
