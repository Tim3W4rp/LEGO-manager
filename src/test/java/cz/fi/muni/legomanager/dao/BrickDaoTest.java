package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Brick;
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
public class BrickDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BrickDao brickDao;

    @Test
    public void testBrickDao() {
        
        Brick firstBrick = new Brick();
        firstBrick.setColor(Color.BLACK);
        brickDao.create(firstBrick);
        
        Brick secondBrick = new Brick();
        secondBrick.setColor(Color.WHITE);
        brickDao.create(secondBrick);
        
        
        Assert.assertTrue(brickDao.findAll().size() == 2);
        Assert.assertTrue(brickDao.findById(Long.valueOf(1)) != null);
        Assert.assertTrue(brickDao.findById(Long.valueOf(1)).equals(firstBrick));
        Assert.assertFalse(brickDao.findById(Long.valueOf(1)).equals(secondBrick));
        
        Brick locatedBrick = brickDao.findById(Long.valueOf(1));
        Assert.assertTrue(locatedBrick.getColor().equals(Color.BLACK));
        
        
        brickDao.delete(locatedBrick);
        
        
        List<Brick> brickList = brickDao.findAll();
        Assert.assertTrue(brickList.size() == 1);
        brickList.get(0).setColor(Color.GREEN);
        
        brickDao.update(brickList.get(0));
        
        List<Brick> greenBrickList = brickDao.findAll();
        Assert.assertTrue(greenBrickList.size() == 1);
        Assert.assertTrue(greenBrickList.get(0).getColor() == Color.GREEN);
        
        
    }
    
    
    
}
