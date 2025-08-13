package org.example;

import java.util.Arrays;

public class DeikstraForUndirectedGraph<V> implements ShortWay<V> {

    private final Graph<V> graph;
    private boolean[] visited;

    public DeikstraForUndirectedGraph(Graph<V> graph) {
        this.graph = graph;
    }

    @Override
    public int[] getWay(V v) {
        IArray<V> vertexes = graph.getVertexes();

        int[] ways = new int[vertexes.size()];
        Arrays.fill(ways, Integer.MAX_VALUE);
        visited = new boolean[vertexes.size()];
        Arrays.fill(visited, false);
        ways[vertexes.indexOf(v)] = 0;
        int min;

        for (int k = 0; k < vertexes.size(); k++) {
            min = getMin(ways);
            visited[min] = true;
            if (min >= 0) {
                V minVertex = vertexes.get(min);
                int finalMin = min;
                graph.getEdges().stream()// перебор всех ребер
                        .forEach(edge -> {
                            if (edge.getV1().equals(minVertex)) {
                                int weight = ways[finalMin] + edge.getWeight();
                                if (weight < ways[vertexes.indexOf(edge.getV2())]) {
                                    ways[vertexes.indexOf(edge.getV2())] = weight;
                                }
                            }
                            if (edge.getV2().equals(minVertex)) {
                                int weight = ways[finalMin] + edge.getWeight();
                                if (weight < ways[vertexes.indexOf(edge.getV1())]) {
                                    ways[vertexes.indexOf(edge.getV1())] = weight;
                                }
                            }
                        });
            }

        }
        return ways;
    }


    private int getMin(int[] mas) {
        int min = -1;
        for (int i = 0; i < mas.length; i++) {
            if (!visited[i]) {
                if (min == -1) {
                    min = i;
                } else if (mas[i] < mas[min]) {
                    min = i;
                }
            }
        }
        return min;
    }

}
