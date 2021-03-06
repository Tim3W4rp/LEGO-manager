package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.dto.KitSimpleDTO;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.services.CategoryService;
import cz.fi.muni.legomanager.services.DozerService;
import cz.fi.muni.legomanager.services.KitService;
import cz.fi.muni.legomanager.services.SetOfKitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private KitService kitService;

    @Autowired
    SetOfKitsService setOfKitsService;

    @Autowired
    private DozerService dozerService;


    @Override
    public Long createCategory(CategoryDTO category) {
        Category mappedCategory = dozerService.mapTo(category, Category.class);
        categoryService.create(mappedCategory);
        return mappedCategory.getId();
    }

    @Override
    public void updateCategory(CategoryDTO category) {
        Category mappedCategory = dozerService.mapTo(category, Category.class);
        categoryService.update(mappedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return dozerService.mapTo(categoryService.getAllCategories(), CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        CategoryDTO categoryDTO = dozerService.mapTo(category, CategoryDTO.class);
        categoryDTO.setKits(this.getKits(category.getId()));
        return categoryDTO;
    }

    @Override
    public List<KitSimpleDTO> getKits(Long categoryId) {
        Category c = categoryService.getCategory(categoryId);
        if (c == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        return dozerService.mapTo(c.getKits(), KitSimpleDTO.class);
    }

    @Override
    public void removeCategory(Long categoryId) {
        categoryService.delete(categoryId);
    }
}
