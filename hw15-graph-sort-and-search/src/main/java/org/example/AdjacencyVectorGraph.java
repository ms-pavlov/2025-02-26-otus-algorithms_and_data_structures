package org.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdjacencyVectorGraph implements Graph<String> {

    private final IArray<String> vertex;
    private GraphState[] state;
    private Integer[][] vectors;
    private int max;

    public AdjacencyVectorGraph() {
        this.vertex = new VectorArray<>();
        max = 0;
        vectors = new Integer[0][max];
        state = new GraphState[0];
    }

    public AdjacencyVectorGraph(Integer[][] vectors) {
        this.vertex = new VectorArray<>();
        this.vectors = vectors;
        if (vectors.length > 0) {
            max = vectors[0].length;
        }
        state = new GraphState[vectors.length];
        for(int i = 0; i < vectors.length; i++) {
            vertex.add((i+1) +"");
        }
    }


    @Override
    public void addEdge(String v, String v1) {
        if (!vertex.contains(v)) {
            addVertex(v);
        }
        if (!vertex.contains(v1)) {
            addVertex(v1);
        }
        if (Arrays.stream(vectors[vertex.indexOf(v)]).noneMatch(value -> value != null && value == vertex.indexOf(v1))) {
            int count = (int) Arrays.stream(vectors[vertex.indexOf(v)])
                    .filter(Objects::nonNull)
                    .count();
            if (count >= max) {
                resize();
            }
            vectors[vertex.indexOf(v)][count] = vertex.indexOf(v1);
        }

    }

    @Override
    public void addVertex(String v) {
        if (!vertex.contains(v)) {
            vertex.add(v);
            Integer[][] newIncidents = new Integer[vertex.size()][max];
            System.arraycopy(vectors, 0, newIncidents, 0, vectors.length);
            vectors = newIncidents;

            GraphState[] newState = new GraphState[vertex.size()];
            System.arraycopy(state, 0, newState, 0, state.length);
            state = newState;
        }
    }

    private void resize() {
        max++;
        Integer[][] old = vectors;
        for (int i = 0; i < old.length; i++) {
            Integer[] newLine = new Integer[max];
            System.arraycopy(old[i], 0, newLine, 0, old[i].length);
            vectors[i] = newLine;
        }
    }

    @Override
    public void print() {
        for (int i = 0; i < vertex.size(); i++) {
            System.out.printf("%s |  %5s",
                    vertex.get(i),
                    Arrays.stream(vectors[i])
                            .map(value -> null != value ? vertex.get(value) : "--")
                            .collect(Collectors.joining(" |  ")));
            System.out.println();
        }
    }

    private int[] getInWeight(IArray<String> banList) {
        int[] result = new int[vertex.size()];
        for (int i = 0; i < vertex.size(); i++) {
            if (!banList.contains(vertex.get(i))) {
                for (var item : vectors[i]) {
                    if (item != null) {
                        result[item] += 1;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public IArray<String> demukronAsList() {

        IArray<String> result = new VectorArray<>();
        int sum;
        do {
            int[] weight = getInWeight(result);
            boolean anySources = false;
            for (int i = 0; i < weight.length; i++) {
                if (0 == weight[i] && !result.contains(vertex.get(i))) {
                    anySources = true;
                    result.add(vertex.get(i));
                }
            }
            if (!anySources) {
                return null;
            }
            sum = Arrays.stream(weight).sum();
        } while (sum > 0);

        return result;
    }

    @Override
    public int[][] demukron() {
        int[][] result = new int[0][0];
        IArray<String> stack = new VectorArray<>();
        int sum;
        do {
            IArray<Integer> level = new VectorArray<>();
            int[] weight = getInWeight(stack);
            boolean anySources = false;
            for (int i = 0; i < weight.length; i++) {
                if (0 == weight[i] && !stack.contains(vertex.get(i))) {
                    anySources = true;
                    stack.add(vertex.get(i));
                    level.add(i);
                }
            }
            if (!anySources) {
                return null;
            }

            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = new int[level.size()];
            for (int i = 0; i < level.size(); i++) {
                result[result.length - 1][i] = level.get(i);
            }
            sum = Arrays.stream(weight).sum();
        } while (sum > 0);

        return result;

    }

    @Override
    public void printDemukron() {
        IArray<String> result = demukronAsList();

        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.printf(result.get(i));
                if (i < result.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("Топологическая сортировка не возможна");
        }
    }

    private void clearState() {
        Arrays.fill(state, GraphState.NONE);
    }

    private boolean DFS(int begin, IArray<String> stack) {
        state[begin] = GraphState.SEEN;
        for (Integer item : vectors[begin]) {
            if(null != item) {
                if (state[item] == GraphState.NONE) {
                    if (!DFS(item, stack)) {
                        return false;
                    }
                }
            }
        }
        state[begin] = GraphState.CPLT;
        stack.add(vertex.get(begin));
        return true;
    }

    @Override
    public IArray<String> tarian() {
        clearState();
        IArray<String> stack = new VectorArray<>();
        for (int i = 0; i < vertex.size(); i++) {
            if (state[i] == GraphState.NONE) {
                if (!DFS(i, stack)) {
                    return null;
                }
            }
        }
        return stack;
    }

    @Override
    public void printTarian() {
        IArray<String> result = tarian();

        if (result != null) {
            for (int i = result.size() -1 ; i >= 0; i--) {
                System.out.printf(result.get(i));
                if (i > 0) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("Топологическая сортировка не возможна");
        }
    }
}
