package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.CategoryDao;
import cz.fi.muni.legomanager.entity.Category;

import javax.inject.Inject;
import java.util.List;


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
    public void create(Category set) {
        categoryDao.create(set);
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
}
