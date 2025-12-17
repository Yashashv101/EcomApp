package com.yash.springecom.controller;

import com.yash.springecom.model.Product;
import com.yash.springecom.repo.ProductRepo;
import com.yash.springecom.service.ProductService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Product p=service.getProductById(id);
        if(p!=null) return new ResponseEntity<>(p,HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
