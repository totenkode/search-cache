package no.totenkode.memory.table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ColumnDefinition {

    private final String name;
    private final boolean index;
    private final Method method;

    public ColumnDefinition(String name, boolean index, Method method) {
        this.name = name;
        this.index = index;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public boolean isIndex() {
        return index;
    }

    public Method getMethod() {
        return method;
    }
}
