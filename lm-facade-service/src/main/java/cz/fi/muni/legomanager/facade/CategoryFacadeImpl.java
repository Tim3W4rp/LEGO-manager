package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.services.DozerService;
import cz.fi.muni.legomanager.services.KitService;
import cz.fi.muni.legomanager.services.SetOfKitsService;
import org.dozer.Mapper;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
        return dozerService.mapTo(categoryService.getCategory(categoryId), CategoryDTO.class);
    }

    @Override
    public List<SetOfKitsDTO> getSets(Long categoryId) {
        Category c = categoryService.getCategory(categoryId);
        if (c == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        return dozerService.mapTo(c.getSetsOfKits(), SetOfKitsDTO.class);
    }

    @Override
    public List<KitDTO> getKits(Long categoryId) {
        Category c = categoryService.getCategory(categoryId);
        if (c == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        return dozerService.mapTo(c.getKits(), KitDTO.class);
    }

    @Override
    public void addSet(Long categoryId, Long setId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        SetOfKits set = setOfKitsService.getSet(setId);
        if (set == null) {
            throw new RuntimeException("Set doesn't exist.");
        }
        categoryService.addSetOfKits(category, set);
    }

    @Override
    public void removeSet(Long categoryId, Long setId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        SetOfKits set = setOfKitsService.getSet(setId);
        if (set == null) {
            throw new RuntimeException("Set doesn't exist.");
        }
        category.removeSetOfKits(set);
    }

    @Override
    public void addKit(Long categoryId, Long kitId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        Kit kit = kitService.findKitById(kitId);
        if (kit == null) {
            throw new RuntimeException("Kit doesn't exist.");
        }
        category.addKit(kit);
    }

    @Override
    public void removeKit(Long categoryId, Long kitId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        Kit kit = kitService.findKitById(kitId);
        if (kit == null) {
            throw new RuntimeException("Kit doesn't exist.");
        }
        category.removeKit(kit);
    }

    @Override
    public void removeCategory(Long categoryId) {
        categoryService.delete(categoryId);
    }
}
