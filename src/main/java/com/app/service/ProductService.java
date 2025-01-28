package com.app.service;

import com.app.model.ProductDetailsModel;
import com.app.model.ProductModel;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;
public interface ProductService {
    List<ProductModel> getAllProducts();

    ProductModel getProductDetailsById(int id);

    ProductModel getProductById(int id); // Added this missing method

    void saveProduct(ProductModel product);

    void updateProduct(ProductModel productModel, ProductDetailsModel detailsModel ) throws IOException;

    void deleteProduct(int id);
    void deleteProductImage(int id);
}
