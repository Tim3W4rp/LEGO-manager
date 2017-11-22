package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.*;

import java.util.List;

public interface CategoryFacade {
    Long createCategory(CategoryDTO category);

    void updateCategory(CategoryDTO category);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long categoryId);

    List<KitDTO> getKits(Long categoryId);

    void removeCategory(Long categoryId);
}
