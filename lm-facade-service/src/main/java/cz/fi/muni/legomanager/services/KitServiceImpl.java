package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Martin Jordán
 */
@Service
public class KitServiceImpl implements KitService {

    @Inject
    private KitDao kitDao;

    @Inject
    private BrickDao brickDao;

    @Override
    public Long createKit(Kit kit) {
        kitDao.create(kit);
        return kit.getId();
    }

    @Override
    public Kit findKitById(long id) {
        Kit kit = kitDao.findById(id);
        if (kit == null) {
            throw new RuntimeException("Kit with such ID does not exist");
        }

        return kit;
    }

    @Override
    public Long updateKit(Kit kit) {
        kitDao.update(kit);
        return kit.getId();
    }

    @Override
    public void deleteKit(long id) {
        Kit kit = kitDao.findById(id);
        if (kit == null) {
            throw new RuntimeException("Deleting non-existant kit");
        }

        kitDao.delete(kit);
    }

    @Override
    public List<Kit> findAllKits(){
        return kitDao.findAll();
    }

    @Override
    public Brick findBrickById(long id) {
        Brick brick = brickDao.findById(id);
        if (brick == null) {
            throw new RuntimeException("Such brick does not exist");
        }

        return brick;
    }

    @Override
    public void removeAllBricksOfThisTypeFromKitById(long kitId, long brickId) {
        Kit kit = findKitById(kitId);
        Brick brick = findBrickById(brickId);
        kit.removeAllBricksOfThisType(brick);
    }

    @Override
    public void removeOneBrickFromKitById(long kitId, long brickId){
        Kit kit = kitDao.findById(kitId);
        Brick brick = findBrickById(brickId);
        kit.removeBrick(brick);
    }

    @Override
    public Kit findOneRandomSimilarKit(Kit similarKit) {
        Random random = new Random();
        List<Kit> similarKits = findSimilarKits(similarKit);
        int index = random.nextInt(similarKits.size());
        return similarKits.get(index);
    }

    @Override
    public List<Kit> findSimilarKits(Kit similarKit) {
        if (similarKit == null) {
            throw new RuntimeException("Kit is null");
        }

        List<Kit> allKits = kitDao.findAll();
        if (allKits.isEmpty()) {
            throw new RuntimeException("There are no kits to compare this kit to");
        }

        List<Kit> similarKits = new ArrayList<>();
        Integer priceInterval = 200;
        Integer lowPrice = similarKit.getPrice() - priceInterval;
        Integer highPrice = similarKit.getPrice() + priceInterval;
        Integer ageInterval = 2;
        Integer lowAge = similarKit.getAgeLimit() - ageInterval;
        Integer highAge = similarKit.getAgeLimit() + ageInterval;
        Set<Category> similarKitCategories = similarKit.getCategories();

        for (Kit kit : allKits) {
            if ((kit.getPrice() >= lowPrice && kit.getPrice() <= highPrice) &&
                    (kit.getAgeLimit() >= lowAge && kit.getAgeLimit() <= highAge)){
                similarKits.add(kit);
            }

            Set<Category> currentKitCategories = kit.getCategories();
            for (Category currentKitCategory : currentKitCategories) {
                for (Category similarKitCategory : similarKitCategories) {
                    if (currentKitCategory.getName().equals(similarKitCategory.getName())){
                        if (!similarKits.contains(kit)){
                            similarKits.add(kit);
                        }
                    }
                }
            }
        }

        if (similarKits.contains(similarKit)) {
            similarKits.remove(similarKit);
        }

        return similarKits;
    }
}
