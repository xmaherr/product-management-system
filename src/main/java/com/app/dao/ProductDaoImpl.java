package com.app.dao;


import com.app.entity.ProductDetails;
import com.app.entity.ProductEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.lang.instrument.Instrumentation;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private static volatile Instrumentation globalInstrumentation;
    
    @Override
    public void addProduct(ProductEntity productEntity) {
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("appContext.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.save(productEntity);

        try {
            transaction.commit();
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }

        session.close();
    }

    @Override
    public List<ProductEntity> getProducts() {
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("appContext.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        Query query= session.createQuery("from ProductEntity");

        List<ProductEntity> products =query.getResultList();

        for (ProductEntity product : products) {
            Hibernate.initialize(product.getDetailsId()); // or getDetailsId(), if it's just the ID
        }

        try {
            transaction.commit();
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }


        session.close();

        return products;
    }

    @Override
    public ProductDetails getProductDetailsById(int productDetailsID) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductDetails productDetails =  session.get(ProductDetails.class, productDetailsID);

        return productDetails;
    }

}
