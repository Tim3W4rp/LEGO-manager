package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Kit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martin Jordán
 */
@Service
public class KitService {
    private KitDao kitDao;
    private BrickDao brickDao;

    @Inject
    public KitService(KitDao kitDao) {
        this.kitDao = kitDao;
    }

    public Long createKit(Kit kit) {
        kitDao.create(kit);
        return kit.getId();
    }

    public Kit findById(Long id) {
        return kitDao.findById(id);
    }

    public Long updateKit(Kit kit) {
        kitDao.update(kit);
        return kit.getId();
    }

    public void deleteKit(long id) {
        Kit kit = kitDao.findById(id);
        if (kit == null) {
            throw new RuntimeException("Deleting non-existant kit");
        }

        kitDao.delete(kit);
    }

    public List<Kit> findAllKits(){
        return kitDao.findAll();
    }

    public void deleteBrickFromKitById(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        if (kit == null) {
            throw new RuntimeException("Such kit does not exist");
        }

        Brick brick = brickDao.findById(brickId);
        if (brick == null) {
            throw new RuntimeException("Such brick does not exist");
        }

        kit.removeAllBricksOfThisType(brick);
    }

    public void removeBrick(){

    }

}
