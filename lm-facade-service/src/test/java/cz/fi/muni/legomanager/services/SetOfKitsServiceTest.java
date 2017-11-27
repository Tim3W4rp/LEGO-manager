package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.dao.SetOfKitsDao;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SetOfKitsServiceTest {
    @Mock
    private SetOfKitsDao setDao;

    @Mock
    private KitDao kitDao;

    @InjectMocks
    private SetOfKitsServiceImpl setService;


    private SetOfKits set1;

    private SetOfKits set2;

    @Before
    public void setUp() {

        set1 = new SetOfKits();
        set1.setPrice(BigDecimal.ONE);
        set1.setDescription("Star Wars");

        set2 = new SetOfKits();
        set2.setPrice(BigDecimal.TEN);
        set2.setDescription("Batman");

        when(setDao.findById(anyLong())).thenReturn(set1);

        ArrayList<SetOfKits> mySets = new ArrayList<>();
        mySets.add(set1);
        mySets.add(set2);
        when(setDao.findAll()).thenReturn(mySets);
    }


    @Test
    public void getSetTest() {

        SetOfKits foundSet = setService.getSet(1L);
        Assert.assertEquals(BigDecimal.ONE, foundSet.getPrice());
        Assert.assertEquals("Star Wars", foundSet.getDescription());
    }


    @Test
    public void getAllSetsTest() {


        List<SetOfKits> listOfSets = setService.getAllSets();

        Assert.assertEquals(BigDecimal.ONE, listOfSets.get(0).getPrice());
        Assert.assertEquals(BigDecimal.TEN, listOfSets.get(1).getPrice());

        Assert.assertEquals("Star Wars", listOfSets.get(0).getDescription());
        Assert.assertEquals("Batman", listOfSets.get(1).getDescription());
    }

    @Test
    public void createTest() {
        setService.create(set1);
        verify(setDao, times(1)).create(set1);
    }

    @Test
    public void deleteTest() {
        setService.delete(1L);
        verify(setDao, times(1)).delete(set1);
    }

    @Test
    public void updateTest() {
        setService.update(set1);
        verify(setDao, times(1)).update(set1);

    }

    @Test
    public void removeKitFromSetTest() {

        Kit kit = new Kit();
        kit.setAgeLimit(15);
        kit.setDescription("ABC");

        SetOfKits set = mock(SetOfKits.class);
        set.setDescription("EFG");

        when(setDao.findById(3L)).thenReturn(set);
        when(kitDao.findById(1L)).thenReturn(kit);

        setService.removeKitFromSet(3L, 1);
        verify(set, times(1)).removeKit(kit);
    }

    @Test
    public void addKitToSetTest() {
        Kit kit = new Kit();
        kit.setAgeLimit(10);
        kit.setDescription("ABC");

        SetOfKits set = mock(SetOfKits.class);
        set.setDescription("EFG");

        when(setDao.findById(3L)).thenReturn(set);
        when(kitDao.findById(1L)).thenReturn(kit);

        setService.addKitToSet(3L, 1);
        verify(set, times(1)).addKit(kit);
    }

    @Test
    public void getEmptySetCategoriesTest() {
        List<Category> list = setService.getSetCategories(1);
        Assert.assertEquals(0, list.size());
    }


}
