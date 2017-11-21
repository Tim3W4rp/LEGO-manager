package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.Brick;

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
    public Set<Kit> getKitsByCategory(Long categoryId);
    public void addCategory(Long kitId, Long categoryId);
    public void removeCategory(Long kitId, Long categoryId);
    public Set<Category> getKitCategories(Long kitId);
    public void addBrickToKit(Long kitId, Long brickId);
    public void removeOneBrickFromKitById(long kitId, long brickId);
    public void removeAllBricksOfThisTypeFromKitById(long kitId, long brickId);
    public Kit findOneRandomSimilarKit(Kit kit);
    public List<Kit> findSimilarKits(Kit kit);
    public Brick findBrickById(long id);
}