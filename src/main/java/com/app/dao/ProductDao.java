package com.app.dao;

import com.app.entity.ProductDetails;
import com.app.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    List<ProductEntity> getProducts();

    ProductEntity getProductById(int id);
    ProductEntity getProductWithDetailsById(int id);
    void saveOrUpdateProduct(ProductEntity product);

    void deleteProduct(int id);
}
