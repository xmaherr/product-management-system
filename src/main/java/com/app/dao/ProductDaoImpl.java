package com.app.dao;

import com.app.entity.ProductEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

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
    @Transactional(readOnly = true) // Again, this is a read-only operation
    public ProductEntity getProductWithDetailsById(int id) {
        try (Session session = sessionFactory.openSession()) {
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            if (productEntity != null) {
                Hibernate.initialize(productEntity.getDetails());
            }
            return productEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional  // This method updates or saves, so we need a regular transaction
    public void saveOrUpdateProduct(ProductEntity product) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional  // This method involves a delete operation, so a regular transaction is needed
    public void deleteProduct(int id) {
        try (Session session = sessionFactory.openSession()) {
            ProductEntity product = session.get(ProductEntity.class, id);
            if (product != null) {
                session.delete(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
