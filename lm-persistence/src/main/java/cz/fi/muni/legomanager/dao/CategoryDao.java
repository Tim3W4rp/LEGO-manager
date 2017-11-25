package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Category;

import java.util.List;

/**
 * @author Stepan Granat
 */
public interface CategoryDao {
    public void create(Category category);

    public Category findById(Long id);

    public Category update(Category category);

    public void delete(Category category);

    public List<Category> findAll();
}
