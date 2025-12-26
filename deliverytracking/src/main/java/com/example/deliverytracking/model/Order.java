package com.example.deliverytracking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }

    public Order(Long id, String customerName, OrderStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
