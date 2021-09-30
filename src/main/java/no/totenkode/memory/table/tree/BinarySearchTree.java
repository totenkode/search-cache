package no.totenkode.memory.table.tree;

import java.util.Set;

public class BinarySearchTree {

    private BinarySearchTreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(long key, long id) {
        root = insert(root, key, id);
    }

    private BinarySearchTreeNode insert(BinarySearchTreeNode root, long key, long id) {
        if (root == null) {
            root = new BinarySearchTreeNode(key, id);
            return root;
        }
        if (key == root.key) {
            root.ids.add(id);
        } else if (key < root.key) {
            root.left = insert(root.left, key, id);
        } else {
            root.right = insert(root.right, key, id);
        }
        return root; // TODO should never happen
    }

    public void delete(long key, long id) {
        root = delete(root, key, id);
    }

    private BinarySearchTreeNode delete(BinarySearchTreeNode root, long key, long id) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = delete(root.left, key, id);
        } else if (key > root.key) {
            root.right = delete(root.right, key, id);
        } else {
            root.ids.remove(id);
            if (!root.ids.isEmpty()) {
                return root;
            }
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = minValue(root.right);
            root.right = delete(root.right, root.key, id);
        }
        return root;
    }

    private long minValue(BinarySearchTreeNode root) {
        long minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    /*
     * Equals search
     */

    public void search(long key, Set<Long> result) {
        search(root, key, result);
    }

    private void search(BinarySearchTreeNode root, long key, Set<Long> result) {
        if (root == null) {
            return;
        }
        if (root.key == key) {
            result.addAll(root.ids);
            return;
        }
        if (root.key > key) {
            search(root.left, key, result);
            return;
        }
        search(root.right, key, result);
    }

    /*
     * Order
     */

    public long index(long key) {
        return index(root, key, 0);
    }

    private long index(BinarySearchTreeNode root, long key, long index) {
        if (root == null) {
            throw new RuntimeException("not found");
        }
        if (root.key == key) {
            return index;
        }
        if (root.key > key) {
            return index(root.left, key, index + root.ids.size());
        }
        return index(root.right, key, index + root.ids.size());
    }

    /*
     * Printing
     */

    public void print() {
        print(root);
    }

    private void print(BinarySearchTreeNode root) {
        if (root != null) {
            print(root.left);
            System.out.println(root.key + " " + printIds(root.ids));
            print(root.right);
        }
    }

    private String printIds(Set<Long> ids) {
        final StringBuilder builder = new StringBuilder();
        for (Long id : ids) {
            builder.append(id).append(", ");
        }
        if (builder.toString().length() > 0) {
            return "[" + builder.substring(0, builder.length() - 2) + "]";
        }
        return builder.toString();
    }
}