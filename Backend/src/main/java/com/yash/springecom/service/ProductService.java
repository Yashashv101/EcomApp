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

    public Product updateProduct(Product product, MultipartFile imageFile) throws IOException{
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
            return productRepo.save(product);
    }

    public void deletProductById(int id) {
        productRepo.deleteById(id);
    }
}
