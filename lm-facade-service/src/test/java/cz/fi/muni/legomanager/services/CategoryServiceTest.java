package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.CategoryDao;
import cz.fi.muni.legomanager.entity.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
public class CategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;

    @Autowired
    @InjectMocks
    private CategoryService categoryService;

    private Category newCategory;

    @BeforeMethod
    public void beforeTest() {

    }
}
