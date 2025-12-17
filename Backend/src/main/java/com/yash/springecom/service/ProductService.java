package com.yash.springecom.service;

import com.yash.springecom.model.Product;
import com.yash.springecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public List<Product> getProduct() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findProductById(id);
    }
}
