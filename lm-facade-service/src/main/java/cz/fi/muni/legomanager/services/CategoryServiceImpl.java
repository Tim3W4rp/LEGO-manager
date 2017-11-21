package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.CategoryDao;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Stepan Granat
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    @Inject
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category getCategory(long id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category create(Category category) {
        categoryDao.create(category);
        return category;
    }

    @Override
    public void delete(long id) {
        Category category = categoryDao.findById(id);
        if (category == null) {
            throw new RuntimeException("This category does not exist");
        }
        categoryDao.delete(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void addKit(Category category, Kit kit) {
        if (category.getKits().contains(kit)) {
            throw new RuntimeException("Kit is already in category");
        }
        category.addKit(kit);
    }

    @Override
    public void removeKit(Category category, Kit kit) {
        category.removeKit(kit);
    }

    @Override
    public void addSetOfKits(Category category, SetOfKits setOfKits) {
        if (category.getSetsOfKits().contains(setOfKits)) {
            throw new RuntimeException("Set is already in category");
        }
        category.addSetOfKits(setOfKits);
    }

    @Override
    public void removeSetOfKits(Category category, SetOfKits setOfKits) {
        category.removeSetOfKits(setOfKits);
    }


}
