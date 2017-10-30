package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Category;
import org.hibernate.Session;
import org.junit.Assert;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;


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

    private Category testCategory;
    private Category starWarsCategory;
    private List<Category> categoryList;

    @Before
    public void setUp() throws Exception {
        starWarsCategory = new Category("Star Wars","A universe of Jedis and space battles");
        testCategory = new Category("Test","Test category");
        categoryDao.create(testCategory);
    }

    @Test
    public void create() throws Exception {
        categoryDao.create(starWarsCategory);
        Session session = (Session) em.getDelegate();
        Category category = (Category) session.createQuery("FROM Category ").list().get(1);
        Assert.assertEquals(category.getName(), "Star Wars");
    }

    @Test
    public void findById() throws Exception {
        Category category = categoryDao.findById(testCategory.getId());
        assertEquals(category, testCategory);
    }

    @Test
    public void update() throws Exception {
        Session session = (Session) em.getDelegate();
        testCategory.setDescription("Changed test category");
        categoryDao.update(testCategory);
        Category b_db = (Category) session.createQuery("FROM Category ").list().get(0);
        Assert.assertEquals(b_db.getDescription(), "Changed test category");
    }

    @Test
    public void delete() throws Exception {
        categoryDao.delete(testCategory);
        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Category").list().size();
        Assert.assertEquals(tableSize, 0);
    }

    @Test
    public void findAll() throws Exception {
        categoryList = categoryDao.findAll();
        Assert.assertEquals(categoryList.size(), 1);
    }

    @Test
    public void createNull() throws Exception {
        categoryDao.create(null);
    }

}
