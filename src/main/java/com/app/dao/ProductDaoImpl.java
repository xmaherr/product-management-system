package com.app.dao;


import com.app.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    
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

        try {
            transaction.commit();
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }

        session.close();

        System.out.println(products.toString());
        return products;
    }
}
