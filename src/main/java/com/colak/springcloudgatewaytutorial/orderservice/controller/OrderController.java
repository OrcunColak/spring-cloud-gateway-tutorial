package com.colak.springcloudgatewaytutorial.orderservice.controller;

import com.colak.springcloudgatewaytutorial.orderservice.dto.OrderModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    // http://localhost:8080/ignored/orders/12345
    @GetMapping("orders/{orderId}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable String orderId) {
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderId(orderId);
        return ResponseEntity.ok(orderModel);
    }
}
