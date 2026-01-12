package com.yash.springecom.controller;

import com.yash.springecom.model.Product;
import com.yash.springecom.repo.ProductRepo;
import com.yash.springecom.service.ProductService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProduct(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        try {
            Product p = service.getProductById(id);
            if(p != null) {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
       Product p=service.getProductById(productId);
       return new ResponseEntity<>(p.getImageData(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/product",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<Product> addProduct(
            @RequestPart("product") Product product,
            @RequestPart("imageFile") MultipartFile imageFile
    ) throws IOException {

        Product saved = service.addProduct(product, imageFile);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
