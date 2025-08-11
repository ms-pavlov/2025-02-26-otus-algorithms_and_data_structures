package org.example;

public class Demukron {

    public static int[][] cal(Integer[][] vectors) {
        return new AdjacencyVectorGraph(vectors).demukron();
    }
}
