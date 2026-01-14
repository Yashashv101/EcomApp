package com.yash.springecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    Long Id;
    @Column(unique=true)
    String orderID;
    String customerName;
    String email;
    LocalDate orderDate;
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    List<OrderItem> items;
}
