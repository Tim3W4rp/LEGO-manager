package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.SetOfKits;

import java.util.List;
import java.util.Set;

/**
 * @author Martin Jordán
 */
public interface KitService {
    public Long createKit(Kit kit);
    public Kit findKitById(long id);
    public Long updateKit(Kit kit);
    public void deleteKitById(long id);
    public List<Kit> findAllKits();
    public Set<Kit> getKitsByCategoryId(long categoryId);
    public void addBrickToKitById(Long kitId, Long brickId);
    public void removeOneBrickFromKitById(long kitId, long brickId);
    public void removeAllBricksOfThisTypeFromKitById(long kitId, long brickId);
    public List<Kit> findSimilarKits(Kit similarKit, int priceRange, int ageLimitRange, Category category);
    public Brick findBrickById(long id);
    public void addKitToSet(Kit kit, SetOfKits setOfKits);
}
