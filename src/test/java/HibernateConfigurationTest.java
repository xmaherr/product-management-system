

import entity.Product;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import javax.persistence.RollbackException;
import javax.persistence.RollbackException;


import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernateConfigurationTest {

    private static SessionFactory sessionFactory;

    @SneakyThrows
    @BeforeAll
    public static void setup() {
        // Initialize Hibernate SessionFactory using hibernate.cfg.xml
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("appContext.xml");
        sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }

    @AfterAll
    public static void teardown() {
        // Close the SessionFactory after all tests
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

//    @Test
//    public void testConfigurationFilePath() {
//        URL configFile = HibernateConfigurationTest.class.getClassLoader().getResource("appConfig.xml");
//        assertNotNull(configFile, "applicationContext.xml should be present in the classpath.");
//    }

    @Test
    public void testSessionFactoryInitialization() {
        // Verify that SessionFactory is not null
        assertNotNull(sessionFactory, "SessionFactory should be initialized.");
    }

    @Test
    public void testBasicInsertOperation() {
        // Open a new session
        Session session = sessionFactory.openSession();
        Transaction transaction = (Transaction) session.beginTransaction();

        // Create a test Product object
        Product product = new Product();
        product.setName("Test Product22");
        product.setDescription("This is a test product.");
        product.setPrice(100.00);

        // Save the product to the database
        Integer productId = (Integer) session.save(product);

        // Commit the transaction
        try {
            transaction.commit();
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }
        session.close();

        // Verify that the product was assigned an ID
        assertNotNull(productId, "Product ID should not be null.");
    }
}
