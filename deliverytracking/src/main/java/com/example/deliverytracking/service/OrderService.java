package com.example.deliverytracking.service;

import com.example.deliverytracking.model.Orders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Orders> orders = new ArrayList<>();

    public Orders createOrder(Orders order) {
        order.setStatus("PLACED");
        orders.add(order);
        return order;
    }

    public List<Orders> getAllOrders() {
        return orders;
    }
}
