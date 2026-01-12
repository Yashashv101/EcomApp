package com.yash.springecom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    Integer id;
    String name;
    String description;
    String brand;
    BigDecimal price;
    String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    Date releaseDate;
    Boolean productAvailable;
    Integer stockQuantity;
    String imageName;
    String imageType;
    @Lob
    @JsonIgnore
    @Basic(fetch = FetchType.EAGER)
    private byte[] imageData;

}
