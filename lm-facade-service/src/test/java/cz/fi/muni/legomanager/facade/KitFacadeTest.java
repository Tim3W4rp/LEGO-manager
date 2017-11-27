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

    @BeforeMethod
    public void setUp() {
        KitCreateDTO newKit = new KitCreateDTO();
        newKit.setAgeLimit(0);
        newKit.setDescription("");
        newKit.setPrice(0);

        newKitId = kitFacade.createKit(newKit);
    }

    @Test
    public void testCreateKit() {
        KitDTO returnedKitDTO = kitFacade.findKitById(newKitId);
        assertEquals(returnedKitDTO.getId(), newKitId);
    }

    @Test
    public void testFindKitById() {
        KitDTO found = kitFacade.findKitById(newKitId);
        assertEquals(found.getId(), newKitId);
    }

    @Test
    public void testUpdateKit() {
        KitDTO kitDTO = kitFacade.findKitById(newKitId);

        kitDTO.setPrice(100);
        kitFacade.updateKit(kitDTO);

        KitDTO changedKit = kitFacade.findKitById(newKitId);

        assertEquals(changedKit.getPrice().longValue(), 100L);
    }

    @Test
    public void testDeleteKitById() {
        assertEquals(kitFacade.findAllKits().size(), 1);

        kitFacade.deleteKitById(newKitId);

        assertEquals(kitFacade.findAllKits().size(), 0);
    }

    @Test
    public void testFindAllKits() {
        assertEquals(kitFacade.findAllKits().size(), 1);
    }


}