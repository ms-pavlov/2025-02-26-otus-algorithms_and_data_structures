package org.example;

public class SplayTree implements Tree {
    public TreeNode root;

    @Override
    public void insert(int key) {
        splayInsert(key);
    }

    public TreeNode insert(int key, TreeNode curr) {
        TreeNode node = new TreeNode(key);
        if (root == null) {
            root = node;
            return node;
        }
        if (curr == null) curr = root;
        if (key < curr.key) {
            if (curr.left == null)
                curr.left = node;
            else
                return insert(key, curr.left);
        } else {
            if (curr.right == null)
                curr.right = node;
            else
                return insert(key, curr.right);
        }
        return node;
    }

    public void splayInsert(int key) {
        TreeNode curr = insert(key, root);
        TreeNode parent = searchParent(curr, root);
        while (parent != null) {
            TreeNode grand = searchParent(parent, root);
            if (parent.left == curr) {
                TreeNode node = minRightRotate(parent);
                if (grand != null) {
                    if (grand.left == parent)
                        grand.left = node;
                    else
                        grand.right = node;
                }
            } else if (parent.right == curr){
                TreeNode node = minLeftRotate(parent);
                if (grand != null) {
                    if (grand.left == parent)
                        grand.left = node;
                    else
                        grand.right = node;
                }
            }
            parent = grand;
        }
    }

    @Override
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

    @Override
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

    private void updateHeight(TreeNode node) {
        if (node == null) return;
        int left = node.left == null ? -1 : node.left.height;
        int right = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(left, right);
    }
}
