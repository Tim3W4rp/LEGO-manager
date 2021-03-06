package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Shape;
import org.hibernate.Session;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Test of {@link ShapeDao} methods.
 *
 * @author Michal Peška
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShapeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ShapeDao shapeDao;

    @PersistenceContext
    private EntityManager em;

    private Shape vaderShape;
    private Shape blockShape;
    private Shape gandalfShape;
    private List<Shape> shapeList;


    @BeforeMethod
    public void setUp() {
        vaderShape = new Shape();
        vaderShape.setName("Darth Vader");
        shapeDao.create(vaderShape);

        blockShape = new Shape();
        blockShape.setName("Block");
        shapeDao.create(blockShape);

        gandalfShape = new Shape();
        gandalfShape.setName("Gandalf");
        shapeDao.create(gandalfShape);

    }

    @Test
    public void create() {
        Session session = (Session) em.getDelegate();
        Shape shape = (Shape) session.createQuery("FROM Shape").list().get(0);
        Assert.assertEquals(shape.getName(), "Darth Vader");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createWithNull() {
        shapeDao.create(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createDuplicate() {
        shapeDao.create(vaderShape);
    }

    @Test
    public void delete() {
        shapeDao.delete(vaderShape);
        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Shape").list().size();
        Assert.assertEquals(2, tableSize);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteWithNull() {
        shapeDao.delete(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteDuplicate() {
        shapeDao.delete(vaderShape);
        shapeDao.delete(vaderShape);
    }

    @Test
    public void update() {
        Session session = (Session) em.getDelegate();
        blockShape.setName("Tower block");
        shapeDao.update(blockShape);
        Shape foundShape = (Shape) session.createQuery("FROM Shape ").list().get(1);
        Assert.assertEquals(foundShape.getName(), "Tower block");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNull() {
        shapeDao.update(null);
    }

    @Test
    public void findById() {
        Shape shape = shapeDao.findById(gandalfShape.getId());
        Assert.assertEquals(shape.getName(), "Gandalf");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findWithNull() {
        shapeDao.findById(null);
    }

    @Test
    public void findByName() {
        Shape found = shapeDao.findByName(vaderShape.getName());
        Assert.assertEquals(found, vaderShape);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingName() {
        shapeDao.findByName("Non existing name");
    }

    @Test
    public void findAll() {
        shapeList = shapeDao.findAll();
        Assert.assertEquals(3, shapeList.size());
    }


}
