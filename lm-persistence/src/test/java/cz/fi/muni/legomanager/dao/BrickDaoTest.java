package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Shape;

import org.hibernate.Session;
import org.junit.Assert;
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

/**
 * Test of {@link BrickDao} methods.
 * 
 * @author Michal Peška
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BrickDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BrickDao brickDao;

    @Autowired
    private ShapeDao shapeDao;
    
    @PersistenceContext
    private EntityManager em;
    
    Brick greenBlockBrick;
    Brick redBlockBrick;
    Brick blueBlockBrick;
    Shape blockShape;
    List<Brick> brickList;
    
    
    @BeforeMethod
    public void setUp() {
        blockShape = new Shape();
        blockShape.setName("Block");
        shapeDao.create(blockShape);

        redBlockBrick = new Brick();
        redBlockBrick.setRed(255);
        redBlockBrick.setGreen(0);
        redBlockBrick.setBlue(0);
        redBlockBrick.setShape(blockShape);
        brickDao.create(redBlockBrick);

        greenBlockBrick = new Brick();
        greenBlockBrick.setRed(0);
        greenBlockBrick.setGreen(255);
        greenBlockBrick.setBlue(0);
        greenBlockBrick.setShape(blockShape);
        brickDao.create(greenBlockBrick);
        
        blueBlockBrick = new Brick();
        blueBlockBrick.setRed(0);
        blueBlockBrick.setGreen(0);
        blueBlockBrick.setBlue(255);
        blueBlockBrick.setShape(blockShape);
        brickDao.create(blueBlockBrick);
        
    }
    
    @Test
    public void create() throws Exception {
        Session session = (Session) em.getDelegate();
        Brick brick = (Brick) session.createQuery("FROM Brick").list().get(0);
        Assert.assertEquals(255, brick.getRed());
    }
    
    @Test
    public void delete() throws Exception {
        brickDao.delete(greenBlockBrick);
        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Brick").list().size();
        Assert.assertEquals(2, tableSize);
    }
    
    @Test
    public void update() throws Exception {
        Session session = (Session) em.getDelegate();
        redBlockBrick.setRed(0);
        redBlockBrick.setGreen(255);
        redBlockBrick.setBlue(0);
        brickDao.update(redBlockBrick);
        Brick foundBrick = (Brick) session.createQuery("FROM Brick ").list().get(1);
        Assert.assertEquals(0, foundBrick.getRed());
        Assert.assertEquals(255, foundBrick.getGreen());
        Assert.assertEquals(0, foundBrick.getBlue());
    }
    
    @Test
    public void findById() throws Exception {
        Brick brick = brickDao.findById(blueBlockBrick.getId());
        Assert.assertEquals(blueBlockBrick.getId(), brick.getId());
    }
    
    @Test
    public void findAll() throws Exception {
        brickList = brickDao.findAll();
        Assert.assertEquals(3, brickList.size());
        
    }
     
    @Test
    public void testBrickShapeRelationship() throws Exception{
        Assert.assertEquals("Block", blueBlockBrick.getShape().getName());
       
    }   
    
}
