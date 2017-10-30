package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Shape;


import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/**
 * @author Michal Peška
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShapeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ShapeDao shapeDao;
    
    @PersistenceContext
    private EntityManager em;
    
    Shape vaderShape;
    Shape blockShape;
    Shape gandalfShape;
    List<Shape> shapeList;
    
    
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
    public void create() throws Exception {
        Session session = (Session) em.getDelegate();
        Shape shape = (Shape) session.createQuery("FROM Shape").list().get(0);
        Assert.assertEquals(shape.getName(), "Darth Vader");        
    }
    
    @Test
    public void delete() throws Exception {
        shapeDao.delete(vaderShape);
        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Shape").list().size();
        Assert.assertEquals(tableSize, 2);
    }
    
    @Test
    public void update() throws Exception {
        Session session = (Session) em.getDelegate();
        blockShape.setName("Tower block");
        shapeDao.update(blockShape);
        Shape foundShape = (Shape) session.createQuery("FROM Shape ").list().get(0);
        Assert.assertEquals(foundShape.getName(), "Tower block");        
        
    }
    
    @Test
    public void findById() throws Exception {
        Shape shape = shapeDao.findById(gandalfShape.getId());
        Assert.assertEquals(shape.getName(), "Gandalf");        
        
    }
    
    @Test
    public void findAll() throws Exception {
        shapeList = shapeDao.findAll();
        Assert.assertEquals(shapeList.size(), 2);        
        
    }
    
    
    
   

}
