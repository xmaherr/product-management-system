package com.app.service;

import com.app.dao.ProductDao;
import com.app.entity.ProductDetails;
import com.app.entity.ProductEntity;
import com.app.model.ProductDetailsModel;
import com.app.model.ProductModel;
import com.app.util.UtilFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
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

            if (thereIsImage(product.getDetails().getImageFile())) {
                try {
                    productDetails.setImage(product.getDetails().getImageFile().getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                productDetails.setImage(null);
            }

            productEntity.setDetails(productDetails);

            this.productDao.saveOrUpdateProduct(productEntity);
        }
    }

    @Override
    public void updateProduct(ProductModel productModel, ProductDetailsModel detailsModel) throws IOException {
        ProductEntity product = productDao.getProductWithDetailsById(productModel.getId());
        if (product != null) {
            product.setName(productModel.getName());

            if (product.getDetails() != null) {
                ProductDetails details = product.getDetails();
                details.setManufacturer(detailsModel.getManufacturerModel());
                details.setPrice(detailsModel.getPrice());
                details.setAvailable(detailsModel.isAvailable());
                details.setExpiryDate(detailsModel.getExpiryDate());
                //set image
                if (thereIsImage(detailsModel.getImageFile())) {
                    details.setImage(detailsModel.getImageFile().getBytes());
                }
            }
            productDao.saveOrUpdateProduct(product);
        }
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }

    @Override
    public void deleteProductImage(int id) {
        this.productDao.deleteProductImage(id);
    }

    public boolean thereIsImage(MultipartFile file) {

        return file!=null && file.getSize()>0;
    }


}
