package com.ritesh.order.controller;

import com.ritesh.order.model.OrderDetail;
import com.ritesh.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Map<String, String>> addOrder(@RequestBody OrderDetail order){
        Map<String,String> response = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
