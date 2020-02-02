package com.wojto.wmcase.entity;

import javax.persistence.*;

@Entity
@Table(name="quantities")
public class Quantity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="quantity")
    private int quantity;

    public Quantity(){};

    public Quantity(int id, int quantity) {
//        this.associatedOrder = associatedOrder;
//        this.associatedCase = associatedCase;
        this.id = id;
        this.quantity = quantity;
    }

    public Quantity(int quantity) {
        super();
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
