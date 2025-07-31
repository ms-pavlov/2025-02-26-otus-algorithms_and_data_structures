package org.example;

public class AVLTree extends BinaryTree {

    @Override
    public void insert(int key, TreeNode curr) {
        TreeNode node = new TreeNode(key);
        if (root == null) {
            root = node;
            return;
        }
        if (curr == null) {
            curr = root;
        }
        if (key < curr.key) {
            if (curr.left == null) {
                curr.left = node;
            } else {
                insert(key, curr.left);
            }
        } else {
            if (curr.right == null) {
                curr.right = node;
            } else {
                insert(key, curr.right);
            }
        }

        updateHeight(curr.left);
        updateHeight(curr.right);
        curr.left = balance(curr.left);
        curr.right = balance(curr.right);
    }

    @Override
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

        if(node == minParent)
            node = min;
        else
            node = minParent;

        do
        {
            updateHeight(node);
            if (node == null)
                break;
            if(node.left != null)
                node.left = balance(node.left);
            if (node.right != null)
                node.right = balance(node.right);
            node = searchParent(node, root);
        }
        while (node != null);
    }

    private void updateHeight(TreeNode node) {
        if (node == null) return;
        int left = node.left == null ? -1 : node.left.height;
        int right = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(left, right);
    }

    private TreeNode minLeftRotate(TreeNode a) {
        TreeNode b = a.right;
        a.right = b.left;
        b.left = a;
        if (root == a) {
            root = b;
        }
        updateHeight(a);
        updateHeight(b);
        return b;
    }

    private TreeNode minRightRotate(TreeNode a) {
        TreeNode b = a.left;
        a.left = b.right;
        b.right = a;
        if (root == a) {
            root = b;
        }
        updateHeight(a);
        updateHeight(b);
        return b;
    }

    private TreeNode maxLeftRotate(TreeNode a) {
        a.right = minRightRotate(a.right);
        a = minLeftRotate(a);
        updateHeight(a);
        return a;
    }

    private TreeNode maxRightRotate(TreeNode a) {
        a.left = minLeftRotate(a.left);
        a = minRightRotate(a);
        updateHeight(a);
        return a;
    }

    public TreeNode balance(TreeNode node) {
        if (node == null) return null;
        int left = node.left == null ? -1 : node.left.height;
        int right = node.right == null ? -1 : node.right.height;
        int balance = left - right;
        if (Math.abs(balance) < 2) return node;
        if (balance > 1) {
            TreeNode b = node.left;
            left = b.left == null ? -1 : b.left.height;
            right = b.right == null ? -1 : b.right.height;
            if ((left - right) >= 0) {
                node = minRightRotate(node);
            } else {
                node = maxRightRotate(node);
            }
        } else {
            TreeNode b = node.right;
            left = b.left == null ? -1 : b.left.height;
            right = b.right == null ? -1 : b.right.height;
            if ((left - right) <= 0) {
                node = minLeftRotate(node);
            } else {
                node = maxLeftRotate(node);
            }
        }
        return node;
    }

}