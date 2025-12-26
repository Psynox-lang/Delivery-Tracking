package com.example.deliverytracking.repository;

import com.example.deliverytracking.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
