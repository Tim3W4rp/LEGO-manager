package cz.fi.muni.legomanager.entity;

import cz.fi.muni.legomanager.InMemoryDatabaseSpring;
import cz.fi.muni.legomanager.entity.Category;
import org.hibernate.Session;
import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Stepan Granat
 */
public class CategoryTest {

    private EntityManagerFactory emf;


    @Before
    public void beforeTest() {
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
        emf = Persistence.createEntityManagerFactory("default");
    }

    @Test
    public void equalityAndHash() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Category c1 = new Category();
        c1.setName("Test category");
        c1.setDescription("Test description");

        Category c2 = new Category();
        c2.setName("Test category");
        c2.setDescription("Test description");

        em.persist(c1);
        em.getTransaction().commit();


        Session session = (Session) emf.createEntityManager().getDelegate();

        List result = session.createQuery("FROM Category C where C.name = :categoryName")
                .setString("categoryName", c1.getName()).list();


        Category c_db = (Category) result.get(0);

        Assert.assertTrue(c_db.equals(c2));

        Assert.assertEquals(c_db.hashCode(), c2.hashCode());
    }

    @Test
    public void relationTest() {

    }
}
