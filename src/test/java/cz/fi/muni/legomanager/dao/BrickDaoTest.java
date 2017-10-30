package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Shape;
import cz.fi.muni.legomanager.enums.*;

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
public class BrickDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BrickDao brickDao;

    //@Autowired
    private ShapeDao shapeDao;
    
    @PersistenceContext
    private EntityManager em;
    
    Brick greenBlockBrick;
    Brick redBlockBrick;
    Brick blackBlockBrick;
    Shape blockShape;
    List<Brick> brickList;
    
    
    @BeforeMethod
    public void setUp() {
        blockShape = new Shape();
        blockShape.setName("Block");
        shapeDao.create(blockShape);
        
        greenBlockBrick = new Brick();
        greenBlockBrick.setColor(Color.GREEN);
        greenBlockBrick.setShape(blockShape);
        brickDao.create(greenBlockBrick);
        
        redBlockBrick = new Brick();
        redBlockBrick.setColor(Color.RED);
        redBlockBrick.setShape(blockShape);
        brickDao.create(redBlockBrick);
        
        blackBlockBrick = new Brick();
        blackBlockBrick.setColor(Color.BLACK);
        blackBlockBrick.setShape(blockShape);
        brickDao.create(blackBlockBrick);
        
    }
    
    @Test
    public void create() throws Exception {
        Session session = (Session) em.getDelegate();
        Brick brick = (Brick) session.createQuery("FROM Brick").list().get(0);
        Assert.assertEquals(brick.getColor(), Color.GREEN);     
        Assert.assertEquals(brick.getShape(), blockShape);  
    }
    
    @Test
    public void delete() throws Exception {
        brickDao.delete(greenBlockBrick);
        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Brick").list().size();
        Assert.assertEquals(tableSize, 2);
    }
    
    @Test
    public void update() throws Exception {
        Session session = (Session) em.getDelegate();
        redBlockBrick.setColor(Color.PINK);
        brickDao.update(redBlockBrick);
        Brick foundBrick = (Brick) session.createQuery("FROM Brick ").list().get(0);
        Assert.assertEquals(foundBrick.getColor(), Color.PINK);        
        
    }
    
    @Test
    public void findById() throws Exception {
        Brick brick = brickDao.findById(blackBlockBrick.getId());
        Assert.assertEquals(brick.getColor(), Color.BLACK);        
    }
    
    @Test
    public void findAll() throws Exception {
        brickList = brickDao.findAll();
        Assert.assertEquals(brickList.size(), 2);        
        
    }
     
    @Test
    public void testBrickShapeRelationship() throws Exception{
                
        Assert.assertEquals(blackBlockBrick.getShape().getName(), "Block");
        Assert.assertEquals(blackBlockBrick.getColor(), Color.BLACK);
       
    }   
    
}
