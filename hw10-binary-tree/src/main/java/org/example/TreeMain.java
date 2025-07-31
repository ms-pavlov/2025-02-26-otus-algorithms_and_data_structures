package org.example;


public class TreeMain {
    public static void main(String[] args) {

        int n = 100_000;

        System.out.println("Бинарное дерево");

        Test binaryTreeTest = new Tester(n, BinaryTree::new);

        binaryTreeTest.run();

        System.out.println("АВЛ дерево");

        Test avl = new Tester(n, AVLTree::new);

        avl.run();

    }


}