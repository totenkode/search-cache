package no.totenkode.memory.table.repository;

import no.totenkode.memory.table.ColumnDefinition;
import no.totenkode.memory.table.TableDefinition;
import no.totenkode.memory.table.find.AbstractPredicate;
import no.totenkode.memory.table.find.PredicateBuilder;
import no.totenkode.memory.table.find.StringPredicate;
import no.totenkode.memory.table.find.ValuePredicate;
import no.totenkode.memory.table.tree.BinarySearchTree;

import java.util.*;
import java.util.stream.Collectors;

public class Repository {

    private Map<Class<?>, TableStuff> map = new HashMap<>();

    public void save(Object o) throws Exception {
        final TableStuff tableStuff = map.get(o.getClass());
        for (TableStuffColumn column : tableStuff.getColumns()) {
            column.insert((Long) column.getMethod().invoke(o), System.identityHashCode(o));
        }
        // todo
    }

    public List<Long> find(PredicateBuilder builder, int limit) {
        final TableStuff stuff = map.get(builder.getType());
        // todo find  all the ids that match

        // for each id found

        // lookup the value for the sort column of that value

        // int get index of this
        //

        // sort the predicates by the sort order
        // search the fields in the order they are sorted
        // if no sort, return the first {limit} values found

        Set<Long> result = new HashSet<>();
        for (AbstractPredicate predicate : builder.getPredicates()) {
            final Set<Long> predicateIds = new HashSet<>();
            final BinarySearchTree searchTree = stuff.getField(predicate.field).getSearchTree();
            if (predicate instanceof StringPredicate) {
                final String value = ((StringPredicate) predicate).value;
                // TODO split value into letters
            } else if (predicate instanceof ValuePredicate) {
                searchTree.search(((ValuePredicate) predicate).value, predicateIds);
            } else {
                throw new UnsupportedOperationException(predicate.getClass() + " not implemented");
            }
            result = result.parallelStream().filter(predicateIds::contains).collect(Collectors.toSet());
        }
        // TODO sort
        return new ArrayList<>(result);
    }

    public void construct(TableDefinition tableDefinition) {
        final TableStuff stuff = new TableStuff(tableDefinition.getName());
        for (ColumnDefinition columnDefinition : tableDefinition.getColumnDefinitions()) {
            stuff.create(columnDefinition.getName(), columnDefinition.getMethod());
        }
        map.put(tableDefinition.getType(), stuff);
    }
}
