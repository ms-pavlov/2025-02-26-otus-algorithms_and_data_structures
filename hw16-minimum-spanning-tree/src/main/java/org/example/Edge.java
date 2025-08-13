package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Edge implements Comparable<Edge>{
    private Integer v1;
    private Integer v2;
    private int weight;


    @Override
    public int compareTo(Edge o) {
        if(null == o) {
            return 1;
        }
        return weight - o.getWeight();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", weight=" + weight +
                '}';
    }
}
