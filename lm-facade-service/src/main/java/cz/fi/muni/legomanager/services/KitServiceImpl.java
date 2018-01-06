package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.dao.CategoryDao;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.KitBrick;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Martin Jordán
 */
@Service
public class KitServiceImpl implements KitService {

    @Autowired
    private KitDao kitDao;

    @Autowired
    private BrickDao brickDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private KitBrickService kitBrickService;

    @Override
    public Long createKit(Kit kit) {
        if (kit == null) {
            throw new IllegalArgumentException("Kit cannot be null.");
        }

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
        if (kit == null) {
            throw new IllegalArgumentException("Kit cannot be null.");
        }

        kitDao.update(kit);
        return kit.getId();
    }

    @Override
    public void deleteKitById(long id) {
        Kit kit = kitDao.findById(id);
        if (kit == null) {
            throw new RuntimeException("Deleting non-existant kit");
        }

        kitDao.delete(kit);
    }

    @Override
    public List<Kit> findAllKits() {
        return kitDao.findAll();
    }

    @Override
    public Set<Kit> getKitsByCategoryId(long categoryId) {
        Category category = categoryDao.findById(categoryId);
        return category.getKits();
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
    public List<Kit> findSimilarKits(Kit similarKit, int priceRange, int ageLimitRange, Category category) {
        if (similarKit == null) {
            throw new RuntimeException("Kit is null");
        }

        List<Kit> allKits = kitDao.findAll();
        if (allKits.isEmpty()) {
            throw new RuntimeException("There are no kits to compare this kit to");
        }

        List<Kit> similarKits = new ArrayList<>();
        Integer lowPrice = similarKit.getPrice() - priceRange;
        Integer highPrice = similarKit.getPrice() + priceRange;
        Integer lowAge = similarKit.getAgeLimit() - ageLimitRange;
        Integer highAge = similarKit.getAgeLimit() + ageLimitRange;

        for (Kit kit : allKits) {
            if (((kit.getPrice() >= lowPrice && kit.getPrice() <= highPrice) &&
                    (kit.getAgeLimit() >= lowAge && kit.getAgeLimit() <= highAge) &&
                    kit.getCategory().equals(category)) && !similarKits.contains(kit)) {
                similarKits.add(kit);
            }
        }

        return similarKits;
    }

    @Override
    public Kit createRandomKitByRules(int minBrickCountInKit, int maxBrickCountInKit, List<Brick> bricksInKit) {

        if (minBrickCountInKit < 0 || maxBrickCountInKit < 0) {
            throw new IllegalArgumentException("minBrickCountInKit and maxBrickCountInKit cannot be negative");
        }

        if (maxBrickCountInKit <= minBrickCountInKit) {
            throw new IllegalArgumentException("maxBrickCountInKit must be greater than minBrickCountInKit");
        }

        if (bricksInKit == null || bricksInKit.isEmpty()) {
            throw new IllegalArgumentException("bricksInKit must contain at least one brick");
        }

        Category category;
        List<Category> allCategories = categoryDao.findAll();

        if (allCategories != null && allCategories.size() != 0) {
            category = allCategories.get(generateRandomNumber(0, allCategories.size()) - 1);
        } else {
            category = new Category();
            category.setName("Sample name");
            category.setDescription("Sample description.");
            categoryDao.create(category);
        }

        Kit randomKit = new Kit();
        randomKit.setAgeLimit(generateRandomNumber(0, 100));
        randomKit.setDescription("Random generated kit by rules.");
        randomKit.setPrice(generateRandomNumber(10, 1000));
        randomKit.setCategory(category);

        int maxForOneBrick = maxBrickCountInKit / bricksInKit.size();
        int piecesTotal = 0;
        int numberOfBrick = 0;

        /*for (Brick brick : bricksInKit) {
            numberOfBrick++;
            Long id = kitBrickService.createKitBrick(randomKit, brick);
            KitBrick kitBrick = kitBrickService.findKitBrickById(id);
            kitBrickService.addBrickToKit(randomKit, brick);

            int brickCount = generateRandomNumber(0, maxForOneBrick);
            piecesTotal += brickCount;

            if (numberOfBrick == bricksInKit.size() && piecesTotal < minBrickCountInKit) {
                int minNeeded = minBrickCountInKit - piecesTotal;
                int maxNeeded = maxBrickCountInKit - piecesTotal;
                kitBrickService.setBrickCount(randomKit, brick, generateRandomNumber(minNeeded, maxNeeded));
            } else {
                kitBrickService.setBrickCount(randomKit, brick, brickCount);
            }
            randomKit.addKitBrick(kitBrick);
        }*/

        return kitDao.create(randomKit);
    }

    @Override
    public void addKitToSet(Kit kit, SetOfKits setOfKits) {
        setOfKits.addKit(kit);
    }

    @Override
    public void removeAllBricksOfThisTypeFromKitById(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        Brick brick = brickDao.findById(brickId);
        kitBrickService.removeAllBricksOfThisType(kit, brick);
    }

    @Override
    public void decreaseBrickCountByOne(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        Brick brick = brickDao.findById(brickId);
        kitBrickService.decreaseBrickCountByOne(kit, brick);
    }

    @Override
    public void addBrickToKitById(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        Brick brick = brickDao.findById(brickId);
        kitBrickService.addBrickToKit(kit, brick);
    }

    @Override
    public long getBrickCount(long kitId, long brickId) {
        Kit kit = kitDao.findById(kitId);
        Brick brick = brickDao.findById(brickId);
        return kitBrickService.getBrickCount(kit, brick);
    }

    @Override
    public void setBrickCount(long kitId, long brickId, int amount) {
        Kit kit = kitDao.findById(kitId);
        Brick brick = brickDao.findById(brickId);
        kitBrickService.setBrickCount(kit, brick, amount);
    }

    private int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
