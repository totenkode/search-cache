package no.totenkode.memory.table;

import no.totenkode.memory.table.annotation.Column;
import no.totenkode.memory.table.annotation.Table;

@Table("test_object")
public class TestObject {

    @Column("id")
    private String id;

    @Column("text")
    private String text;

    @Column("bool_unboxed")
    private boolean unboxedBoolean;

    @Column("bool_boxed")
    private Boolean boxedBoolean;

    public TestObject(String id, String text, boolean unboxedBoolean, Boolean boxedBoolean) {
        this.id = id;
        this.text = text;
        this.unboxedBoolean = unboxedBoolean;
        this.boxedBoolean = boxedBoolean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUnboxedBoolean() {
        return unboxedBoolean;
    }

    public void setUnboxedBoolean(boolean unboxedBoolean) {
        this.unboxedBoolean = unboxedBoolean;
    }

    public Boolean getBoxedBoolean() {
        return boxedBoolean;
    }

    public void setBoxedBoolean(Boolean boxedBoolean) {
        this.boxedBoolean = boxedBoolean;
    }
}
