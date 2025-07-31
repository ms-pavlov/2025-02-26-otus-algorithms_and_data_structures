package org.example;

public class BinaryTree implements Tree {

    public TreeNode root;

    public void insert(int key) {
        insert(key, root);
    }

    public void insert(int key, TreeNode curr) {
        TreeNode node = new TreeNode(key);
        if (root == null) {
            root = node;
            return;
        }
        if (curr == null) {
            curr = root;
        }

        while(true) {
            if (key < curr.key) {
                if (curr.left == null) {
                    curr.left = node;
                    return;
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.right == null) {
                    curr.right = node;
                    return;
                } else {
                    curr = curr.right;
                }
            }
        }
    }

    public TreeNode search(int key) {
        return search(key, root);
    }

    public TreeNode search(int key, TreeNode node) {

        TreeNode point = node;

        while(point != null) {
            if (key == point.key) {
                return point;
            }
            if (key < point.key) {
                point = point.left;
            } else {
                point = point.right;
            }
        }
        return null;
    }

    public TreeNode searchParent(TreeNode node, TreeNode search) {
        if (node == null) {
            return null;
        }
        while (null != search) {
            if (node.key == search.key) {
                return null;
            }
            if (search.left != null) {
                if (node.key == search.left.key) {
                    return search;
                }
            }
            if (search.right != null) {
                if (node.key == search.right.key) {
                    return search;
                }
            }
            if (node.key < search.key) {
                search = search.left;
            } else {
                search = search.right;
            }
        }
        return null;
    }

    public void remove(int key) {
        TreeNode node = search(key);
        if (node == null) return;
        remove(node);
    }

    public void remove(TreeNode node) {
        TreeNode parent = searchParent(node, root);
        if (node.right == null) {   // нет правого потомка
            if (parent != null)  // удаляем не корень
            {
                if (parent.left == node) {
                    parent.left = node.left;
                } else {
                    parent.right = node.right;
                }
            } else { // удаляем корень без правого потомка
                root = node.left;
            }
            return;
        }

        TreeNode min = node.right;  // минимум в правом поддереве
        TreeNode minParent = node;  // родитель минимума
        while (min.left != null) {
            minParent = min;
            min = min.left;
        }
        if (parent != null) {  // удаляем не корень
            if (parent.left == node) {
                parent.left = min;
            } else {
                parent.right = min;
            }
        } else {               // удаляем корень
            root = min;
        }

        if (min != node.right) {  // если минимум в правом поддереве не сам правый потомок
            minParent.left = min.right;
            min.right = node.right;
        }
        min.left = node.left;    // переназначаем левое поддерево удаляемого узла

    }
}
