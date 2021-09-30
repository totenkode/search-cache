package no.totenkode.memory.table.find;

/**
 * Splits the value into words and search for them separately
 */
public class WordPredicate extends AbstractPredicate {

    public String value;

    public WordPredicate(String field, String value) {
        super(field);
        this.value = value;
    }
}
