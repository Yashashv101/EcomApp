package com.yash.springecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Entity(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long Id;
    @Column(unique=true)
    String orderId;
    String customerName;
    String email;
    String status;
    LocalDate orderDate;
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    List<OrderItem> items;
}
