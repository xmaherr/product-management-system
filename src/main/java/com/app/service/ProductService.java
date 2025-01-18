package com.app.service;

import com.app.entity.ProductEntity;
import com.app.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void addProduct(ProductEntity product);
    List<ProductEntity> getAllProducts();
}
