package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsCreateDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.services.KitService;
import cz.fi.muni.legomanager.services.SetOfKitsService;
import cz.fi.muni.legomanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SetOfKitsFacadeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    SetOfKitsFacade setFacade;

    @Autowired
    KitService kitService;

    @Autowired
    CategoryService catService;

    @Autowired
    SetOfKitsService setOfKitsService;

    private SetOfKitsCreateDTO newSet;
    private long xWingKitId;
    private long deathStarKitId;


    @BeforeMethod
    public void beforeTest() {

        Kit xWingKit;
        Kit deathStarKit;

        newSet = new SetOfKitsCreateDTO();
        newSet.setDescription("Star Wars: Battle for Yavin IV");
        newSet.setPrice(BigDecimal.valueOf(1000000));


        Category swRebelsCategory = new Category();
        swRebelsCategory.setName("SW Rebels");
        swRebelsCategory.setDescription("Blabla");
        catService.create(swRebelsCategory);


        Category swEmpireCategory = new Category();
        swEmpireCategory.setName("SW Empire");
        swEmpireCategory.setDescription("Blablabla");
        catService.create(swEmpireCategory);


        xWingKit = new Kit();
        xWingKit.setDescription("X-Wing");
        xWingKit.setPrice(250);
        xWingKit.setAgeLimit(7);
        xWingKit.setCategory(swRebelsCategory);
        xWingKitId = kitService.createKit(xWingKit);


        deathStarKit = new Kit();
        deathStarKit.setDescription("X-Wing");
        deathStarKit.setPrice(250);
        deathStarKit.setAgeLimit(7);
        deathStarKit.setCategory(swEmpireCategory);
        deathStarKitId = kitService.createKit(deathStarKit);

    }


    @Test
    public void createSetTest() {
        Long id = setFacade.createSet(newSet);
        SetOfKitsDTO set = setFacade.findSetById(id);
        Assert.assertEquals(set.getId(), id);
        Assert.assertEquals(set.getDescription(), "Star Wars: Battle for Yavin IV");
    }

    @Test
    public void findSetByIdTest() {
        Long id = setFacade.createSet(newSet);
        SetOfKitsDTO set = setFacade.findSetById(id);
        Assert.assertEquals(set.getId(), id);
    }

    @Test
    public void updateSetTest() {
        Long id = setFacade.createSet(newSet);
        SetOfKitsDTO set = setFacade.findSetById(id);

        set.setDescription("Star Wars: Battle for Scarif");
        setFacade.updateSet(set);

        SetOfKitsDTO setChanged = setFacade.findSetById(id);
        Assert.assertEquals(setChanged.getDescription(), "Star Wars: Battle for Scarif");
    }

    @Test
    public void deleteSetByIdTest() {
        Long id = setFacade.createSet(newSet);
        Assert.assertEquals(setFacade.getAllSets().size(), 1);

        setFacade.deleteSetById(id);
        Assert.assertEquals(setFacade.getAllSets().size(), 0);
    }

    @Test
    public void changeDescriptionTest()  {
        Long id = setFacade.createSet(newSet);
        setFacade.changeDescription(id, "Some star trek stuff");
        SetOfKitsDTO set = setFacade.findSetById(id);
        Assert.assertEquals(set.getDescription(), "Some star trek stuff");
    }

    @Test
    public void changePriceTest() {
        Long id = setFacade.createSet(newSet);
        setFacade.changePrice(id, BigDecimal.ONE);
        SetOfKitsDTO set = setFacade.findSetById(id);
        Assert.assertEquals(set.getPrice(), BigDecimal.ONE);
    }

    @Test
    public void getAllSetsTest() {
        Long id = setFacade.createSet(newSet);
        Assert.assertEquals(setFacade.getAllSets().size(), 1);
    }

    @Test
    public void addKitToSetTest() {
        Long setId = setFacade.createSet(newSet);
        setFacade.addKitToSet(setId, xWingKitId);
        SetOfKitsDTO set = setFacade.findSetById(setId);
        Assert.assertEquals(set.getKits().size(), 1);
        Assert.assertEquals(set.getKits().get(0).getDescription(), "X-Wing");
    }

    @Test
    public void removeKitFromSetTest() {
        Long setId = setFacade.createSet(newSet);
        setFacade.addKitToSet(setId, xWingKitId);
        SetOfKitsDTO set = setFacade.findSetById(setId);
        Assert.assertEquals(set.getKits().size(), 1);

        setFacade.removeKitFromSet(setId, xWingKitId);
        SetOfKitsDTO setUpdated = setFacade.findSetById(setId);
        Assert.assertEquals(setUpdated.getKits().size(), 0);

    }

    @Test
    public void getSetCategoriesTest() {
        Long setId = setFacade.createSet(newSet);
        setFacade.addKitToSet(setId, deathStarKitId);

        Assert.assertEquals(setFacade.getSetCategories(setId).size(), 1);

    }



}
