package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.dao.KitBrickDao;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.KitBrick;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Martin Jordán
 */
@Service
public class KitService {
    private KitDao kitDao;
    private KitBrickDao kitBrickDao;

    @Inject
    public KitService(KitDao kitDao) {
        this.kitDao = kitDao;
    }

    public Kit createKit(Kit kit) {
        return kitDao.create(kit);
    }

    public void deleteKit(long id) {
        Kit kit = kitDao.findById(id);
        if (kit == null) {
            throw new RuntimeException("Deleting non-existant kit");
        }

        kitDao.delete(kit);
    }

    public void deleteBrickFromKit(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        if (kit == null) {
            throw new RuntimeException("Deleting brick from non-existant kit");
        }



    }
}
