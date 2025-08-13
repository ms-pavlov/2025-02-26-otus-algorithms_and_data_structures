package org.example;

public interface MinSpanningFactory {

    Edge[] build(Graph<Integer> graph);

    Edge[] buildByAdjacencyVector(Integer[][] graph);
}
