package org.example;


public class DemoGraph {


    public static void print(Graph<String> g)  {

        g.addVertex("l1");
        g.addVertex("l2");
        g.addVertex("l3");

        g.addVertex("r1");
        g.addVertex("r2");
        g.addVertex("r3");
        g.addVertex("r4");

        g.addEdge("l1", "r1");
        g.addEdge("l1", "r2");
        g.addEdge("l2", "r2");
        g.addEdge("l3", "r3");
        g.addEdge("l3", "r4");

        g.print();
    }


}