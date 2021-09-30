package no.totenkode.memory.table.repository;

import no.totenkode.memory.table.tree.BinarySearchTree;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableStuffColumn {

    private final String name;
    private final Method method;
    private final BinarySearchTree searchTree = new BinarySearchTree();

    public TableStuffColumn(String name, Method method) {
        this.name = name;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Method getMethod() {
        return method;
    }

    public void insert(long key, long id) {
        searchTree.insert(key, id);
    }

    public BinarySearchTree getSearchTree() {
        return searchTree;
    }
}
