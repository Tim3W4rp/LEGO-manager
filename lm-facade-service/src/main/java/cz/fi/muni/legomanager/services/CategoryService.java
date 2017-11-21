package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategory(long id);

    List<Category> getAllCategories();

    void create(Category set);

    void delete(long id);

    void update(Category category);
}
