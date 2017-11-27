package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.BrickDTO;
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
 * Test class for {@link BrickFacade}.
 *
 * @author Lukáš Dvořák
 */
@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BrickFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BrickFacade brickFacade;

    private Long brickId;

    @BeforeMethod
    public void setUp() {

        BrickDTO newBrick = new BrickDTO();
        newBrick.setRed(0);
        newBrick.setGreen(255);
        newBrick.setBlue(0);

        brickId = brickFacade.create(newBrick);
    }

    @Test
    public void testCreate() {
        BrickDTO returnedBrickDTO = brickFacade.findById(brickId);

        assertEquals(returnedBrickDTO.getId(), brickId);
    }

    @Test
    public void testUpdate() {
        BrickDTO brickDTO = brickFacade.findById(brickId);

        brickDTO.setBlue(255);
        brickFacade.update(brickDTO);

        BrickDTO brickChanged = brickFacade.findById(brickId);

        assertEquals(brickChanged.getBlue(), 255);
    }

    @Test
    public void testDelete() {
        assertEquals(brickFacade.findAll().size(), 1);

        brickFacade.delete(brickId);

        assertEquals(brickFacade.findAll().size(), 0);
    }

    @Test
    public void testFindById() {
        BrickDTO found = brickFacade.findById(brickId);
        assertEquals(found.getId(), brickId);
    }

    @Test
    public void testFindAll() {
        assertEquals(brickFacade.findAll().size(), 1);
    }

}
