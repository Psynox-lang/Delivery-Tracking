package com.example.deliverytracking.service;

import com.example.deliverytracking.exception.InvalidOrderStatusException;
import com.example.deliverytracking.exception.OrderNotFoundException;
import com.example.deliverytracking.model.Order;
import com.example.deliverytracking.model.OrderStatus;
import com.example.deliverytracking.repository.OrderRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public OrderService(OrderRepository orderRepository,
            SimpMessagingTemplate messagingTemplate) {
        this.orderRepository = orderRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // Create new order
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PLACED);
        return orderRepository.save(order);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update order status + send WebSocket update
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        OrderStatus currentStatus = order.getStatus();

        if (!isValidTransition(currentStatus, newStatus)) {
            throw new InvalidOrderStatusException(
                    "Cannot change status from " + currentStatus + " to " + newStatus);
        }

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);

        messagingTemplate.convertAndSend("/topic/orders", updatedOrder);

        return updatedOrder;
    }

    // Business rules for status flow
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
