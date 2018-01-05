package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.KitBrick;

public interface KitBrickService {
    public Long createKitBrick(Kit kit, Brick brick);
    public KitBrick findKitBrickById(long id);
    public void addBrickToKit(Kit kit, Brick brick);
    public void decreaseBrickCountByOne(Kit kit, Brick brick);
    public void removeAllBricksOfThisType(Kit kit, Brick brick);
    public void setBrickCount(Kit kit, Brick brick, int amount);
    public long getBrickCount(Kit kit, Brick brick);
}
