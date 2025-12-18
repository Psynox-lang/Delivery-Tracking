package com.example.deliverytracking.controller;

import org.springframework.web.bind.annotation.*;
import com.example.deliverytracking.model.Orders;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        order.setStatus("PLACED");
        return order;
    }

}
