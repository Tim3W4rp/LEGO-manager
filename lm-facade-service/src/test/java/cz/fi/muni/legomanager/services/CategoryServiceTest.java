package cz.fi.muni.legomanager.services;

import com.sun.javafx.UnmodifiableArrayList;
import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.CategoryDao;
import cz.fi.muni.legomanager.entity.Category;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
public class CategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;

    @Autowired
    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category newCategory;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);

        newCategory = new Category();
        newCategory.setName("Hot category");
        newCategory.setDescription("I mean hot not like hoot");

        List<Category> categories = new UnmodifiableArrayList<>(new Category[]{newCategory}, 1);

        Mockito.when(categoryDao.findById(1L)).thenReturn(newCategory);
        Mockito.when(categoryDao.findAll()).thenReturn(categories);
    }

    @Test
    public void create() {
        categoryService.create(newCategory);
        Mockito.verify(categoryDao, Mockito.times(1)).create(newCategory);
    }

    @Test
    public void getCategory() {
        Category c = categoryService.getCategory(1L);
        Mockito.verify(categoryDao, Mockito.times(1)).findById(1L);
        Assert.assertEquals(c, newCategory);
    }

    @Test
    public void delete() {
        categoryService.delete(1L);
        Mockito.verify(categoryDao, Mockito.times(1)).delete(newCategory);
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        Assert.assertEquals(categories.get(0), newCategory);
    }

    @Test
    public void update() {
        categoryService.update(newCategory);
        Mockito.verify(categoryDao, Mockito.times(1)).update(newCategory);
    }
}
