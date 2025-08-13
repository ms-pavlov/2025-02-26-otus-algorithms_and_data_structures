package org.example;

public class Craskal implements MinSpanningFactory {

    private int[] parent;

    @Override
    public Edge[] build(Graph<Integer> graph) {
        IArray<Edge> result = new VectorArray<>();

        IArray<Edge> edges = graph.getEdges();
        IArray<Integer> vertexes = graph.getVertexes();
        edges.sort();

        parent = new int[vertexes.size()];
        for (int i = 0; i < vertexes.size(); i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int beg = getParent(vertexes.indexOf(edge.getV1()));
            int end = getParent(vertexes.indexOf(edge.getV2()));
            if (beg != end) {
                result.add(edge);
                parent[end] = beg;
            }
        }

        return result.stream().toArray(Edge[]::new);
    }

    @Override
    public Edge[] buildByAdjacencyVector(Integer[][] graph) {
        return build(new AdjacencyVectorGraph(graph));
    }

    private int getParent(int v) {
        if (parent[v] == v) return v;
        return getParent(parent[v]);
    }

}
