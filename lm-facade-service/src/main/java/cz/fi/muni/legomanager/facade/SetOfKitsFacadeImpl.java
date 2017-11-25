package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.SetOfKitsCreateDTO;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SetOfKitsFacadeImpl implements SetOfKitsFacade {
    @Autowired
    private SetOfKitsService setService;

/*    @Autowired
    private KitService kitService;

    @Autowired
    SetOfKitsService setOfKitsService;*/

    @Autowired
    private DozerService dozerService;


    @Override
    public Long createSet(SetOfKitsCreateDTO setCreateDTO) {
        SetOfKits mappedSet = dozerService.mapTo(setCreateDTO, SetOfKits.class);
        setService.create(mappedSet);
        return mappedSet.getId();
    }

    @Override
    public SetOfKitsDTO findSetById(Long setId) {
        return dozerService.mapTo(setService.getSet(setId), SetOfKitsDTO.class);
    }

    @Override
    public void updateSet(SetOfKitsDTO setDTO) {
        SetOfKits mappedSet = dozerService.mapTo(setDTO, SetOfKits.class);
        setService.update(mappedSet);
    }

    @Override
    public void deleteSetById(Long setId) {
        setService.delete(setId);
    }

    @Override
    public void changeDescription(Long setId, String newDescription) {
        setService.getSet(setId).setDescription(newDescription);
    }

    @Override
    public void changePrice(Long setId, BigDecimal newPrice) {
        setService.getSet(setId).setPrice(newPrice);
    }

    @Override
    public List<SetOfKitsDTO> getAllSets() {
        return dozerService.mapTo(setService.getAllSets(), SetOfKitsDTO.class);
    }

    @Override
    public List<SetOfKitsDTO> getSetsByCategoryId(Long categoryId) {


    }

    @Override
    public void addKitToSet(Long kitId, Long brickId) {


    }

    @Override
    public void removeKitFromSet(Long kitId, Long brickId) {
        //categoryService.delete(categoryId);
        setService.
    }














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
    public List<KitDTO> getKits(Long categoryId) {
        Category c = categoryService.getCategory(categoryId);
        if (c == null) {
            throw new RuntimeException("Category doesn't exist.");
        }
        return dozerService.mapTo(c.getKits(), KitDTO.class);
    }

    @Override
    public void removeCategory(Long categoryId) {
        categoryService.delete(categoryId);
    }
}
