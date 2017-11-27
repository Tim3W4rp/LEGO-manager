package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Kit;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = ServiceConfigurationContext.class)
public class KitServiceTest extends AbstractTestNGSpringContextTests {

    private Kit milleniumFalcon;

    private Kit darthVaderStatue;

    @Mock
    private KitDao kitDao;

    @Autowired
    @InjectMocks
    private KitService kitService;

    @BeforeClass
    public void beforeClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        kitService = mock(KitService.class);
    }

    @BeforeMethod
    public void setUp() throws Exception {

        milleniumFalcon = new Kit ();
        milleniumFalcon.setDescription("Epic Han Solo's ship");
        milleniumFalcon.setPrice(1599);
        milleniumFalcon.setAgeLimit(12);
        milleniumFalcon.setCategories(null);
        milleniumFalcon.setSetOfKits(null);
        milleniumFalcon.setKitBricks(null);

        darthVaderStatue = new Kit ();
        darthVaderStatue.setDescription("A Darth Vader statue with red lightsaber");
        darthVaderStatue.setPrice(699);
        darthVaderStatue.setAgeLimit(13);
        darthVaderStatue.setCategories(null);
        darthVaderStatue.setSetOfKits(null);
        darthVaderStatue.setKitBricks(null);
    }

    @Test
    public void testCreateKit() throws Exception {
        when(kitService.createKit(milleniumFalcon)).thenReturn(1L);
        when(kitService.createKit(darthVaderStatue)).thenReturn(2L);

        Long id = kitService.createKit(milleniumFalcon);
        Long id2 = kitService.createKit(darthVaderStatue);

        Assert.assertEquals(id.longValue(), 1L);
        Assert.assertEquals(id2.longValue(), 2L);
    }

    @Test
    public void testFindKitById() throws Exception {
        when(kitService.findKitById(1)).thenReturn(milleniumFalcon);
        Assert.assertEquals(kitService.findKitById(1), milleniumFalcon);
    }

    @Test
    public void testUpdateKit() throws Exception {

    }

    @Test
    public void testDeleteKitById() throws Exception {

    }

    @Test
    public void testFindAllKits() throws Exception {
    }

    @Test
    public void testGetKitsByCategory() throws Exception {
    }

    @Test
    public void testAddCategory() throws Exception {
    }

    @Test
    public void testRemoveCategory() throws Exception {
    }

    @Test
    public void testGetKitCategories() throws Exception {
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

    @Test
    public void testFindOneRandomSimilarKit() throws Exception {
    }

    @Test
    public void testFindSimilarKits() throws Exception {
    }

    @Test
    public void testFindBrickById() throws Exception {
    }

}