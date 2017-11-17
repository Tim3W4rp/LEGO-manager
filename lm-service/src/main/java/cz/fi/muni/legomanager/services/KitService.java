package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Kit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Martin Jordán
 */
@Service
public class KitService {
    private KitDao kitDao;

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
            throw new RuntimeException("Kit does not exist, continue.");
        }

        kitDao.delete(kit);
    }

    public void deleteBrickFromKit(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        if (kit == null) {

        }

        kit.findBrickById(brickId);




    }
}
