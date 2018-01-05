package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Category;
import org.hibernate.Session;
import org.junit.Assert;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
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
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
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

    @BeforeMethod
    public void setUp() {
        starWarsCategory = new Category("Star Wars", "A universe of Jedis and space battles");
        testCategory = new Category("Test", "Test category");
        categoryDao.create(testCategory);
    }

    @Test
    public void create() {
        categoryDao.create(starWarsCategory);
        Session session = (Session) em.getDelegate();
        Category category = (Category) session.createQuery("FROM Category ").list().get(1);
        Assert.assertEquals(category.getName(), "Star Wars");
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void createCategoryWithNull() {
        categoryDao.create(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void createCategoryWithNullName() {
        testCategory.setName(null);
        categoryDao.create(testCategory);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void createAlreadyExists() {
        categoryDao.create(testCategory);
    }

    @Test
    public void update() {
        Session session = (Session) em.getDelegate();
        testCategory.setDescription("Changed test category");
        categoryDao.update(testCategory);
        Category b_db = (Category) session.createQuery("FROM Category ").list().get(0);
        Assert.assertEquals(b_db.getDescription(), "Changed test category");
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void updateWithNull() {
        categoryDao.update(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullName() {
        testCategory.setName(null);
        categoryDao.update(testCategory);
    }

    @Test
    public void delete() {
        categoryDao.delete(testCategory);
        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Category").list().size();
        Assert.assertEquals(0, tableSize);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void deleteWithNull() {
        categoryDao.delete(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void deleteDoesNotExist() {
        categoryDao.delete(starWarsCategory);
    }

    @Test
    public void findById() {
        Category category = categoryDao.findById(testCategory.getId());
        assertEquals(category, testCategory);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findByIdNull() {
        categoryDao.findById(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findByIdDoesNotExist() {
        categoryDao.findById(999L);
    }

    @Test
    public void findAll() {
        categoryList = categoryDao.findAll();
        Assert.assertEquals(1, categoryList.size());
    }

}
