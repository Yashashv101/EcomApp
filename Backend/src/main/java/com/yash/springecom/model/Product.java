package com.yash.springecom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String description;
    String brand;
    BigDecimal price;
    String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
    Date releaseDate;
    boolean productAvailable;
    int stockQuantity;
    String imageName;
    String imageType;
    @Lob
    byte[] imageData;
}
