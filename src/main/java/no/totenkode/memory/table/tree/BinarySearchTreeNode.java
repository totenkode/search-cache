package no.totenkode.memory.table.tree;

import java.util.HashSet;
import java.util.Set;

public class BinarySearchTreeNode {

    public long key;
    public final Set<Long> ids = new HashSet<>();

    public BinarySearchTreeNode left;
    public BinarySearchTreeNode right;

    public BinarySearchTreeNode(long data, long id) {
        key = data;
        left = right = null;
        ids.add(id);
    }

}
