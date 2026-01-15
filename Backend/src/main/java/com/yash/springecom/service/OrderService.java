package com.yash.springecom.service;

import com.yash.springecom.model.Order;
import com.yash.springecom.model.OrderItem;
import com.yash.springecom.model.Product;
import com.yash.springecom.model.dto.OrderItemRequest;
import com.yash.springecom.model.dto.OrderItemResponse;
import com.yash.springecom.model.dto.OrderRequest;
import com.yash.springecom.model.dto.OrderResponse;
import com.yash.springecom.repo.OrderRepo;
import com.yash.springecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderRepo orderRepo;

    public OrderResponse placeOrder(OrderRequest request) {
        Order order=new Order();
        String orderId="ORD"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> items=new ArrayList<>();
        for(OrderItemRequest itemReq:request.items()){
            Product prod=productRepo.findById(itemReq.productId()).orElseThrow(()->new RuntimeException("Product Not Found"));
            prod.setStockQuantity(prod.getStockQuantity()-itemReq.quantity());
            productRepo.save(prod);

            OrderItem orderItem=OrderItem.builder()
                    .product(prod)
                    .quantity(itemReq.quantity())
                    .totalPrice(prod.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
                    .order(order)
                    .build();

            items.add(orderItem);
        }
        order.setItems(items);
        Order saveOrder=orderRepo.save(order);
        List<OrderItemResponse> itemResponse=new ArrayList<>();
        for(OrderItem item:order.getItems()){
            OrderItemResponse res=new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
        }
        OrderResponse orderResponse=new OrderResponse(
                saveOrder.getOrderId(),
                saveOrder.getCustomerName(),
                saveOrder.getEmail(),
                saveOrder.getStatus(),
                saveOrder.getOrderDate(),
                itemResponse
                );
        return orderResponse;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> ls=orderRepo.findAll();
        List<OrderResponse> orderResponse=new ArrayList<>();
        for(Order o:ls){

            List<OrderItemResponse> itemResponse=new ArrayList<>();
            for(OrderItem item:o.getItems()){
                OrderItemResponse res=new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponse.add(res);
            }

            OrderResponse res=new OrderResponse(
                    o.getOrderId(),
                    o.getCustomerName(),
                    o.getEmail(),
                    o.getStatus(),
                    o.getOrderDate(),
                    itemResponse
            );
            orderResponse.add(res);
        }
        return orderResponse;
    }
}
