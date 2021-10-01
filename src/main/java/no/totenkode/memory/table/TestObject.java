package no.totenkode.memory.table;

import no.totenkode.memory.table.annotation.Column;
import no.totenkode.memory.table.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

@Table("test_object")
public class TestObject {

    @Column("id")
    private Long id;

    @Column("active")
    private boolean active;

    @Column("timestamp")
    private Date timestamp;

    @Column("price")
    private BigDecimal price;

    @Column("quantity")
    private Integer quantity;

    public TestObject(Long id, boolean active, Date timestamp, BigDecimal price, Integer quantity) {
        this.id = id;
        this.active = active;
        this.timestamp = timestamp;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
