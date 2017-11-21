package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;

import java.util.List;

/**
 * @author Martin Jordán
 */
public interface KitFacade {
    public Long createKit(KitCreateDTO kitCreateDTO);
    public void addCategory(Long kitId, Long categoryId);
    public void removeCategory(Long kitId, Long categoryId);
    public void changeDescription(String newDescription);
    public void changePrice(Integer newPrice);
    public void changeAgeLimit(Integer newAgeLimit);
    public void deleteKitById(Long kitId);
    public void deleteKit(KitDTO kitDTO);
    public List<KitDTO> getAllKits();
    public List<KitDTO> getKitsByCategory(Long categoryId);
    public List<CategoryDTO> getKitCategories();
    public KitDTO getKitById(Long kitId);
    public void addBrickToKit(Long kitId, Long brickId);
    public void removeOneBrickFromKitById(Long kitId, Long brickId);
    public void removeAllBricksOfThisTypeFromKitById(Long kitId, Long brickId);
    public KitDTO findOneRandomSimilarKit(KitDTO kitDTO);
    public List<KitDTO> findSimilarKits(KitDTO kitDTO);
}
