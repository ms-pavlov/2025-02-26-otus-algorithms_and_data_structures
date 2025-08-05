package org.example;

public class TreeNode {

    public final int key;
    public TreeNode left;
    public TreeNode right;
    public int height;

    public TreeNode(int key) {
        this.key = key;
        this.height = 0;
    }
}
