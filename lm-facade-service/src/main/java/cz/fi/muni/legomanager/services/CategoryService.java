package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;

import java.util.List;

/**
 * @author Stepan Granat
 */
public interface CategoryService {

    Category getCategory(long id);

    List<Category> getAllCategories();

    Category create(Category category);

    void delete(long id);

    void update(Category category);

    void addKit(Category category, Kit kit);

    void removeKit(Category category, Kit kit);

    void addSetOfKits(Category category, SetOfKits setOfKits);

    void removeSetOfKits(Category category, SetOfKits setOfKits);
}
