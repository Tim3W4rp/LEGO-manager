package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Stepan Granat
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
public class CategoryDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CategoryDao categoryDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testCreate() {
        Category c = new Category("TestName", "Test description");

        System.out.println(categoryDao);

        System.out.println(em);

        categoryDao.create(c);
        
    }
}
