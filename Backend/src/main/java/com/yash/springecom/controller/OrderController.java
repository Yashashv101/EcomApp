package com.yash.springecom.controller;

import com.yash.springecom.model.dto.OrderRequest;
import com.yash.springecom.model.dto.OrderResponse;
import com.yash.springecom.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController{
    @Autowired
    private OrderService service;

    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse=service.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> ls=service.getAllOrders();
        return new ResponseEntity<>(ls,HttpStatus.OK);
    }
}
