package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.*;

import java.util.List;

public interface CategoryFacade {
    Long createCategory(CategoryDTO category);

    void updateCategory(CategoryDTO category);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long categoryId);

    List<SetOfKitsDTO> getSets(Long categoryId);

    List<KitDTO> getKits(Long categoryId);

    void addSet(Long categoryId, Long setId);

    void removeSet(Long categoryId, Long setId);

    void addKit(Long categoryId, Long kitId);

    void removeKit(Long categoryId, Long kitId);

    void removeCategory(Long categoryId);
}
