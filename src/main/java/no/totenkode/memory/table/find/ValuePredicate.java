package no.totenkode.memory.table.find;

/**
 * Search for the value as it is
 */
public class ValuePredicate extends AbstractPredicate {

    public final Operator operator;
    public final long value;

    public ValuePredicate(String field, Operator operator, long value) {
        super(field);
        this.operator = operator;
        this.value = value;
    }
}
