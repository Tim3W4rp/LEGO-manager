package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.KitBrick;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;


@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class KitServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private Kit kit;

    @Mock
    private KitDao kitDao;

    @Mock
    private BrickService brickService;

    @Mock
    private CategoryService categoryService;

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

    @Mock
    private KitBrickService kitBrickService;

    @Autowired
    @InjectMocks
    private KitService kitService;

    @BeforeMethod
    public void setUp() {
        initMocks(this);

        List<Kit> allKits = new ArrayList<>();
        Set<Kit> kitsInSet = new HashSet<>();
        Set<Kit> kitsInCategory = new HashSet<>();

        allKits.add(kit);
        kitsInSet.add(kit);
        kitsInCategory.add(kit);

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

        when(brickService.findById(1L)).thenReturn(brick);
        when(categoryService.getCategory(1L)).thenReturn(category);
        when(kitDao.findById(1L)).thenReturn(kit);
        when(kitDao.findAll()).thenReturn(allKits);

        when(kitBrickService.getBrickCount(kit, brick)).thenReturn(2L);
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
    public void testAddBrickToKitById() {
        kitService.addBrickToKitById(1L, 1L);
        Kit kit = kitDao.findById(1L);
        Brick brick = brickService.findById(1L);
        verify(kitBrickService).addBrickToKit(kit, brick);
    }

    @Test
    public void testRemoveOneBrickFromKitById() {
        kitService.decreaseBrickCountByOne(1L, 1L);
        Kit kit = kitDao.findById(1L);
        Brick brick = brickService.findById(1L);
        verify(kitBrickService).decreaseBrickCountByOne(kit, brick);
    }

    @Test
    public void testRemoveAllBricksOfThisTypeFromKitById() {
        kitService.removeAllBricksOfThisTypeFromKitById(1L, 1L);
        Kit kit = kitDao.findById(1L);
        Brick brick = brickService.findById(1L);
        verify(kitBrickService).removeAllBricksOfThisType(kit, brick);
    }

    @Test
    public void testGetBrickCount() {
        Kit kit = kitDao.findById(1L);
        Brick brick = brickService.findById(1L);
        assertEquals(kitBrickService.getBrickCount(kit, brick), 2L);
    }

    @Test
    public void testSetBrickCount() {
        Kit kit = kitDao.findById(1L);
        Brick brick = brickService.findById(1L);
        kitBrickService.setBrickCount(kit, brick, 2L);
        assertEquals(kitBrickService.getBrickCount(kit, brick), 2L);
    }

    @Test
    public void testFindSimilarKits() {
        Category c1 = new Category("category1", "desc1");
        Category c2 = new Category("category2", "desc2");


        List<Kit> kits = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            kits.add(generateKitInRange(10, 20, 0, 10, c1));
        }
        for (int i = 0; i < 100; i++) {
            kits.add(generateKitInRange(21, 30, 0, 10, c1));
        }
        for (int i = 0; i < 100; i++) {
            kits.add(generateKitInRange(10, 20, 11, 20, c1));
        }
        for (int i = 0; i < 100; i++) {
            kits.add(generateKitInRange(10, 20, 11, 20, c1));
        }
        for (int i = 0; i < 100; i++) {
            kits.add(generateKitInRange(10, 20, 0, 10, c2));
        }

        when(kitDao.findAll()).thenReturn(kits);

        // similar kits of category 1 within some range
        Assert.assertEquals(100, kitService.findSimilarKits(
                generateKitInRange(15, 15, 5, 5, c1),
                5,
                5,
                c1
        ).size());

        // filter all kits with category c2
        Assert.assertEquals(100, kitService.findSimilarKits(
                generateKitInRange(15, 15, 5, 5, c1),
                100,
                100,
                c2
        ).size());

        // filter all kits in price range and category c1
        Assert.assertEquals(200, kitService.findSimilarKits(
                generateKitInRange(15, 15, 5, 5, c1),
                100,
                5,
                c1
        ).size());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testFindSimilarKitsEmpty() {
        when(kitDao.findAll()).thenReturn(new ArrayList<>());
        kitService.findSimilarKits(kit, 5, 5, category);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testFindSimilarKitsNull() {
        kitService.findSimilarKits(null, 5, 5, category);
    }


    @Test
    public void testFindBrickById() {
        kitService.findBrickById(1L);
        Brick brick = brickService.findById(1L);
        assertEquals(brick.getId().longValue(), 1L);
    }

    @Test
    public void testAddKitToSet() {
        kitService.addKitToSet(kit, setOfKits);
        verify(setOfKits).addKit(kit);
    }

    private Kit generateKitInRange(Integer priceMin, Integer priceMax, Integer ageMin, Integer ageMax, Category category) {
        Kit kit = mock(Kit.class);
        when(kit.getPrice()).thenReturn(randomInRange(priceMin, priceMax));
        when(kit.getAgeLimit()).thenReturn(randomInRange(ageMin, ageMax));
        when(kit.getCategory()).thenReturn(category);
        return kit;
    }

    private int randomInRange(Integer min, Integer max) {
        return min + (int) (Math.random() * ((max - min) + 1));

    }

}