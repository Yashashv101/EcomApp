package com.yash.springecom.repo;

import com.yash.springecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findProductById(int id);
}
