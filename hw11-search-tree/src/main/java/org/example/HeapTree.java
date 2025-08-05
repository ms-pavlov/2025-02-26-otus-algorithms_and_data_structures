package org.example;

public class HeapTree implements Tree{

    private Treap treap;

    @Override
    public void insert(int key) {
        if(treap == null) {
            treap = new Treap(key);
        } else {
            treap = treap.add(key);
        }
    }

    @Override
    public TreeNode search(int key) {
        if(treap.find(key)) {
            return new TreeNode(key);
        }
        return null;
    }

    @Override
    public void remove(int key) {
        treap = treap.remove(key);
    }
}
