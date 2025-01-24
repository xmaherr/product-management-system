package com.app.service;

import com.app.model.ProductDetailsModel;
import com.app.model.ProductModel;

import java.util.List;
public interface ProductService {
    List<ProductModel> getAllProducts();

    ProductModel getProductDetailsById(int id);

    ProductModel getProductById(int id); // Added this missing method

    void saveProduct(ProductModel product);

    void updateProduct(int id, ProductModel productModel, ProductDetailsModel detailsModel);

    void deleteProduct(int id);
}
