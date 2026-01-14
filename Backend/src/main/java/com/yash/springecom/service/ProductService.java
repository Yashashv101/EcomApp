package com.yash.springecom.service;

import com.yash.springecom.model.Product;
import com.yash.springecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Transactional(readOnly = true)
    public List<Product> getProduct() {
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(int id) {
        return productRepo.findProductById(id);
    }

    @Transactional
    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        
        return productRepo.save(product);
    }

    @Transactional
    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException{
        Product prod=productRepo.findProductById(id);
        prod.setName(product.getName());
        prod.setDescription(product.getDescription());
        prod.setBrand(product.getBrand());
        prod.setPrice(product.getPrice());
        prod.setCategory(product.getCategory());
        prod.setReleaseDate(product.getReleaseDate());
        prod.setProductAvailable(product.getProductAvailable());
        prod.setStockQuantity(product.getStockQuantity());
        if (imageFile != null && !imageFile.isEmpty()) {
            prod.setImageName(imageFile.getOriginalFilename());
            prod.setImageType(imageFile.getContentType());
            prod.setImageData(imageFile.getBytes());
        }
        return productRepo.save(prod);
    }

    public void deletProductById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
