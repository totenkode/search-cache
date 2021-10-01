package no.totenkode.memory.table.tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeNode {

    public long key;
    public final List<Long> ids = new ArrayList<>();

    public BinarySearchTreeNode left;
    public BinarySearchTreeNode right;

    public BinarySearchTreeNode(long data, long id) {
        key = data;
        left = right = null;
        ids.add(id);
    }

}
