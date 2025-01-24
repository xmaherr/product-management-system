package com.app.service;

import com.app.dao.ProductDao;
import com.app.entity.ProductDetails;
import com.app.entity.ProductEntity;
import com.app.model.ProductDetailsModel;
import com.app.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductModel> getAllProducts() {
        return productDao.getProducts().stream()
                .map(ProductModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductModel getProductDetailsById(int id) {
        ProductEntity product = productDao.getProductWithDetailsById(id);
        if (product != null && product.getDetails() != null) {
            ProductModel productModel = new ProductModel(product);
            productModel.setDetails(new ProductDetailsModel(product.getDetails()));
            return productModel;
        }
        System.out.println(product.toString());
        System.out.println(product.getDetails().toString());
        return null;
    }

    @Override
    public ProductModel getProductById(int id) { // Implemented the missing method
        ProductEntity product = productDao.getProductById(id);
        return product != null ? new ProductModel(product) : null;
    }

    @Override
    public void saveProduct(ProductModel product) {
        if (product.getDetails() != null) {
            ProductEntity productEntity =new ProductEntity();
            productEntity.setName(product.getName());

            ProductDetails productDetails = new ProductDetails();
            productDetails.setAvailable(product.getDetails().isAvailable());
            productDetails.setPrice(product.getDetails().getPrice());
            productDetails.setManufacturer(product.getDetails().getManufacturerModel());
            productDetails.setExpiryDate(product.getDetails().getExpiryDate());

            productEntity.setDetails(productDetails);

            this.productDao.saveOrUpdateProduct(productEntity);
        }
    }

    @Override
    public void updateProduct(int id, ProductModel productModel, ProductDetailsModel detailsModel) {
        ProductEntity product = productDao.getProductWithDetailsById(id);
        if (product != null) {
            product.setName(productModel.getName());

            if (product.getDetails() != null) {
                ProductDetails details = product.getDetails();
                details.setManufacturer(detailsModel.getManufacturerModel());
                details.setPrice(detailsModel.getPrice());
                details.setAvailable(detailsModel.isAvailable());
                details.setExpiryDate(detailsModel.getExpiryDate());
            }
            System.out.println(product.toString());
            System.out.println(product.getDetails().toString());
            productDao.saveOrUpdateProduct(product);
        }
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }
}
