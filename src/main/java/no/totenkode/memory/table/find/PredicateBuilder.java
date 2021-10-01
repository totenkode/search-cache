package no.totenkode.memory.table.find;

import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder {

    private final Class<?> type;
    private final List<AbstractPredicate> predicates = new ArrayList<>();

    public PredicateBuilder(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public List<AbstractPredicate> getPredicates() {
        return predicates;
    }

    public PredicateBuilder string(String field, String string) {
        predicates.add(new StringPredicate(field, string));
        return this;
    }

    public PredicateBuilder equal(String field, long value) {
        predicates.add(new ValuePredicate(field, Operator.EQUALS, value));
        return this;
    }

    public PredicateBuilder lessThen(String field, long value) {
        predicates.add(new ValuePredicate(field, Operator.LESS, value));
        return this;
    }

    public PredicateBuilder greaterThen(String field, long value) {
        predicates.add(new ValuePredicate(field, Operator.GREATER, value));
        return this;
    }
}
