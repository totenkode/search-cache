package no.totenkode.memory.table;

import java.util.ArrayList;
import java.util.List;

public class TableDefinition {

    private final String name;
    private final Class<?> type;
    private final List<ColumnDefinition> columnDefinitions = new ArrayList<>();

    public TableDefinition(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }
}
