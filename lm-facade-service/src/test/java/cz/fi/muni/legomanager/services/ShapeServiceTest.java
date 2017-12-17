package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.ShapeDao;
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
import static org.testng.Assert.assertNotNull;

/**
 * Test class for {@link ShapeService}.
 *
 * @author Lukáš Dvořák
 */
@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ShapeServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ShapeDao shapeDao;

    @Autowired
    @InjectMocks
    private ShapeService shapeService;

    @Mock
    private Shape shape;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Shape> allShapes = new ArrayList<>();
        allShapes.add(shape);

        when(shape.getId()).thenReturn(1L);
        when(shape.getName()).thenReturn("Cube");

        when(shapeDao.findById(1L)).thenReturn(shape);
        when(shapeDao.findByName("Cube")).thenReturn(shape);

        when(shapeDao.findAll()).thenReturn(allShapes);
    }

    @Test
    public void testCreate() {
        shapeService.create(shape);
        verify(shapeDao).create(shape);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullShape() {
        shapeService.create(null);
    }

    @Test
    public void testUpdate() {
        shapeService.update(shape);
        verify(shapeDao).update(shape);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullShape() {
        shapeService.update(null);
    }

    @Test
    public void testDelete() {
        shapeService.delete(shape);
        verify(shapeDao).delete(shape);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullShape() {
        shapeService.delete(null);
    }

    @Test
    public void testFindById() {
        Shape found = shapeService.findById(1L);
        verify(shapeDao).findById(1L);

        assertNotNull(found);
        assertEquals(shape, found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        shapeService.findById(null);
    }

    @Test
    public void testFindByName() {
        Shape found = shapeService.findByName("Cube");
        verify(shapeDao).findByName("Cube");

        assertNotNull(found);
        assertEquals(shape, found);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullName() {
        shapeService.findByName(null);
    }

    @Test
    public void testFindAll() {
        List<Shape> found = shapeService.findAll();
        verify(shapeDao).findAll();

        List<Shape> expected = new ArrayList<>();
        expected.add(shape);

        assertEquals(expected, found);
    }
}
