package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.KitBrickDao;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.KitBrick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitBrickServiceImpl implements KitBrickService {

    @Autowired
    private KitBrickDao kitBrickDao;

    @Override
    public Long createKitBrick(Kit kit, Brick brick) {
        kitBrickDao.create(new KitBrick(kit, brick, 1));
        return null;
    }

    @Override
    public KitBrick findKitBrickById(long id) {
        return kitBrickDao.findById(id);
    }

    @Override
    public long getBrickCount(Kit kit, Brick brick) {
        List<KitBrick> kitBricks = kitBrickDao.findAll();
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick) && kitBrick.getKit().equals(kit)) {
                return kitBrick.getCount();
            }
        }
        throw new RuntimeException("Such brick or kit does not exist");
    }

    @Override
    public void addBrickToKit(Kit kit, Brick brick) {
        List<KitBrick> kitBricks = kitBrickDao.findAll();
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick) && kitBrick.getKit().equals(kit)) {
                kitBrick.increaseBrickCountByOne();
                return;
            }
        }

        kitBricks.add(new KitBrick(kit, brick, 1));
    }

    @Override
    public void decreaseBrickCountByOne(Kit kit, Brick brick) {
        List<KitBrick> kitBricks = kitBrickDao.findAll();
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick) && kitBrick.getKit().equals(kit)) {
                kitBrick.decreaseBrickCountByOne();
                return;
            }
        }
        throw new RuntimeException("Such brick does not exist in this kit");

    }

    @Override
    public void removeAllBricksOfThisType(Kit kit, Brick brick) {
        List<KitBrick> kitBricks = kitBrickDao.findAll();
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick) && kitBrick.getKit().equals(kit)) {
                kitBricks.remove(kitBrick);
                return;
            }
        }
        throw new RuntimeException("Such brick does not exist in this kit");
    }

    @Override
    public void setBrickCount(Kit kit, Brick brick, long amount) {
        List<KitBrick> kitBricks = kitBrickDao.findAll();
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick) && kitBrick.getKit().equals(kit)) {
                kitBrick.setCount(amount);
                return;
            }
        }
        throw new RuntimeException("Such brick does not exist in this kit");
    }
}
