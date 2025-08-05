package org.example;

public class SearchTreeMain {
    public static void main(String[] args) {
        int n = 200_000;
        System.out.println("Splay дерево");
        Test splayTest = new Tester(n, SplayTree::new);
        splayTest.run();

        System.out.println("Random дерево");
        Test randomTest = new Tester(n, () -> new RandomTree(0.20));
        randomTest.run();

        System.out.println("Декартово дерево");
        Test heapTest = new Tester(n, HeapTree::new);
        heapTest.run();
    }
}