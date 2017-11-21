package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.Brick;

import java.util.List;

/**
 * @author Martin Jordán
 */
public interface KitService {
    public Long createKit(Kit kit);
    public Kit findKitById(long id);
    public Long updateKit(Kit kit);
    public void deleteKit(long id);
    public List<Kit> findAllKits();
    public Brick findBrickById(long id);
    public void removeAllBricksOfThisTypeFromKitById(long kitId, long brickId);
    public void removeOneBrickFromKitById(long kitId, long brickId);
    public Kit findOneRandomSimilarKit(Kit kit);
    public List<Kit> findSimilarKits(Kit kit);
}
