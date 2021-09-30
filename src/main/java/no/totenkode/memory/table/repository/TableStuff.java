package no.totenkode.memory.table.repository;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TableStuff {

    private final String name;
    private final Map<String, TableStuffColumn> map = new HashMap<>();

    public TableStuff(String name) {
        this.name = name;
    }

    public TableStuffColumn getField(String name) {
        return map.get(name);
    }

    public void create(String name, Method method) {
        map.put(name, new TableStuffColumn(name, method));
    }

    public Collection<TableStuffColumn> getColumns() {
        return map.values();
    }
}
