package com.example.deliverytracking.service;

import com.example.deliverytracking.model.Order;
import com.example.deliverytracking.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    // Create order
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PLACED);
        orders.add(order);
        return order;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orders;
    }

    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {

        Order order = orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus currentStatus = order.getStatus();

        if (!isValidTransition(currentStatus, newStatus)) {
            throw new RuntimeException(
                    "Cannot change status from " + currentStatus + " to " + newStatus);
        }

        order.setStatus(newStatus);
        return order;
    }

    // Business rule: allowed transitions
    private boolean isValidTransition(OrderStatus current, OrderStatus next) {
        return switch (current) {
            case PLACED -> next == OrderStatus.ACCEPTED;
            case ACCEPTED -> next == OrderStatus.PREPARING;
            case PREPARING -> next == OrderStatus.PICKED_UP;
            case PICKED_UP -> next == OrderStatus.DELIVERED;
            default -> false;
        };
    }
}
