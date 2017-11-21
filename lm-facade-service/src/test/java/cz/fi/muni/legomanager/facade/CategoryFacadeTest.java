package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
public class CategoryFacadeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CategoryFacade categoryFacade;


    CategoryDTO newCategory;


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
}
