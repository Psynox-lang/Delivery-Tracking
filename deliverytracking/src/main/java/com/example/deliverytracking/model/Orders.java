package com.example.deliverytracking.model;

public class Orders {
    private Long id;
    private String customerName;
    private String status;

    public Orders() {

    }

    public Orders(Long id, String customerName, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
