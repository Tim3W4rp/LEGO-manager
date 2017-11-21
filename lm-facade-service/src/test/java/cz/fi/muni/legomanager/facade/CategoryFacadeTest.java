package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CategoryFacadeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CategoryFacade categoryFacade;

    private CategoryDTO newCategory;


    @BeforeMethod
    public void beforeTest() {
        newCategory = new CategoryDTO();
        newCategory.setName("Ahoj");
        newCategory.setDescription("Blabla");
    }

    @Test
    public void create() {
        Long id = categoryFacade.createCategory(newCategory);
        CategoryDTO cat = categoryFacade.getCategoryById(id);
        Assert.assertEquals(cat.getId(), id);
    }

    @Test
    public void update() {
        Long id = categoryFacade.createCategory(newCategory);
        CategoryDTO cat = categoryFacade.getCategoryById(id);

        cat.setName("Hello");
        categoryFacade.updateCategory(cat);

        CategoryDTO catChanged = categoryFacade.getCategoryById(id);
        Assert.assertEquals(catChanged.getName(), "Hello");
    }

    @Test
    public void getAllCategories() {
        Long id = categoryFacade.createCategory(newCategory);

        Assert.assertEquals(categoryFacade.getAllCategories().size(), 1);
    }

    @Test
    public void removeCategory() {
        Long id = categoryFacade.createCategory(newCategory);

        Assert.assertEquals(categoryFacade.getAllCategories().size(), 1);

        categoryFacade.removeCategory(id);

        Assert.assertEquals(categoryFacade.getAllCategories().size(), 0);
    }



}
