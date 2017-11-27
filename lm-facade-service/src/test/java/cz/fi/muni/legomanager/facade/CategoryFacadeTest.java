package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CategoryFacadeTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;


    @Autowired
    CategoryFacade categoryFacade;

    @Autowired
    KitFacade kitFacade;

    private Long categoryId;


    @BeforeMethod
    public void beforeTest() {
        CategoryDTO newCategory = new CategoryDTO();
        newCategory.setName("Ahoj");
        newCategory.setDescription("Blabla");

        categoryId = categoryFacade.createCategory(newCategory);
    }

    @Test
    public void create() {
        CategoryDTO cat = categoryFacade.getCategoryById(categoryId);
        Assert.assertEquals(cat.getId(), categoryId);
    }

    @Test
    public void update() {
        CategoryDTO cat = categoryFacade.getCategoryById(categoryId);

        cat.setName("Hello");
        categoryFacade.updateCategory(cat);

        CategoryDTO catChanged = categoryFacade.getCategoryById(categoryId);
        Assert.assertEquals(catChanged.getName(), "Hello");
    }

    @Test
    public void getAllCategories() {
        Assert.assertEquals(categoryFacade.getAllCategories().size(), 1);
    }

    @Test
    public void getKits() {
        // TODO: waiting for correct KitDTO
    }

    @Test
    public void removeCategory() {

        Assert.assertEquals(categoryFacade.getAllCategories().size(), 1);

        categoryFacade.removeCategory(categoryId);

        Assert.assertEquals(categoryFacade.getAllCategories().size(), 0);
    }
}
