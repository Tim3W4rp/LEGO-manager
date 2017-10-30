package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Test of {@link CategoryDao} methods.
 *
 * @author Martin Jordán
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CategoryDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CategoryDao categoryDao;

    @PersistenceContext
    private EntityManager em;

    private SetOfKits buildingsSet;
    private SetOfKits carsSet;

    private Category testCategory;
    private Category carsCategory;
    private Category boysCategory;

    private Kit eiffelTowerKit;
    private Kit statueOfLibertyKit;
    private Kit towerBridgeKit;

    private Kit porscheKit;
    private Kit ferrariKit;
    private Kit footballerKit;

    @Before
    public void setUp() throws Exception {
        testCategory = new Category("Star Wars","A universe of Jedis and space battles");
        categoryDao.create(testCategory);
    }

    @Test
    public void create() throws Exception {
        Session session = (Session) em.getDelegate();
        Category category = (Category) session.createQuery("FROM Category ").list().get(0);
        Assert.assertEquals(category.getName(), "Star Wars");
    }

    @Test
    public void findById() throws Exception {
        Category category = categoryDao.findById(this.testCategory.getId());
        Assert.assertEquals(category.getId().longValue(), 1L);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

}
