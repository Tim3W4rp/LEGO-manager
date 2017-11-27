package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Shape;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Test class for {@link BrickService}.
 *
 * @author Lukáš Dvořák
 */
@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class BrickServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BrickDao brickDao;

    @Autowired
    @InjectMocks
    private BrickService brickService;

    @Mock
    private Brick brick;

    @Mock
    private Shape shape;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Brick> allBricks = new ArrayList<>();
        allBricks.add(brick);

        when(brick.getId()).thenReturn(1L);
        when(brick.getRed()).thenReturn(0);
        when(brick.getGreen()).thenReturn(255);
        when(brick.getBlue()).thenReturn(0);
        when(brick.getShape()).thenReturn(shape);

        when(brickDao.findById(1L)).thenReturn(brick);

        when(brickDao.findAll()).thenReturn(allBricks);
    }

    @Test
    public void testCreate() {
        brickService.create(brick);
        verify(brickDao).create(brick);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullBrick() {
        brickService.create(null);
    }

    @Test
    public void testUpdate() {
        brickService.update(brick);
        verify(brickDao).update(brick);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullBrick() {
        brickService.update(null);
    }

    @Test
    public void testDelete() {
        brickService.delete(brick);
        verify(brickDao).delete(brick);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullBrick() {
        brickService.delete(null);
    }

    @Test
    public void testFindById() {
        Brick found = brickService.findById(1L);
        verify(brickDao).findById(1L);
        assertEquals(brick, found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        brickService.findById(null);
    }

    @Test
    public void testFindAll() {
        List<Brick> found = brickService.findAll();
        verify(brickDao).findAll();

        List<Brick> expected = new ArrayList<>();
        expected.add(brick);

        assertEquals(expected, found);
    }
}
