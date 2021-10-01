package no.totenkode.memory.table.repository;

import no.totenkode.memory.table.ColumnDefinition;
import no.totenkode.memory.table.TableDefinition;
import no.totenkode.memory.table.TestObject;
import no.totenkode.memory.table.find.AbstractPredicate;
import no.totenkode.memory.table.find.PredicateBuilder;
import no.totenkode.memory.table.find.StringPredicate;
import no.totenkode.memory.table.find.ValuePredicate;
import no.totenkode.memory.table.tree.BinarySearchTree;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {

    private Map<Class<?>, TableStuff> map = new HashMap<>();

    public void save(Object o) throws Exception {
        TestObject testObject = (TestObject) o;
        final TableStuff tableStuff = map.get(o.getClass());
        for (TableStuffColumn column : tableStuff.getColumns()) {
            final Object value = column.getMethod().invoke(o);

            if (value == null) {
                column.insert(Long.MIN_VALUE, testObject.getId());
            } else {
                if (value instanceof Boolean) {
                    final Boolean booleanValue = (Boolean) value;
                    column.insert(booleanValue ? 1 : 0, testObject.getId());
                } else if (value instanceof String) {
                    // TODO needs a text search tree
                } else if (value instanceof BigDecimal) {
                    final BigDecimal realValue = (BigDecimal) value;
                    final long longValue = realValue.multiply(new BigDecimal("100.00")).longValue();
                    column.insert(longValue, testObject.getId());
                } else if (value instanceof Integer) {
                    final Integer integerValue = (Integer) value;
                    column.insert(integerValue, testObject.getId());
                } else if (value instanceof Long) {
                    column.insert((long) value, testObject.getId());
                } else if (value instanceof Date) {
                    column.insert(((Date) value).getTime(), testObject.getId());
                }
            }

        }
        // todo
    }

    public List<Long> find(PredicateBuilder builder, int limit) {
        final TableStuff stuff = map.get(builder.getType());

        boolean first = true;
        Set<Long> result = new HashSet<>();
        for (AbstractPredicate predicate : builder.getPredicates()) {
            final Set<Long> predicateIds = new HashSet<>();
            final BinarySearchTree searchTree = stuff.getField(predicate.field).getSearchTree();
            if (predicate instanceof StringPredicate) {
                // TODO needs a text search tree
            } else if (predicate instanceof ValuePredicate) {
                searchTree.search(((ValuePredicate) predicate).value, predicateIds);
            } else {
                throw new UnsupportedOperationException(predicate.getClass() + " not implemented");
            }
            if(first) {
                result.addAll(predicateIds);
                first = false;
            } else {
                result = result.parallelStream().filter(predicateIds::contains).collect(Collectors.toSet());
            }

        }

        final BinarySearchTree text = stuff.getField("id").getSearchTree();
        return result.stream().sorted((o1, o2) -> {
            final Long i1 = text.index(o1);
            final Long i2 = text.index(o2);
            return i1.compareTo(i2);
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public void construct(TableDefinition tableDefinition) {
        final TableStuff stuff = new TableStuff(tableDefinition.getName());
        for (ColumnDefinition columnDefinition : tableDefinition.getColumnDefinitions()) {
            stuff.create(columnDefinition.getName(), columnDefinition.getMethod());
        }
        map.put(tableDefinition.getType(), stuff);
    }
}
