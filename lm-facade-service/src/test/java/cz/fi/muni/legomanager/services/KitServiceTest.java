package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.dao.CategoryDao;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;


@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class KitServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private Kit kit;

    @Mock
    private KitDao kitDao;

    @Mock
    private BrickDao brickDao;

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private SetOfKits setOfKits;

    @Mock
    private List<KitBrick> kitBricks;

    @Mock
    private KitBrick kitBrick;

    @Mock
    private Category category;

    @Mock
    private Brick brick;

    @Autowired
    @InjectMocks
    private KitService kitService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Kit> allKits = new ArrayList<>();
        Set<Kit> kitsInSet = new HashSet<>();
        Set<Kit> kitsInCategory = new HashSet<>();

        kitsInCategory.add(kit);
        allKits.add(kit);
        kitsInSet.add(kit);

        when(kit.getId()).thenReturn(1L);
        when(kit.getPrice()).thenReturn(0);
        when(kit.getAgeLimit()).thenReturn(12);
        when(kit.getDescription()).thenReturn("");
        when(kit.getSetOfKits()).thenReturn(setOfKits);
        when(kit.getCategory()).thenReturn(category);
        when(kit.getKitBricks()).thenReturn(kitBricks);
        when(brick.getId()).thenReturn(1L);
        when(brick.getKitBricks()).thenReturn(kitBricks);
        when(category.getId()).thenReturn(1L);
        when(category.getKits()).thenReturn(kitsInCategory);
        when(kitBrick.getBrick()).thenReturn(brick);
        when(kitBrick.getKit()).thenReturn(kit);
        when(setOfKits.getKits()).thenReturn(kitsInSet);

        when(brickDao.findById(1L)).thenReturn(brick);
        when(kitDao.findById(1L)).thenReturn(kit);
        when(kitDao.findAll()).thenReturn(allKits);
    }

    @Test
    public void testCreateKit() {
        kitService.createKit(kit);
        verify(kitDao).create(kit);
    }

    @Test
    public void testFindKitById() {
        Kit foundKit = kitService.findKitById(1L);
        verify(kitDao).findById(1L);
        assertEquals(foundKit, kit);
    }

    @Test
    public void testUpdateKit() {
        kitService.updateKit(kit);
        verify(kitDao).update(kit);
    }

    @Test
    public void testDeleteKitById() {
        kitService.deleteKitById(1L);
        verify(kitDao).delete(kit);
    }

    @Test
    public void testFindAllKits() {
        List<Kit> foundKits = kitService.findAllKits();
        verify(kitDao).findAll();

        List<Kit> expectedKits = new ArrayList<>();
        expectedKits.add(kit);

        assertEquals(expectedKits, foundKits);
    }

    @Test
    public void testGetKitsByCategoryId() {
    }

    @Test
    public void testAddBrickToKitById() {
        kitService.addBrickToKitById(1L, 1L);
        Kit kit = kitDao.findById(1L);
        Brick brick = brickDao.findById(1L);
        verify(kit).addBrick(brick);
    }

    @Test
    public void testRemoveOneBrickFromKitById() {
    }

    @Test
    public void testRemoveAllBricksOfThisTypeFromKitById() {
    }

    @Test
    public void testFindSimilarKits() {
    }

    @Test
    public void testFindBrickById() {
    }

    @Test
    public void testAddKitToSet() {
        kitService.addKitToSet(kit, setOfKits);
        verify(setOfKits).addKit(kit);
    }

}