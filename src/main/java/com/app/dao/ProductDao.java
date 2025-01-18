package com.app.dao;

import com.app.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    void addProduct(ProductEntity productEntity);
    List<ProductEntity> getProducts();
}
