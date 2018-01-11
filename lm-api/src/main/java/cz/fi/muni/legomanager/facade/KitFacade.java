package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.BrickDTO;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;

import java.util.List;

/**
 * @author Martin Jordán
 */
public interface KitFacade {
    public Long createKit(KitCreateDTO kitCreateDTO);
    public KitDTO findKitById(Long kitId);
    public void updateKit(KitDTO kitDTO);
    public void deleteKitById(Long kitId);
    public void changeDescription(Long kitId, String newDescription);
    public void changePrice(Long kitId, Integer newPrice);
    public void changeAgeLimit(Long kitId, Integer newAgeLimit);
    public List<KitDTO> findAllKits();
    public List<KitDTO> getKitsByCategoryId(Long categoryId);
    public void addBrickToKit(Long kitId, Long brickId);
    public void removeOneBrickFromKitById(Long kitId, Long brickId);
    public void removeAllBricksOfThisTypeFromKitById(Long kitId, Long brickId);
    public List<KitDTO> findSimilarKits(long kitId, int priceRange, int ageLimitRange);
    public KitDTO createRandomKitByRules(int minBrickCount, int maxBrickCount, List<BrickDTO> bricks);
}
