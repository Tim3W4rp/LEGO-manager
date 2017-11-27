package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class KitFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private KitFacade kitFacade;

    private Long newKitId;
    private Long existingKitId;

    @BeforeMethod
    public void setUp() {

        KitCreateDTO newKit = new KitCreateDTO();
        newKitId = kitFacade.createKit(newKit);

        KitDTO existingKit = new KitDTO();
    }

    @Test
    public void testCreateKit() throws Exception {
    }

    @Test
    public void testFindKitById() throws Exception {
    }

    @Test
    public void testUpdateKit() throws Exception {
    }

    @Test
    public void testDeleteKitById() throws Exception {
    }

    @Test
    public void testChangeDescription() throws Exception {
    }

    @Test
    public void testChangePrice() throws Exception {
    }

    @Test
    public void testChangeAgeLimit() throws Exception {
    }

    @Test
    public void testFindAllKits() throws Exception {
    }

    @Test
    public void testGetKitsByCategoryId() throws Exception {
    }

    @Test
    public void testAddBrickToKit() throws Exception {
    }

    @Test
    public void testRemoveOneBrickFromKitById() throws Exception {
    }

    @Test
    public void testRemoveAllBricksOfThisTypeFromKitById() throws Exception {
    }

}