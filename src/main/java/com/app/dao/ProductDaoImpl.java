package com.app.dao;

import com.app.entity.ProductDetails;
import com.app.entity.ProductEntity;
import com.app.util.UtilFile;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private HttpServletRequest request;

    // Default transaction propagation is REQUIRED, which ensures that the transaction is handled correctly
    @Override
    @Transactional(readOnly = true)  // Marking it as read-only for better optimization
    public List<ProductEntity> getProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ProductEntity", ProductEntity.class).list();
        }
    }

    @Override
    @Transactional(readOnly = true) // Marked as read-only since it's a read operation
    public ProductEntity getProductById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ProductEntity.class, id);
        } catch (Exception e) {
          e.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity getProductWithDetailsById(int id) {
        try (Session session = sessionFactory.openSession()) {
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            if (productEntity != null) {
                Hibernate.initialize(productEntity.getDetails());
                byte[] imageBytes = productEntity.getDetails().getImage();

                if (imageBytes != null && imageBytes.length > 0) {
                   productEntity.getDetails().setImage(UtilFile.decompressImage(imageBytes));
                }
            }
            return productEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void saveOrUpdateProduct(ProductEntity product) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        byte[] imageBytes = product.getDetails().getImage();
        if (imageBytes != null && imageBytes.length > 0) {
            product.getDetails().setImage(UtilFile.compressImage(imageBytes));
        }

        try {
            sessionFactory.getCurrentSession().saveOrUpdate(product);
            transactionManager.commit(status); // Commit the transaction
        } catch (Exception e) {
            transactionManager.rollback(status); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            ProductEntity product = sessionFactory.getCurrentSession().get(ProductEntity.class, id);
            if (product != null) {
                sessionFactory.getCurrentSession().delete(product);
                transactionManager.commit(status); // Commit the transaction
            }
        } catch (Exception e) {
            transactionManager.rollback(status); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductImage(int id) {

        ProductDetails productDetails=getProductDetails(id);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

            productDetails.setImage(null);
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(productDetails);
            transactionManager.commit(status); // Commit the transaction
        } catch (Exception e) {
            transactionManager.rollback(status); // Rollback on error
            e.printStackTrace();
        }

    }



    @Override
    public ProductDetails getProductDetails(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ProductDetails.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
