package org.example;

import java.util.Arrays;

public class PrimForUndirectedGraph implements MinSpanningFactory {

    private IArray<Integer> vertexes;
    private IArray<Edge> edges;
    private GraphState[] state;
    private final int begin;

    public PrimForUndirectedGraph(int begin) {
        this.begin = begin;
    }


    @Override
    public Edge[] build(Graph<Integer> graph) {
        vertexes = graph.getVertexes();
        edges = graph.getEdges();
        state = new GraphState[vertexes.size()];

        Arrays.fill(state, GraphState.NONE);
        state[begin] = GraphState.SEEN;
        int cnt = 1;

        IArray<Edge> result = new VectorArray<>();
        while (cnt < vertexes.size()) {
            Edge edge = searchMin();
            result.add(edge);
            state[vertexes.indexOf(edge.getV2())] = GraphState.SEEN;
            state[vertexes.indexOf(edge.getV1())] = GraphState.SEEN;
            cnt++;
        }

        return result.stream().toArray(Edge[]::new);
    }

    private Edge searchMin() {
        Edge result = null;
        int minWeight = Integer.MAX_VALUE;

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int indexV1 = vertexes.indexOf(edge.getV1());
            int indexV2 = vertexes.indexOf(edge.getV2());
            if ((state[indexV1] != GraphState.NONE && state[indexV2] == GraphState.NONE)
                || (state[indexV2] != GraphState.NONE && state[indexV1] == GraphState.NONE)) {
                if (edge.getWeight() < minWeight){
                    result = edge;
                    minWeight = edge.getWeight();
                }
            }
        }
        return result;
    }


    @Override
    public Edge[] buildByAdjacencyVector(Integer[][] graph) {
        return build(new AdjacencyVectorGraph(graph));
    }
}
