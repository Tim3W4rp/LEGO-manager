package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Shape;
import cz.fi.muni.legomanager.enums.*;

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

    @Test
    public void testShapeDao() {
        
        Shape firstShape = new Shape();
        firstShape.setName("Block");
        shapeDao.create(firstShape);
        
        Shape secondShape = new Shape();
        secondShape.setName("Man");
        shapeDao.create(secondShape);
        
        
        Assert.assertTrue(shapeDao.findAll().size() == 2);
        Assert.assertTrue(shapeDao.findById(Long.valueOf(1)) != null);
        Assert.assertTrue(shapeDao.findById(Long.valueOf(1)).equals(firstShape));
        Assert.assertFalse(shapeDao.findById(Long.valueOf(1)).equals(secondShape));
        
        Shape locatedShape = shapeDao.findById(Long.valueOf(1));
        Assert.assertTrue(locatedShape.getName().equals("Block"));
        
        
        shapeDao.delete(locatedShape);
        
        
        List<Shape> shapeList = shapeDao.findAll();
        Assert.assertTrue(shapeList.size() == 1);
        shapeList.get(0).setName("Window");
        
        shapeDao.update(shapeList.get(0));
        
        List<Shape> windowShapeList = shapeDao.findAll();
        Assert.assertTrue(windowShapeList.size() == 1);
        Assert.assertTrue(windowShapeList.get(0).getName().equals("Window"));
        
        
    }

}
