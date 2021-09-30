package no.totenkode.memory.table.find;

/**
 * Search for the whole string as one including separating spaces
 */
public class StringPredicate extends AbstractPredicate {

    public String value;

    public StringPredicate(String field, String value) {
        super(field);
        this.value = value;
    }
}
