package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.ShapeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test class for {@link ShapeFacade}.
 *
 * @author Lukáš Dvořák
 */
@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShapeFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ShapeFacade shapeFacade;

    private Long shapeId;

    @BeforeMethod
    public void setUp() {

        ShapeDTO newShape = new ShapeDTO();
        newShape.setName("Cube");

        shapeId = shapeFacade.create(newShape);
    }

    @Test
    public void testCreate() {
        ShapeDTO returnedShapeDTO = shapeFacade.findById(shapeId);

        assertEquals(returnedShapeDTO.getId(), shapeId);
    }

    @Test
    public void testUpdate() {
        ShapeDTO shapeDTO = shapeFacade.findById(shapeId);

        shapeDTO.setName("New cube");
        shapeFacade.update(shapeDTO);

        ShapeDTO shapeChanged = shapeFacade.findById(shapeId);

        assertEquals(shapeChanged.getName(), "New cube");
    }

    @Test
    public void testDelete() {
        assertEquals(shapeFacade.findAll().size(), 1);

        shapeFacade.delete(shapeId);

        assertEquals(shapeFacade.findAll().size(), 0);
    }

    @Test
    public void testFindById() {
        ShapeDTO found = shapeFacade.findById(shapeId);
        assertEquals(found.getId(), shapeId);
    }

    @Test
    public void testFindAll() {
        assertEquals(shapeFacade.findAll().size(), 1);
    }

}
