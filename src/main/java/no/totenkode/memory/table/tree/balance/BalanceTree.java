package no.totenkode.memory.table.tree.balance;

public class BalanceTree {
    private BalanceNode root;

    public BalanceNode find(int key) {
        BalanceNode current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public BalanceNode getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    private BalanceNode insert(BalanceNode node, int key) {
        if (node == null) {
            return new BalanceNode(key);
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    private BalanceNode delete(BalanceNode node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                BalanceNode mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private BalanceNode mostLeftChild(BalanceNode node) {
        BalanceNode current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private BalanceNode rebalance(BalanceNode z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private BalanceNode rotateRight(BalanceNode y) {
        BalanceNode x = y.left;
        BalanceNode z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private BalanceNode rotateLeft(BalanceNode y) {
        BalanceNode x = y.right;
        BalanceNode z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(BalanceNode n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(BalanceNode n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(BalanceNode n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

}
