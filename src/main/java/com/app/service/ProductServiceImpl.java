package com.app.service;

import com.app.dao.ProductDao;
import com.app.entity.ProductEntity;
import com.app.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(ProductEntity product) {

    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productDao.getProducts();
    }
}
